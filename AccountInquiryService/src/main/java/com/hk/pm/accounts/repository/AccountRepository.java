package com.hk.pm.accounts.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hk.pm.accounts.common.repository.IAccountRepository;
import com.hk.pm.accounts.config.AccountDataConfig;
import com.hk.pm.accounts.model.Account;


/**
 * @author Vineeth Kiv
 * DAO layer for account service. 
 * Can be replaced with JPA/Crud repository in actual implementation
 *
 */
@Repository
public class AccountRepository implements IAccountRepository {

	private final Logger logger = LoggerFactory.getLogger(AccountRepository.class);
	
	@Autowired
	AccountDataConfig accountConfig;
	
	@Override
	public List<String> findAccountIdList() {
		List<String> accountIdsList = new ArrayList<>();
		
		logger.info("AccountRepository.findAccountIdList() start");
		
		accountConfig.getTestAccount().stream().forEach(t -> {
			accountIdsList.add(t.getAccountID());
		});
		
		logger.info("AccountRepository.findAccountIdList() end");
		return accountIdsList;
	}

	@Override
	public Account findAccountDetailsById(String accountId) {
		
		logger.info("AccountRepository.findAccountDetailsById() start " + accountId);
		
		Account localAccount = accountConfig.getTestAccount().stream()
				.filter(t -> t.getAccountID().equals(accountId)).findFirst().get();
		
		logger.debug("AccountRepository.findAccountDetailsById() details " + localAccount.toString());
		logger.info("AccountRepository.findAccountDetailsById() end " + accountId);
		return localAccount;
	}

}
