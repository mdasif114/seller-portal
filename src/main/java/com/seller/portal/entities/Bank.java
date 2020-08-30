package com.seller.portal.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
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
}
