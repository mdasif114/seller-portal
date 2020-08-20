package com.seller.empowerment.service;

import com.seller.empowerment.entity.User;
import com.seller.empowerment.validators.UserRegistrationValidator;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationValidator registration);
}