package net.saisimon.controller;

import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.saisimon.dto.Mail;
import net.saisimon.dto.Result;
import net.saisimon.helper.EmailHelper;

@RestController
@RequestMapping("/mail")
public class MailController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MailController.class);
	
	@Autowired
	private EmailHelper emailSender;
	
	@PostMapping("/send")
	public Result send(@RequestBody @Valid Mail mail, BindingResult bindingResult) {
		Result result = handleBindingResult(bindingResult);
		if (result == null) {
			result = emailSender.send(mail);
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Result: " + result.toString());
		}
		return result;
	}
	
	@PostMapping("/validate")
	public Result validate(@RequestBody Map<String, String> paramMap) {
		Result result = handleParamMap(paramMap);
		if (result == null) {
			result = emailSender.connect(paramMap.get("email"), paramMap.get("password"));
		}
		if (LOG.isDebugEnabled()) {
			LOG.debug("Result: " + result.toString());
		}
		return result;
	}
	
	private Result handleBindingResult(BindingResult bindingResult) {
		Result result = null;
		if (bindingResult.hasErrors()) {
			result = Result.builder().code(400).result(false).errorMessage("Param Error").build();
			StringBuffer errorBuffer = new StringBuffer();
			if (bindingResult.hasFieldErrors("from")) {
				errorBuffer.append("Form can not be Blank.\n");
			}
			if (bindingResult.hasFieldErrors("password")) {
				errorBuffer.append("Password can not be Blank.\n");
			}
			if (bindingResult.hasFieldErrors("to")) {
				errorBuffer.append("To List can not be Empty.\n");
			}
			if (bindingResult.hasFieldErrors("content")) {
				errorBuffer.append("Email Content can not be Blank.\n");
			}
			if (errorBuffer.length() > 0) {
				result.setMessage(errorBuffer.toString());
			} else if (bindingResult.hasGlobalErrors()) {
				String error = bindingResult.getGlobalError().toString();
				result.setMessage(error);
			} else {
				result.setMessage("Unkonwn Error");
			}
		}
		return result;
	}

	private Result handleParamMap(Map<String, String> paramMap) {
		Result result = null;
		StringBuffer errorBuffer = new StringBuffer();
		String email = paramMap.get("email");
		if (StringUtils.isBlank(email)) {
			errorBuffer.append("Email can not be Blank.\n");
		}
		String password = paramMap.get("password");
		if (StringUtils.isBlank(password)) {
			errorBuffer.append("Password can not be Blank.\n");
		}
		if (errorBuffer.length() > 0) {
			result = Result.builder().code(400).result(false).message(errorBuffer.toString()).errorMessage("Param Error").build();
		}
		return result;
	}
	
}
