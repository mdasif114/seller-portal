package com.seller.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seller.portal.entity.Identity;
import com.seller.portal.entity.User;
import com.seller.portal.repositories.IdentityRepository;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.IdentityRegistrationDAO;

@Service
public class IdentityRegistrationService {

	@Autowired
	private IdentityRepository identityRepository;

	@Autowired
	private UserRepository userRepository;

	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return findByEmail(username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Identity getIdentityDetails() {
		User user = getAuthenticatedUser();
		if (user != null && identityRepository.existsById(user.getUserId())) {
			return identityRepository.getOne(user.getUserId());
		}
		return null;
	}

	public boolean saveIdentityDetails(IdentityRegistrationDAO identityRegistrationDAO) {
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

	private void addIdentityDetails(User user, IdentityRegistrationDAO identityRegistrationDAO) {
		Identity identity = new Identity();
		identity.setIdentityId(user.getUserId());
		identity.setDocumentType(identityRegistrationDAO.getDocumentType());
		identity.setDocumentNumber(identityRegistrationDAO.getDocumentNumber());
		identity.setUser(user);
		identityRepository.save(identity);
	}
}
