package com.seller.portal.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seller.portal.entity.Address;
import com.seller.portal.entity.User;
import com.seller.portal.repositories.AddressRepository;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.AddressRegistrationDAO;

@Service
public class AddressRegistrationSevice {

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public List<Address> getAddress() {
		List<Address> address = new ArrayList<Address>();
		addressRepository.findAll().forEach(address::add);
		return address;
	}

	public Address getUserAddress() {
		User user = getAuthenticatedUser();
		if (user != null && addressRepository.existsById(user.getUserId())) {
			return addressRepository.getOne(user.getUserId());
		}
		return null;
	}

	public void saveAddress(AddressRegistrationDAO addressRegistrationDAO) {
		System.out.println("Printing all address: " + getAddress().size());
		User user = getAuthenticatedUser();

		if (user != null && !addressRepository.existsById(user.getUserId())) {
			addAddress(user, addressRegistrationDAO);
			return;
		} else {
			updateAddress(user, addressRegistrationDAO);
		}
	}

	private void updateAddress(User user, AddressRegistrationDAO addressRegistrationDAO) {
		if (user != null && addressRepository.existsById(user.getUserId())) {
			System.out.println("Deleting existing address for user id: " + user.getUserId());
			addressRepository.deleteById(user.getUserId());
		}
		addAddress(user, addressRegistrationDAO);
	}

	private void addAddress(User user, AddressRegistrationDAO addressRegistrationDAO) {
		Address address = new Address();
		address.setAddressId(user.getUserId());
		address.setLineOne(addressRegistrationDAO.getLineOne());
		address.setLineTwo(addressRegistrationDAO.getLineTwo());
		address.setCity(addressRegistrationDAO.getCity());
		address.setCountry(addressRegistrationDAO.getCountry());
		address.setPostCode(addressRegistrationDAO.getPostCode());
		address.setUser(user);
		addressRepository.save(address);
	}
	
	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return findByEmail(username);
	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
