package com.seller.portal.entities;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
public class Bank  implements Serializable {

	@Id
	private Long bankId;

	private String accountName;
	private String accountNumber;
	private String bankName;
	private String swiftCode;

	@OneToOne
	@MapsId
	private User user;

	public Bank(String accountName, String accountNumber, String bankName, String swiftCode) {
		this.accountName = accountName;
		this.accountNumber = accountNumber;
		this.bankName = bankName;
		this.swiftCode = swiftCode;
	}
}
