package com.seller.portal.validators;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {

	@NotEmpty(message = "Please choose account type")
	private String accountType;

	@NotEmpty(message = "Please select this field")
	private String shopBasedIn;

	@NotEmpty(message = "Mobile number is required")
	@Pattern(regexp = "^\\+(?:[0-9] ?){6,14}[0-9]$", message = "Please enter valid mobile number")
	private String mobileNumber;

	@Pattern(regexp = "^[a-zA-Z0-9.!#$%&’*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$", message = "Please enter valid email id")
	private String email;

	@Pattern(regexp = "^(?=.*?[A-Z])(?=(.*[a-z]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{10,30}$", message = "The password field must have between 10 to 30 characters,"
			+ " contains both upper-case and lower-case letters,\r\n" + " include one or more numerical digits and"
			+ " one or more special characters (e.g. @, #, $).")
	private String password;
	
}