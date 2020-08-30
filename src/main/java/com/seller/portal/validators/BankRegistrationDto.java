package com.seller.portal.validators;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankRegistrationDto {

	@NotEmpty(message = "Account name is required")
	@Size(max = 49, message = "Must have less than 50 characters")
	private String accountName;

	@NotEmpty(message = "Account number is required")
	@Size(max = 49, message = "Must have less than 50 characters")
	private String accountNumber;

	@NotEmpty(message = "Bank name is required")
	@Size(max = 49, message = "Must have less than 50 characters")
	private String bankName;

	@NotEmpty(message = "SWIFT code is required")
	@Pattern(regexp = "^[A-Z]{6}[A-Z0-9]{2}([A-Z0-9]{3})?$", message = "Please enter valid SWIFT code")
	private String swiftCode;

}
