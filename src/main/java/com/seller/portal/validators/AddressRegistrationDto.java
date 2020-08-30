package com.seller.portal.validators;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressRegistrationDto {

	@NotEmpty(message = "Please enter your address")
	@Size(max = 49, message = "Must have less than 50 characters")
	private String lineOne;

	@Size(min=0, max = 49, message = "Must have less than 50 characters")
	private String lineTwo;

	@NotEmpty(message = "Please enter city name")
	@Size(max = 30, message = "Must be less than 30 characters")
	private String city;
	
	@NotEmpty(message = "Please enter postal code")
	@Size(max = 30, message = "Must be less than 30 characters")
	private String postCode;
	
	@NotEmpty(message = "Please select country name")
	private String country;

}
