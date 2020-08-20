package com.seller.portal.service;

import com.seller.portal.entity.User;
import com.seller.portal.validators.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto user_registration);
}