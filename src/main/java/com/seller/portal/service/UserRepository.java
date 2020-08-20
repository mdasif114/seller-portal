package com.seller.portal.service;

import com.seller.portal.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository <User, Long > {
    User findByEmail(String email);
}