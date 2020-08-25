package com.seller.portal.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.seller.portal.entities.Role;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.UserRegistrationDto;

@Service
public class UserRegistrationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}
	
	/*
	 * public User getAddress(Long addressId) { return
	 * userRepository.findByAddressId(addressId); }
	 */
	
	public User save(UserRegistrationDto userRegistration) {
		User user = new User();
		user.setAccountType(userRegistration.getAccountType());
		user.setShopBasedIn(userRegistration.getShopBasedIn());
		user.setMobileNumber(userRegistration.getMobileNumber());
		user.setEmail(userRegistration.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("Asif");
		User user = userRepository.findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}

}