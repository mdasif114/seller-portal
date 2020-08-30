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
public class Identity  implements Serializable {

	@Id
	private Long identityId;

	private String documentType;
	private String documentNumber;

	@OneToOne
	@MapsId
	private User user;

    public Identity(String documentNumber, String documentType) {
    	this.documentType = documentType;
    	this.documentNumber = documentNumber;
    }
}
