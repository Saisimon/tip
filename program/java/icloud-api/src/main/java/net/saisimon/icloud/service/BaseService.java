package net.saisimon.icloud.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;

import net.saisimon.icloud.ICloud;
import net.saisimon.icloud.http.HttpsClient;

public abstract class BaseService {
	
	protected HttpsClient httpsClient;
	protected List<Header> headerList = new ArrayList<>();
	protected Map<String, String> params = new HashMap<>();
	
	public BaseService(HttpsClient httpsClient, Map<String, String> params) {
		if (null == httpsClient) {
			 throw new IllegalArgumentException("Illegal HttpsClient");
		}
		this.httpsClient = httpsClient;
		if (null != params) {
			this.params.putAll(params);
		}
		headerList.addAll(ICloud.headerList);
	}
	
}
