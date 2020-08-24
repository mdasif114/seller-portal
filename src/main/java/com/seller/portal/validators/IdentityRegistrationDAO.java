package com.seller.portal.validators;

import javax.validation.constraints.NotEmpty;

public class IdentityRegistrationDAO {

	@NotEmpty(message = "Please choose document type")
	private String documentType;
	
	@NotEmpty(message = "Please enter document number")
	private String documentNumber;

	public String getDocumentType() {
		System.out.println("getDocumentType(): " + documentType);
		return documentType;
	}

	public void setDocumentType(String documentType) {
		System.out.println("setDocumentType(): " + documentType);
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		System.out.println("getDocumentNumber(): " + documentNumber);
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		System.out.println("setDocumentNumber(): " + documentNumber);
		this.documentNumber = documentNumber;
	}
	
}
