package net.saisimon.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ClassUtils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SystemUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(SystemUtil.class);
	
	public SystemUtil() {
		throw new IllegalAccessError();
	}
	
	public static String getAppPath() {
		String appPath = ClassUtils.getDefaultClassLoader().getResource("").getPath();
		if (!appPath.endsWith("/")) {
			appPath += "/";
		}
		return appPath;
	}
	
	public static String toJson(Object object) {
		String result = "";
        if (null != object) {
        	ObjectMapper objectMapper = new ObjectMapper();
        	objectMapper.setSerializationInclusion(Include.NON_NULL);
            try {
                result = objectMapper.writeValueAsString(object);
            } catch (JsonProcessingException e) {
            	LOG.error("Parse to json error", e);
            }
        }
        return result;
	}
	
	public static <T> T fromJson(String json, Class<T> clazz) {
		T t = null;
		if (StringUtils.isNotBlank(json)) {
			ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
	        try {
	            t = objectMapper.readValue(json, clazz);
	        } catch (Exception e) {
	        	LOG.error("Parse from json error", e);
	        }
        }
        return t;
	}
	
}
