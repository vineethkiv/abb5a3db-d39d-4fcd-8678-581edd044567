package com.hk.pm.accounts.common.service;

import java.util.List;

import com.hk.pm.accounts.model.Account;

public interface IAccountService {

	public List<String> getAccountIdList();
	
	public Account getAccountDetailsById(String accountId);
	
	
}
