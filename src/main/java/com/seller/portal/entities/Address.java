package com.seller.portal.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	private Long addressId;
	
	@OneToOne
	@MapsId
	private User user;
	
	private String lineOne;
	private String lineTwo;
	private String city;
	private String postCode;
	private String country;

}
