package net.saisimon.util;

import java.io.IOException;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
import nu.xom.ParsingException;

public class EmailUtil {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailUtil.class);
	private static final Pattern EMAIL_HOST_PATTERN = Pattern.compile("@\\w+\\.");
	private static final Pattern EMAIL_PATTERN = Pattern.compile("[\\w!#$%&'*+/=?^_`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\\w](?:[\\w-]*[\\w])?\\.)+[\\w](?:[\\w-]*[\\w])?");
	
	private EmailUtil() {
		throw new IllegalAccessError();
	}
	
	public static String fetchEmailHost(String email) {
		String host = "";
		if (StringUtils.isNotBlank(email)) {
			Matcher matcher = EMAIL_HOST_PATTERN.matcher(email);
			if (matcher.find()) {
				host = email.substring(matcher.start() + 1, matcher.end() - 1);
				host = host.toLowerCase();
			}
		}
		return host;
	}
	
	public static Map<String, String> parseEmailHost(String email) {
		Map<String, String> result = Maps.newHashMap();
		String host = fetchEmailHost(email);
		if (StringUtils.isNotBlank(host)) {
			Builder builder = new Builder();
			String configPath = SystemUtil.getAppPath() + "email/" + host + "_config.xml";
			try {
				Document doc = builder.build(configPath);
				Element element = doc.getRootElement();
				Elements items = element.getChildElements("item");
				for (int i = 0; i < items.size(); i++) {
					Element item = items.get(i);
					result.put(item.getAttribute("key").getValue(), item.getValue());
				}
			} catch (ParsingException e) {
				LOG.error("Parse Email Config Error", e);
				result.clear();
			} catch (IOException e) {
				LOG.error("Email Config Not Exists", e);
				result.clear();
			}
		}
		return result;
	}
	
	public static boolean checkFormat(String email) {
		if (StringUtils.isBlank(email)) {
			return false;
		}
		return EMAIL_PATTERN.matcher(email).matches();
	}
	
}
