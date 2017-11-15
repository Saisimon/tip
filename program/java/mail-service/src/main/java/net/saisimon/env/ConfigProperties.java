package net.saisimon.env;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config.properties")
public class ConfigProperties {
	
	public static final int SUCCESS = 200;
	public static final int REQUEST_ERROR = 400;
	public static final int SERVER_ERROR = 500;
	
	@Value("${mail.smtp.connectiontimeout:30000}")
	private Long mailConnectiontimeout;
	@Value("${mail.smtp.timeout:30000}")
	private Long mailTimeout;
	@Value("${mail.smtp.writetimeout:60000}")
	private Long mailWritetimeout;
	@Value("${password.encrypt.key:tomtoperp}")
	private String key;
	
	public Long getMailConnectiontimeout() {
		return mailConnectiontimeout;
	}
	public Long getMailTimeout() {
		return mailTimeout;
	}
	public Long getMailWritetimeout() {
		return mailWritetimeout;
	}
	public String getKey() {
		return key;
	}
}
