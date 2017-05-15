package com.hk.pm.accounts.model;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @author Vineeth Kiv
 * Model object to hold the account details
 *
 */
public class Account {

	private String accountID;
	private BigDecimal accountValue;
	private String currency;
	private boolean accountActive;
	private ArrayList<String> accountOwnersNames;
	private ArrayList<Asset> holdings;

	public String getAccountID() {
		return accountID;
	}

	public void setAccountID(String accountID) {
		this.accountID = accountID;
	}

	public BigDecimal getAccountValue() {
		return accountValue;
	}

	public void setAccountValue(BigDecimal accountValue) {
		this.accountValue = accountValue;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public boolean isAccountActive() {
		return accountActive;
	}

	public void setAccountActive(boolean accountActive) {
		this.accountActive = accountActive;
	}

	public ArrayList<String> getAccountOwnersNames() {
		return accountOwnersNames;
	}

	public void setAccountOwnersNames(ArrayList<String> accountOwnersNames) {
		this.accountOwnersNames = accountOwnersNames;
	}

	public ArrayList<Asset> getHoldings() {
		return holdings;
	}

	public void setHoldings(ArrayList<Asset> holdings) {
		this.holdings = holdings;
	}
	
	@Override
	public String toString() {
		return "account id:" + getAccountID() + " ,  isActive:" + isAccountActive() + ", currency:" + getCurrency()
				+ ", getAccountValue:" + getAccountValue(); 
	}

}
