package com.seller.portal.validators;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentityRegistrationDto {

	@NotEmpty(message = "Please choose document type")
	private String documentType;
	
	@NotEmpty(message = "Please enter document number")
	@Size(max = 49, message = "Must have less than 50 characters")
	private String documentNumber;

}
