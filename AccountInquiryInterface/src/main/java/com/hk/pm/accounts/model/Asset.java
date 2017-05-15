package com.hk.pm.accounts.model;

import java.math.BigDecimal;

/**
 * @author Vineeth Kiv
 * Model object to hold the asset details
 *
 */
public class Asset {

	private String ISIN;
	private BigDecimal units;

	public String getISIN() {
		return ISIN;
	}

	public void setISIN(String iSIN) {
		ISIN = iSIN;
	}

	public BigDecimal getUnits() {
		return units;
	}

	public void setUnits(BigDecimal units) {
		this.units = units;
	}

}
