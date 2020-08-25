package com.seller.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seller.portal.entities.Address;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.AddressRepository;
import com.seller.portal.validators.AddressRegistrationDto;

@Service
public class AddressRegistrationSevice {

	@Autowired
	private AddressRepository addressRepository;
	
	public Address getUserAddress(User user) {
		if (user != null && addressRepository.existsById(user.getUserId())) {
			return addressRepository.getOne(user.getUserId());
		}
		return null;
	}

	public void saveAddress(User user, AddressRegistrationDto addressRegistrationDAO) {
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
}
