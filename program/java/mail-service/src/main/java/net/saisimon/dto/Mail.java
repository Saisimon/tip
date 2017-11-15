package net.saisimon.dto;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(exclude = "password")
public class Mail implements Serializable {
	
	private static final long serialVersionUID = -4181323228522842730L;
	private static final Logger LOG = LoggerFactory.getLogger(Mail.class);
	private static final String SENT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final Pattern HTML_LABEL_PATTERN = Pattern.compile("<[a-zA-Z]+[0-6]*.*/?\\s*>(.*<\\s*/\\s*[a-zA-Z]+[0-6]*\\s*>)?");
	
	@NotBlank
	private String from;
	/**
	 * AES encrypt
	 */
	@NotBlank
	private String password;
	@NotEmpty
	private String[] to;
	private String replyTo;
	private String[] cc;
	private String[] bcc;
	/**
	 * yyyy-MM-dd HH:mm:ss
	 */
	private String sentDate;
	private String subject;
	@NotBlank
	private String content;
	private String[] attachmentPaths;
	private String[] attachmentNames;
	
	public boolean hasAttachments() {
		return ArrayUtils.isNotEmpty(attachmentPaths);
	}
	
	public boolean isHTML() {
		return StringUtils.isBlank(content) ? false : HTML_LABEL_PATTERN.matcher(content).find();
	}
	
	public Date getSendDate() {
		Date date = null;
		if (StringUtils.isNotBlank(sentDate)) {
			try {
				SimpleDateFormat df = new SimpleDateFormat(SENT_DATE_FORMAT);
				date = df.parse(sentDate);
			} catch (ParseException e) {
				LOG.warn("Parse Sent Date Failed.", e);
			}
		}
		return date;
	}
	
}
