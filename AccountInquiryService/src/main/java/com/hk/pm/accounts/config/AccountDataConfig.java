package com.hk.pm.accounts.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import com.hk.pm.accounts.model.Account;

/**
 * @author Vineeth Kiv
 * Config class to load the account details from property file
 *
 */
@Component
@PropertySource("classpath:account.properties")
@ConfigurationProperties(prefix = "accountservice.data")
public class AccountDataConfig {

	private List<Account> testAccount;

	public List<Account> getTestAccount() {
		return testAccount;
	}

	public void setTestAccount(List<Account> testAccount) {
		this.testAccount = testAccount;
	}
	
}
