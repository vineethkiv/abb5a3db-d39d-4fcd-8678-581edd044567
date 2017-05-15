package com.hk.pm.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author Vineeth Kiv
 * Bootstart class for the account service
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
public class AccountInquiryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountInquiryServiceApplication.class, args);
	}
}
