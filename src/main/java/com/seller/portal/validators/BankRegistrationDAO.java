package com.seller.portal.validators;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class BankRegistrationDAO {

	@NotEmpty(message = "Account name is required")
	@Size(max = 49)
	private String accountName;

	@NotEmpty(message = "Account number is required")
	private String accountNumber;

	@NotEmpty(message = "Bank name is required")
	private String bankName;

	@NotEmpty(message = "SWIFT code is required")
	//@Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$", message = "Please enter valid SWIFT code")
	private String swiftCode;

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getSwiftCode() {
		return swiftCode;
	}

	public void setSwiftCode(String swiftCode) {
		this.swiftCode = swiftCode;
	}
	
}
