package com.seller.portal.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Data
@Entity
public class Identity  implements Serializable {

	@Id
	private Long identityId;

	private String documentType;
	private String documentNumber;

	@OneToOne
	@MapsId
	private User user;

}
