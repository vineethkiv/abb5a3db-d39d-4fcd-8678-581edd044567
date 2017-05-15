package com.hk.pm.accounts.api;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hk.pm.accounts.model.Account;

/**
 * @author Vineeth Kiv
 * Exposed service interface
 * Can be shared with service consumers as a contract
 *
 */
@RequestMapping("/accounts")
public interface IAccountDetailService {

	@RequestMapping(
            value = "/getAccountIdList",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
	)
	public List<String> getAccountIdList();
	
	
	@RequestMapping(
            value = "/getAccountById/{accountId}",
            method= RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
	)
	public Account getAccountDetailsById(@PathVariable("accountId") String accountId);
	
	
}
