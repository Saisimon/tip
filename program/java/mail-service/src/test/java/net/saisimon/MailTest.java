package net.saisimon;

import java.util.Base64;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.saisimon.env.ConfigProperties;
import net.saisimon.util.EncryptUtil;
import net.saisimon.util.SystemUtil;

/**
 * Mail Unit Test
 * @author Saisimon
 * @see net.saisimon.dto.Mail
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class MailTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(MailTest.class);
	
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ConfigProperties configProperties;

	@Test
	public void testSend() {
		String from = "from@email.com";
		String password = "******";
		String toA = "toA@email.com";
		String toB = "toB@email.com";
		String subject = "Subject";
		String content = "Content";
		Map<String, Object> paramMap = Maps.newHashMap();
		paramMap.put("from", from);
		String pwd = Base64.getEncoder().encodeToString(EncryptUtil.encrypt(password, configProperties.getKey()));
		paramMap.put("password", pwd);
		paramMap.put("to", Lists.newArrayList(toA, toB));
		paramMap.put("subject", subject);
		paramMap.put("content", content);
		paramMap.put("attachmentPaths", Lists.newArrayList("/attachment/path/test.txt", "/attachment/path/test.jpg"));
		paramMap.put("attachmentNames", Lists.newArrayList("attachment.txt", "attachment.jpg"));
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/mail/send")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(SystemUtil.toJson(paramMap)))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			LOG.error("Test Send Error", e);
		}
	}
	
	@Test
	public void testValidate() {
		String email = "email@email.com";
		String password = "********";
		Map<String, String> paramMap = Maps.newHashMap();
		paramMap.put("email", email);
		String pwd = Base64.getEncoder().encodeToString(EncryptUtil.encrypt(password, configProperties.getKey()));
		paramMap.put("password", pwd);
		try {
			mockMvc.perform(MockMvcRequestBuilders.post("/mail/validate")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(SystemUtil.toJson(paramMap)))
					.andExpect(MockMvcResultMatchers.status().isOk())
					.andDo(MockMvcResultHandlers.print());
		} catch (Exception e) {
			LOG.error("Test Validate Error", e);
		}
	}

}
