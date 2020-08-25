package com.seller.portal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Bank {

	@Id
	private Long bankId;

	private String accountName;
	private String accountNumber;
	private String bankName;
	private String swiftCode;

	@OneToOne
	@MapsId
	private User user;
}
