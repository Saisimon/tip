package net.saisimon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MainController.class);
	
	@RequestMapping(value = "/")
	@ResponseBody
	public String main() {
		LOG.info("Infomation");
		LOG.warn("警告");
		LOG.error("Error", new NullPointerException("Null Pointer Exception"));
		return "Main";
	}
	
}
