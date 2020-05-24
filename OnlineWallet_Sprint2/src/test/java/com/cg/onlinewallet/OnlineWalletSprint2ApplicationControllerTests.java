package com.cg.onlinewallet;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.transaction.annotation.Transactional;

@DirtiesContext(classMode=ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Transactional
public class OnlineWalletSprint2ApplicationControllerTests {

	public OnlineWalletSprint2ApplicationControllerTests() {
		// TODO Auto-generated constructor stub
	}
	@LocalServerPort
	private int port;

}
