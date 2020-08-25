package com.seller.portal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Identity {

	@Id
	private Long identityId;

	private String documentType;
	private String documentNumber;

	@OneToOne
	@MapsId
	private User user;

}
