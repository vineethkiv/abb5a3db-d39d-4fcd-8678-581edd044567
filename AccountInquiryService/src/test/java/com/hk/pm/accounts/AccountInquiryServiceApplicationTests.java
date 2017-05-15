package com.hk.pm.accounts;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.hk.pm.accounts.common.service.IAccountService;
import com.hk.pm.accounts.model.Account;

/**
 * @author Vineeth Kiv
 * jUnit for account service validation
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
public class AccountInquiryServiceApplicationTests {

	@Autowired
	IAccountService accountService;
	
	final RestTemplate template = new RestTemplate();
	
	@Test
	public void testGetAccountDetailsByIdService() {
		assertNotNull(accountService.getAccountDetailsById("12345"));
	}

	@Test
	public void testGetAccountIdListService() {
		assertNotNull(accountService.getAccountIdList());
	}
	
	@Test
	public void testGetAccountIdListController() throws Exception {

		String cred = "{\"username\":\"account-service-admin\",\"password\":\"12345678\"}";
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity entity = new HttpEntity(cred, headers);
		ResponseEntity<String> loginOutput = template.exchange("http://localhost:9081/login", HttpMethod.POST, entity,
				String.class);
		HttpHeaders respHeaders = loginOutput.getHeaders();

		assertNotNull(HttpStatus.OK.equals(loginOutput.getStatusCode()));
		assertNotNull(respHeaders.get("Authorization"));

		String authHeader = "";
		if (null != respHeaders.get("Authorization")) {
			authHeader = respHeaders.get("Authorization").get(0).substring(7);

			HttpHeaders detailsHeaders = new HttpHeaders();
			detailsHeaders.setContentType(MediaType.APPLICATION_JSON);
			detailsHeaders.set("Authorization", authHeader);
			entity = new HttpEntity("", detailsHeaders);
			ResponseEntity<Account> accountOutput = template.exchange(
					"http://localhost:9081/accounts/getAccountById/12345", HttpMethod.GET, entity, Account.class);

			assertNotNull(HttpStatus.OK.equals(accountOutput.getStatusCode()));
			assertNotNull(accountOutput.getBody().getAccountID());
			assertNotNull(accountOutput.getBody().getAccountValue());
			
		}

	}
		
}
