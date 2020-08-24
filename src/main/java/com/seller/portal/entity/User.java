package com.seller.portal.entity;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	private String accountType;
	private String shopBasedIn;

	@Column(unique = true)
	private String mobileNumber;

	@Column(unique = true)
	private String email;
	private String password;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	//@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"), inverseJoinColumns = @JoinColumn(name ="role_id", referencedColumnName = "role_id"))
	private Collection<Role> roles;

	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
	private Address address;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getShopBasedIn() {
		return shopBasedIn;
	}

	public void setShopBasedIn(String shopBasedIn) {
		this.shopBasedIn = shopBasedIn;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

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

	public User() {
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User{" + "id=" + userId + ", accountType='" + accountType + '\'' + ", shopBasedIn='" + shopBasedIn
				+ '\'' + ", email='" + email + '\'' + ", mobileNumber='" + mobileNumber + '\'' + ", password='"
				+ "*********" + '\'' + ", roles=" + roles + '}';
	}
}