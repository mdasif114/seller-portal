package com.seller.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.portal.entities.Identity;

@Repository
public interface IdentityRepository extends  JpaRepository<Identity, Long>  {
}
