package com.seller.portal.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long userId;

	private String accountType;
	private String shopBasedIn;

	@Column(unique = true)
	private String mobileNumber;

	@Column(unique = true)
	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id",
	// referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name
	// ="role_id", referencedColumnName = "role_id"))
	private Collection<Role> roles;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Address address;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Bank bank;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Identity identity;

	public User(String accountType, String shopBasedIn, String email, String mobileNumber, String password) {
		this.accountType = accountType;
		this.shopBasedIn = shopBasedIn;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.password = password;
	}

	public User(String accountType, String shopBasedIn, String email, String password, Collection<Role> roles) {
		this.accountType = accountType;
		this.shopBasedIn = shopBasedIn;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

}