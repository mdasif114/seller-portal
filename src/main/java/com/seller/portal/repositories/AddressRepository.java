package com.seller.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.portal.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
