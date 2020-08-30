package com.seller.portal.repositories;

import com.seller.portal.entities.Identity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IdentityRepository extends  JpaRepository<Identity, Long>  {
}
