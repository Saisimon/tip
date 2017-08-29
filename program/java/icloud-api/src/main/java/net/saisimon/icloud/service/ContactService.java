package net.saisimon.icloud.service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.saisimon.icloud.http.HttpsClient;
import net.saisimon.icloud.util.JsonUtils;
import net.saisimon.icloud.util.StringUtils;

public class ContactService extends BaseService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
	
	private String contact_base_url;
	
	public ContactService(HttpsClient httpsClient, String url, Map<String, String> params) {
		super(httpsClient, params);
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("Illegal url");
		}
		contact_base_url = url + "/co";
	}
	
	public List<Map<String, Object>> getAllContacts() throws IOException {
		Map<String, Object> respMap = startup();
		if (null != respMap) {
			changeset(respMap);
			respMap = startup();
			if (null != respMap) {
				return contacts(respMap);
			}
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Object> startup() throws IOException {
		String contact_refresh_url = contact_base_url + "/startup";
		Map<String, String> contactParam = new LinkedHashMap<>();
		contactParam.putAll(params);
		contactParam.put("clientVersion", "2.1");
		contactParam.put("locale", "zh_CN");
		contactParam.put("order", "last,first");
		String resp = httpsClient.get(StringUtils.fillUrl(contact_refresh_url, contactParam), headerList.toArray(new Header[]{}));
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		if (null != respMap && !respMap.containsKey("error")) {
			if (LOG.isDebugEnabled()) {
				LOG.debug("Contact Startup Success");
			}
			return respMap;
		} else {
			String reason = (String) respMap.getOrDefault("reason", "UNKNOWN");
			LOG.error("Get Contacts Fail, Reason: " + reason);
			return null;
		}
	}
	
	private void changeset(Map<String, Object> respMap) throws IOException {
		String contact_changeset_url = contact_base_url + "/changeset";
		Map<String, String> contactParam = new LinkedHashMap<>();
		contactParam.putAll(params);
		contactParam.put("prefToken", respMap.get("prefToken").toString());
		contactParam.put("syncToken", respMap.get("syncToken").toString());
		httpsClient.post(StringUtils.fillUrl(contact_changeset_url, contactParam));
		if (LOG.isDebugEnabled()) {
			LOG.debug("Contact Changeset");
		}
	}
	
	@SuppressWarnings("unchecked")
	private List<Map<String, Object>> contacts(Map<String, Object> respMap) throws IOException {
		String contacts_url = contact_base_url + "/contacts";
		Map<String, String> contactParam = new LinkedHashMap<>();
		contactParam.putAll(params);
		contactParam.put("clientVersion", "2.1");
		contactParam.put("prefToken", respMap.get("prefToken").toString());
		contactParam.put("syncToken", respMap.get("syncToken").toString());
		contactParam.put("limit", "0");
		contactParam.put("offset", "0");
		String resp = httpsClient.get(StringUtils.fillUrl(contacts_url, contactParam), headerList.toArray(new Header[]{}));
		respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		if (null != respMap && !respMap.containsKey("error")) {
			LOG.info("Get Contacts Successfully");
			return (List<Map<String, Object>>) respMap.get("contacts");
		} else {
			String reason = (String) respMap.getOrDefault("reason", "UNKNOWN");
			LOG.error("Get Contacts Fail, Reason: " + reason);
			return null;
		}
	}
	
}
