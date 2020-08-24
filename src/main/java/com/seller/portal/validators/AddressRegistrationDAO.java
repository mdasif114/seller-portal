package com.seller.portal.validators;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;

public class AddressRegistrationDAO {

	@NotEmpty(message = "Please enter your address")
	@Max(value = 49)
	private String lineOne;

	private String lineTwo;

	@NotEmpty(message = "Please enter city name")
	private String city;
	
	@NotEmpty(message = "Please enter postal code")
	private String postCode;
	
	@NotEmpty(message = "Please select country name")
	private String country;
	
	private String email;
	
	public AddressRegistrationDAO() {
	}
	
	public AddressRegistrationDAO(String email) {
		System.out.println("Asif: Const" + email);
		this.email = email;
	}

	public String getEmail() {
		System.out.println("Asif: Get" + email);
		return email;
	}
	
	public void setEmail(String email) {
		System.out.println("Asif: Get" + email);
		this.email = email;
	}

	public String getLineOne() {
		return lineOne;
	}

	public void setLineOne(String lineOne) {
		this.lineOne = lineOne;
	}

	public String getLineTwo() {
		return lineTwo;
	}

	public void setLineTwo(String lineTwo) {
		this.lineTwo = lineTwo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

}
