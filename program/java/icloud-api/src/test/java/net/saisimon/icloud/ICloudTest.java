package net.saisimon.icloud;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.saisimon.icloud.ICloud;
import net.saisimon.icloud.JsonUtils;

public class ICloudTest {
	
	private static final Logger LOG = LoggerFactory.getLogger(ICloudTest.class);
	
	@Test
	public void testICloud() throws Exception {
		String login = "";
		char[] password = "".toCharArray();
		ICloud iCloud = new ICloud(login, password);
		if (iCloud.requiresTFA()) {
			List<Map<String, Object>> devices = iCloud.getDeviceList();
			LOG.info(JsonUtils.toJson(devices));
			if (null != devices && devices.size() > 0) {
				if (iCloud.sendVerificationCode(devices.get(0))) {
					try (Scanner scanner = new Scanner(System.in)) {
						String code = scanner.nextLine();
						if (!iCloud.validateVerificationCode(devices.get(0), code)) {
							System.exit(1);
						}
					}
				} else {
					System.exit(1);
				}
			} else {
				System.exit(1);
			}
		}
		LOG.info(JsonUtils.toJson(iCloud.getAllContacts()));
		iCloud.close();
	}
	
}
