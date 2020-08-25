package com.seller.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seller.portal.entities.Bank;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.BankAccountRepository;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.BankAccountRegistrationDto;

@Service
public class BankRegistrationService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private UserRepository userRepository;

	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByEmail(authentication.getName());
	}

	public Bank getBankDetails() {
		User user = getAuthenticatedUser();
		if (user != null && bankAccountRepository.existsById(user.getUserId())) {
			return bankAccountRepository.getOne(user.getUserId());
		}
		return null;
	}

	public void saveBankDetails(BankAccountRegistrationDto bankRegistrationDAO) {
		User user = getAuthenticatedUser();
		if (user != null && bankAccountRepository.existsById(user.getUserId())) {
			bankAccountRepository.deleteById(user.getUserId());
		}
		addBankDetails(user, bankRegistrationDAO);
	}

	private void addBankDetails(User user, BankAccountRegistrationDto bankRegistrationDAO) {
		Bank bank = new Bank();
		bank.setBankId(user.getUserId());
		bank.setAccountName(bankRegistrationDAO.getAccountName());
		bank.setAccountNumber(bankRegistrationDAO.getAccountNumber());
		bank.setBankName(bankRegistrationDAO.getBankName());
		bank.setSwiftCode(bankRegistrationDAO.getSwiftCode());
		bank.setUser(user);
		bankAccountRepository.save(bank);
	}

}