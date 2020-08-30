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
@Entity
@NoArgsConstructor
@EqualsAndHashCode
public class Address implements Serializable {

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

	public Address(String city, String country, String lineOne, String lineTwo, String postCode) {
		this.lineOne = lineOne;
		this.lineTwo = lineTwo;
		this.city = city;
		this.postCode = postCode;
		this.country = country;
	}
}
