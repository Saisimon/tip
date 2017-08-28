package net.saisimon.icloud.util;

import java.util.Map;

public class StringUtils {
	
	public static String fillUrl(String url, Map<String, String> params) {
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
	
	public static boolean isBlank(String str) {
		return null == str || "".equals(str.trim());
	}
	
	public static boolean isNotBlank(String str) {
		return !isBlank(str);
	}
	
}
