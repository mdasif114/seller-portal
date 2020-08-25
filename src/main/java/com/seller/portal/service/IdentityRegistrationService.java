package com.seller.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seller.portal.entities.Identity;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.IdentityRepository;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.IdentityRegistrationDto;

@Service
public class IdentityRegistrationService {

	@Autowired
	private IdentityRepository identityRepository;

	@Autowired
	private UserRepository userRepository;

	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByEmail(authentication.getName());
	}

	public Identity getIdentityDetails() {
		User user = getAuthenticatedUser();
		if (user != null && identityRepository.existsById(user.getUserId())) {
			return identityRepository.getOne(user.getUserId());
		}
		return null;
	}

	public boolean saveIdentityDetails(IdentityRegistrationDto identityRegistrationDAO) {
		User user = getAuthenticatedUser();
		
		if (user == null) {
			return false;
		}
		
		if (identityRepository.existsById(user.getUserId())) {
			System.out.println("Deleting existing address for user id: " + user.getUserId());
			identityRepository.deleteById(user.getUserId());
		}
		
		addIdentityDetails(user, identityRegistrationDAO);
		return true;
	}

	private void addIdentityDetails(User user, IdentityRegistrationDto identityRegistrationDAO) {
		Identity identity = new Identity();
		identity.setIdentityId(user.getUserId());
		identity.setDocumentType(identityRegistrationDAO.getDocumentType());
		identity.setDocumentNumber(identityRegistrationDAO.getDocumentNumber());
		identity.setUser(user);
		identityRepository.save(identity);
	}
}
