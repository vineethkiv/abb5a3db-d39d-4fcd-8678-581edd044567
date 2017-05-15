package com.hk.pm.accounts.common.repository;

import java.util.List;

import com.hk.pm.accounts.model.Account;

public interface IAccountRepository {

	public List<String> findAccountIdList();

	public Account findAccountDetailsById(String accountId);

}
