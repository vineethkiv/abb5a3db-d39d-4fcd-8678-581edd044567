package com.hk.pm.accounts.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.hk.pm.accounts.api.IAccountDetailService;
import com.hk.pm.accounts.common.service.IAccountService;
import com.hk.pm.accounts.model.Account;

/**
 * @author Vineeth Kiv
 * Controller class for the account service
 *
 */
@RestController
public class AccountsController implements IAccountDetailService {

	private final Logger logger = LoggerFactory.getLogger(AccountsController.class);
	
	@Autowired
	IAccountService accountService;
	
	@Override
	public List<String> getAccountIdList() {
		logger.info("Calling getAccountIdList ...");
		return accountService.getAccountIdList();
	}

	@Override
	public Account getAccountDetailsById(@PathVariable("accountId") String accountId) {
		logger.info("Calling getAccountDetailsById ... " + accountId);
		return accountService.getAccountDetailsById(accountId);
	}
	
}
