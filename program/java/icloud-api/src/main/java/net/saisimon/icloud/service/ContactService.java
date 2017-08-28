package net.saisimon.icloud.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.saisimon.icloud.ICloud;
import net.saisimon.icloud.http.HttpsClient;
import net.saisimon.icloud.util.JsonUtils;
import net.saisimon.icloud.util.StringUtils;

public class ContactService {
	
	private static final Logger LOG = LoggerFactory.getLogger(ContactService.class);
	
	private HttpsClient httpsClient;
	private List<Header> headerList = new ArrayList<>();
	private Map<String, String> params = new HashMap<>();
	private String contact_base_url;
	
	public ContactService(HttpsClient httpsClient, String url, Map<String, String> params) {
		if (null == httpsClient) {
			 throw new IllegalArgumentException("Illegal HttpsClient");
		}
		if (StringUtils.isBlank(url)) {
			throw new IllegalArgumentException("Illegal url");
		}
		this.httpsClient = httpsClient;
		this.contact_base_url = url;
		if (null != params) {
			this.params.putAll(params);
		}
		headerList.addAll(ICloud.headerList);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> getAllContacts() throws IOException {
		String contact_refresh_url = contact_base_url + "/co/startup";
		String contact_changeset_url = contact_base_url + "/co/changeset";
		Map<String, String> contactParam = new LinkedHashMap<>();
		contactParam.putAll(params);
		contactParam.put("clientVersion", "2.1");
		contactParam.put("locale", "zh_CN");
		contactParam.put("order", "last,first");
		String resp = httpsClient.get(StringUtils.fillUrl(contact_refresh_url, contactParam), headerList.toArray(new Header[]{}));
		Map<String, Object> respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
		String reason = "UNKNOWN";
		if (null != respMap && !respMap.containsKey("error")) {
			Map<String, String> contactRefreshParam = new LinkedHashMap<>();
			contactRefreshParam.putAll(contactParam);
			contactRefreshParam.put("prefToken", respMap.get("prefToken").toString());
			contactRefreshParam.put("syncToken", respMap.get("syncToken").toString());
			httpsClient.post(StringUtils.fillUrl(contact_changeset_url, contactRefreshParam));
			resp = httpsClient.get(StringUtils.fillUrl(contact_refresh_url, contactParam), headerList.toArray(new Header[]{}));
			respMap = (Map<String, Object>) JsonUtils.fromJson(resp, Map.class);
			if (null != respMap && !respMap.containsKey("error")) {
				LOG.info("Get Contacts Successfully");
				return (List<Map<String, Object>>) respMap.get("contacts");
			} else {
				if (respMap != null && respMap.containsKey("reason")) {
					reason = respMap.get("reason").toString();
				}
				LOG.error("Get Contacts Fail, Reason: " + reason);
				return null;
			}
		} else {
			if (respMap != null && respMap.containsKey("reason")) {
				reason = respMap.get("reason").toString();
			}
			LOG.error("Get Contacts Fail, Reason: " + reason);
			return null;
		}
	}
	
}
