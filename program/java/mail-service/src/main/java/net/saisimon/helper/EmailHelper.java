package net.saisimon.helper;

import java.io.File;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import net.saisimon.dto.Mail;
import net.saisimon.dto.Result;
import net.saisimon.env.ConfigProperties;
import net.saisimon.util.EmailUtil;
import net.saisimon.util.EncryptUtil;

@Component
public class EmailHelper {
	
	private static final Logger LOG = LoggerFactory.getLogger(EmailHelper.class);
	
	@Autowired
	private ConfigProperties configProperties;
	
	public Result connect(String email, String password) {
		if (!EmailUtil.checkFormat(email)) {
			return Result.builder().code(400).result(false).message("Email Format Wrong").errorMessage("Param Error").build();
		}
		char[] pwd = decryptPassword(password);
		if (pwd == null) {
			return Result.builder().code(400).result(false).message("Password Decrypt Error").errorMessage("Param Error").build();
		}
		JavaMailSenderImpl javaMailSender = (JavaMailSenderImpl) getJavaMailSender(email, pwd);
		if (javaMailSender == null) {
			return Result.builder().code(500).result(false).message("Unkonwn Email SMTP Error").errorMessage("Param Error").build();
		}
		Result result = null;
		try {
			javaMailSender.testConnection();
			result = Result.builder().code(200).result(true).message("Authentication Success").build();
		} catch (MessagingException e) {
			String errorMsg = e.getMessage();
			LOG.error("Email Authentication Error.", e);
			result = Result.builder().code(400).result(false).errorMessage("Authentication Error").build();
			if (errorMsg.contains("service.mail.qq.com")) {
				result.setMessage("QQ Email Need Authorization Code, Not A Password. More Detail: http://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256");
			} else if (errorMsg.contains("timeout") || errorMsg.contains("timed out")) {
				result.setMessage("Email Connect Timeout.");
			} else {
				result.setMessage("Email Password Wrong.");
			}
		}
		return result;
	}
	
	public Result send(Mail mail) {
		if (!EmailUtil.checkFormat(mail.getFrom())) {
			return Result.builder().code(400).result(false).message("Sender Email Format Wrong").errorMessage("Param Error").build();
		}
		for (String to : mail.getTo()) {
			if (!EmailUtil.checkFormat(to)) {
				return Result.builder().code(400).result(false).message("One of Receiver Email Format Wrong").errorMessage("Param Error").build();
			}
		}
		char[] pwd = decryptPassword(mail.getPassword());
		if (pwd == null) {
			return Result.builder().code(400).result(false).message("Password Decrypt Error").errorMessage("Param Error").build();
		}
		JavaMailSender javaMailSender = getJavaMailSender(mail.getFrom(), pwd);
		if (javaMailSender == null) {
			return Result.builder().code(500).result(false).message("Unkonwn Email SMTP Error").errorMessage("Param Error").build();
		}
		Result result = null;
		try {
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper = null;
			if (mail.hasAttachments()) {
				helper = new MimeMessageHelper(message, true, "UTF-8");
			} else {
				helper = new MimeMessageHelper(message, "UTF-8");
			}
			helper.setFrom(mail.getFrom());
			helper.setTo(mail.getTo());
			if (StringUtils.isNotBlank(mail.getReplyTo())) {
				helper.setReplyTo(mail.getReplyTo());
			}
			if (ArrayUtils.isNotEmpty(mail.getCc())) {
				helper.setCc(mail.getCc());
			}
			if (ArrayUtils.isNotEmpty(mail.getBcc())) {
				helper.setBcc(mail.getBcc());
			}
			if (StringUtils.isNotBlank(mail.getSubject())) {
				helper.setSubject(mail.getSubject());
			}
			Date sentDate = mail.getSendDate();
			if (sentDate != null) {
				helper.setSentDate(sentDate);;
			}
			helper.setText(mail.getContent(), mail.isHTML());
			if (mail.hasAttachments()) {
				int nameLength = mail.getAttachmentNames() == null ? 0 : mail.getAttachmentNames().length;
				for (int i = 0; i < mail.getAttachmentPaths().length; i++) {
					File f = new File(mail.getAttachmentPaths()[i]);
					FileSystemResource file = new FileSystemResource(f);
					String filename = f.getName();
					if (i < nameLength && StringUtils.isNotBlank(mail.getAttachmentNames()[i])) {
						filename = mail.getAttachmentNames()[i];
					}
					helper.addAttachment(filename, file);
				}
			}
			javaMailSender.send(message);
			result = Result.builder().code(200).result(true).message("Send Email Success").build();
		} catch (MessagingException e) {
			LOG.error("Create Mime Message Error.", e);
			result = Result.builder().code(500).result(false).message("Create Mime Message Error").errorMessage("System Error").build();
		} catch (MailAuthenticationException e) {
			String errorMsg = e.getMessage();
			LOG.error("Email Authentication Error.", e);
			result = Result.builder().code(400).result(false).errorMessage("Authentication Error").build();
			if (errorMsg.contains("service.mail.qq.com")) {
				result.setMessage("QQ Email Need Authorization Code, Not A Password. More Detail: http://service.mail.qq.com/cgi-bin/help?subtype=1&&id=28&&no=1001256");
			} else {
				result.setMessage("Email Password Wrong.");
			}
		} catch (MailSendException e) {
			LOG.error("Send Email Error.", e);
			String errorMessage = e.getMessage();
			result = Result.builder().code(500).result(false).errorMessage("System Error").build();
			if (errorMessage.contains("java.io.FileNotFoundException")) {
				result.setMessage("Attachment Not Exists");
			} else if (errorMessage.contains("This message was blocked")) {
				result.setMessage("Attachment Or Content Was Blocked");
			} else if (errorMessage.contains("timeout") || errorMessage.contains("timed out")) {
				result.setMessage("Send Email Timeout");
			} else {
				result.setMessage("Send Email Error");
			}
		} catch (Exception e) {
			LOG.error("Unkonwn Error.", e);
			result = Result.builder().code(500).result(false).message("Unkonwn Error").errorMessage("System Error").build();
		}
		return result;
	}
	
	private JavaMailSender getJavaMailSender(String email, char[] password) {
		Map<String, String> smtpMap = EmailUtil.parseEmailHost(email);
		if (smtpMap.size() != 0) {
			JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
			mailSender.setHost(smtpMap.get("sendService"));
			int port = NumberUtils.toInt(smtpMap.get("sendServicePort"));
			mailSender.setPort(port);
			mailSender.setUsername(email);
			mailSender.setPassword(new String(password));
			// https://javaee.github.io/javamail/docs/api/com/sun/mail/smtp/package-summary.html
			Properties props = mailSender.getJavaMailProperties();
			props.put("mail.transport.protocol", smtpMap.get("sendprotocol"));
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.connectiontimeout", configProperties.getMailConnectiontimeout().toString());
			props.put("mail.smtp.timeout", configProperties.getMailTimeout().toString());
			props.put("mail.smtp.writetimeout", configProperties.getMailWritetimeout().toString());
			if (smtpMap.get("sendServiceEncrypt") != null) {
				String key = "mail.smtp." + smtpMap.get("sendServiceEncrypt") + ".enable";
				props.put(key, "true");
			}
			if (LOG.isDebugEnabled()) {
				props.put("mail.debug", "true");
			}
			return mailSender;
		} else {
			return null;
		}
	}
	
	private char[] decryptPassword(String password) {
		return EncryptUtil.bytes2Chars(EncryptUtil.decrypt(Base64.getDecoder().decode(password), configProperties.getKey()));
	}
	
}
