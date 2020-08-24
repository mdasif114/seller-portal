package com.seller.portal.validators;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class UserRegistrationDto {

	@NotEmpty(message = "Please choose account type")
	private String accountType;

	@NotEmpty(message = "Please select this field")
	private String shopBasedIn;

	@NotEmpty(message = "Mobile number is required")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Please enter valid mobile number")
	private String mobileNumber;

	@Email
	@NotEmpty(message = "Please enter valid email id")
	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Please enter valid email id")
	private String email;

	@NotEmpty(message = "The password field must have between 10 to 30 characters,"
			+ " contains both upper-case and lower-case letters,\r\n" + " include one or more numerical digits and"
			+ " one or more special characters (e.g. @, #, $).")
	private String password;

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getShopBasedIn() {
		return shopBasedIn;
	}

	public void setShopBasedIn(String shopBasedIn) {
		this.shopBasedIn = shopBasedIn;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}