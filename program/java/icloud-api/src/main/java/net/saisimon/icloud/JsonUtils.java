package net.saisimon.icloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {
	
	private static final Logger LOG = LoggerFactory.getLogger(JsonUtils.class);
	
	public static String toJson(Object object) {
        String result = "";
        if (null == object) {
            return result;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.setSerializationInclusion(Include.NON_NULL);
            result = objectMapper.writeValueAsString(object);
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
        }
        return result;
    }
	
	public static Object fromJson(String requestStr, Class<?> clazz) {
        if (null == requestStr || "".equals(requestStr.trim())) {
            return null;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        Object object = null;
        try {
            object = objectMapper.readValue(requestStr, clazz);
        } catch (Exception e) {
        	LOG.error(e.getMessage(), e);
        }
        return object;
    }
	
}
