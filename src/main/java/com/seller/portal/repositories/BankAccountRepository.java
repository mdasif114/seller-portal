package com.seller.portal.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seller.portal.entity.Bank;

@Repository
public interface BankAccountRepository extends JpaRepository<Bank, Long>  {

}
