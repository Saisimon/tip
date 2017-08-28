package net.saisimon.icloud;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ICloud implements Closeable {
	
	private static final Logger LOG = LoggerFactory.getLogger(ICloud.class);
	private static final String HOME_URL = "https://www.icloud.com";
	private static final String SETUP_URL = "https://setup.icloud.com/setup/ws/1";
	private static final String BASE_LOGIN_URL = SETUP_URL + "/login";
	private static final String COOKIE_DIR = System.getProperty("java.io.tmpdir") + File.separatorChar + "cookies";
	
	private String clientId;
	private String clientBuildNumber = "17EHotfix1";
	private Map<String, Object> user = new LinkedHashMap<>();
	private Map<String, String> params = new LinkedHashMap<>();
	private Map<String, Object> data = new HashMap<>();
	private Map<String, Object> webservices = new HashMap<>();
	
	private List<Header> headerList = new ArrayList<>();
	
	private HttpsClient httpsClient;
	private boolean authenticate;
	private String md5;
	
	public ICloud(String login, char[] password) throws Exception {
		this.clientId = UUID.randomUUID().toString().toUpperCase();
		md5 = DigestUtils.md5Hex(login);
		
		user.put("apple_id", login);
		user.put("password", password);
		
		params.put("clientBuildNumber", clientBuildNumber);
		params.put("clientId", clientId);
		
		headerList.add(new BasicHeader("Origin", HOME_URL));
		headerList.add(new BasicHeader("Referer", HOME_URL));
		headerList.add(new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/60.0.3112.90 Safari/537.36"));
		
		httpsClient = new HttpsClient();
		httpsClient.setCookieStore(COOKIE_DIR, md5);
		
		authenticate = authenticate();
	}
	
	@SuppressWarnings("unchecked")
	private boolean authenticate() throws IOException {
		Map<String, Object> body = new LinkedHashMap<>();
		body.putAll(user);
		body.put("extended_login", false);
		HttpEntity entity = new StringEntity(JsonUtils.toJson(body), ContentType.APPLICATION_JSON);
		String resp = httpsClient.post(fillUrl(BASE_LOGIN_URL, params), headerList.toArray(new Header[]{}), entity);
		CookieStore cookieStore = httpsClient.getCookieStore();
		httpsClient.saveCookie(COOKIE_DIR, md5, cookieStore);
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		if (null != respMap && !respMap.containsKey("error")) {
			data.putAll(respMap);
			webservices = (Map<String, Object>) data.get("webservices");
			Map<String, Object> dsInfo = (Map<String, Object>) data.get("dsInfo");
			params.put("dsid", dsInfo.get("dsid").toString());
			LOG.info("Authentication Completed Successfully");
			return true;
		} else {
			LOG.error("Authentication Fail");
			return false;
		}
	}
	
	public boolean requiresTFA() {
		Object required = data.get("hsaChallengeRequired");
		if (null != required) {
			return Boolean.parseBoolean(required.toString());
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getDeviceList() throws IOException {
		String device_url = SETUP_URL + "/listDevices";
		Map<String, String> deviceParam = new LinkedHashMap<>();
		deviceParam.putAll(params);
		String resp = httpsClient.get(fillUrl(device_url, deviceParam), headerList.toArray(new Header[]{}));
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		if (null != respMap && !respMap.containsKey("error")) {
			return (List<Map<String, Object>>) respMap.get("devices");
		} else {
			LOG.error("Get Device List Fail, Reason: " + respMap == null ? "UNKNOWN" : respMap.get("error").toString());
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	public boolean sendVerificationCode(Map<String, Object> device) throws IOException {
		String send_code_url = SETUP_URL + "/sendVerificationCode";
		HttpEntity entity = new StringEntity(JsonUtils.toJson(device), ContentType.APPLICATION_JSON);
		String resp = httpsClient.post(fillUrl(send_code_url, params), headerList.toArray(new Header[]{}), entity);
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		if (null != respMap) {
			Object success = respMap.get("success");
			if (null != success) {
				return Boolean.parseBoolean(success.toString());
			}
			return false;
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public boolean validateVerificationCode(Map<String, Object> device, String code) throws IOException {
		Map<String, Object> dataParam = new HashMap<>();
		dataParam.putAll(device);
		dataParam.put("verificationCode", code);
		dataParam.put("trustBrowser", true);
		String validate_code_url = SETUP_URL + "/validateVerificationCode";
		HttpEntity entity = new StringEntity(JsonUtils.toJson(dataParam), ContentType.APPLICATION_JSON);
		String resp = httpsClient.post(fillUrl(validate_code_url, params), headerList.toArray(new Header[]{}), entity);
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		if (null != respMap) {
			Object success = respMap.get("success");
			if (null != success && Boolean.parseBoolean(success.toString())) {
				authenticate = authenticate();
				return true;
			}
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllContacts() throws IOException {
		if (!authenticate) {
			LOG.error("No Authenticate");
			return null;
		}
		Map<String, Object> contactService = (Map<String, Object>) webservices.get("contacts");
		String contact_base_url = (String) contactService.get("url");
		String contact_refresh_url = contact_base_url + "/co/startup";
		String contact_changeset_url = contact_base_url + "/co/changeset";
		Map<String, String> contactParam = new LinkedHashMap<>();
		contactParam.putAll(params);
		contactParam.put("clientVersion", "2.1");
		contactParam.put("locale", "zh_CN");
		contactParam.put("order", "last,first");
		String resp = httpsClient.get(fillUrl(contact_refresh_url, contactParam), headerList.toArray(new Header[]{}));
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		String reason = "UNKNOWN";
		if (null != respMap && !respMap.containsKey("error")) {
			Map<String, String> contactRefreshParam = new LinkedHashMap<>();
			contactRefreshParam.putAll(contactParam);
			contactRefreshParam.put("prefToken", respMap.get("prefToken").toString());
			contactRefreshParam.put("syncToken", respMap.get("syncToken").toString());
			httpsClient.post(fillUrl(contact_changeset_url, contactRefreshParam));
			resp = httpsClient.get(fillUrl(contact_refresh_url, contactParam), headerList.toArray(new Header[]{}));
			respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
			if (null != respMap && !respMap.containsKey("error")) {
				LOG.info("Get Contacts Successfully");
				return (List<Map<String, Object>>) respMap.get("contacts");
			} else {
				if (respMap == null || respMap.containsKey("reason")) {
					reason = respMap.get("reason").toString();
				}
				LOG.error("Get Contacts Fail, Reason: " + reason);
				return null;
			}
		} else {
			if (respMap == null || respMap.containsKey("reason")) {
				reason = respMap.get("reason").toString();
			}
			LOG.error("Get Contacts Fail, Reason: " + reason);
			return null;
		}
	}
	
	@Override
	public void close() throws IOException {
		if (null != httpsClient) {
			httpsClient.close();
		}
	}
	
	private static String fillUrl(String url, Map<String, String> params) {
		StringBuffer buffer = new StringBuffer(url);
		int idx = 0;
		if (null != params) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				if (idx == 0 && -1 == buffer.indexOf("?")) {
					buffer.append('?');
				} else {
					buffer.append('&');
				}
				buffer.append(entry.getKey()).append('=').append(entry.getValue());
			}
		}
		return buffer.toString();
	}
	
}