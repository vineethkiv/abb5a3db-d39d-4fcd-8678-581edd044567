package com.hk.pm.accounts.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hk.pm.accounts.common.repository.IAccountRepository;
import com.hk.pm.accounts.common.service.IAccountService;
import com.hk.pm.accounts.model.Account;

/**
 * @author Vineeth Kiv
 * Serivce layer for account service
 *
 */
@Service
public class AccountService implements IAccountService {

	private final Logger logger = LoggerFactory.getLogger(AccountService.class);

	@Autowired
	IAccountRepository accountRepository;

	@Override
	public List<String> getAccountIdList() {

		logger.info("AccountService.getAccountIdList()...");
		return accountRepository.findAccountIdList();
	}

	@Override
	public Account getAccountDetailsById(String accountId) {

		logger.info("AccountService.getAccountDetailsById()... " + accountId);
		return accountRepository.findAccountDetailsById(accountId);
	}

}
