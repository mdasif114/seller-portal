package com.seller.portal.service;

import com.seller.portal.entities.Role;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.UserRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserRegistrationService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private HttpSession session;

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public User findByMobileNumber(String mobileNumber) {
		return userRepository.findByMobileNumber(mobileNumber);
	}
	
	public User save(UserRegistrationDto userRegistration) {
		User user = new User();
		user.setAccountType(userRegistration.getAccountType());
		user.setShopBasedIn(userRegistration.getShopBasedIn());
		user.setMobileNumber(userRegistration.getMobileNumber());
		user.setEmail(userRegistration.getEmail());
		user.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
		user.setRoles(Arrays.asList(new Role("ROLE_USER")));
		log.info("adding user details into db: " + user);
		return userRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String email) {
		User user = findByEmail(email);
		
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}

		session.setAttribute("userId", user.getUserId());
		
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
				mapRolesToAuthorities(user.getRoles()));
	}
	
	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
	}
}