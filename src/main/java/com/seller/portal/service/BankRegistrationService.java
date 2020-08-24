package com.seller.portal.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.seller.portal.entity.Bank;
import com.seller.portal.entity.User;
import com.seller.portal.repositories.BankAccountRepository;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.validators.BankRegistrationDAO;

@Service
public class BankRegistrationService {

	@Autowired
	private BankAccountRepository bankAccountRepository;

	@Autowired
	private UserRepository userRepository;

	public User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		return findByEmail(username);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Bank getBankDetails() {
		User user = getAuthenticatedUser();
		if (user != null && bankAccountRepository.existsById(user.getUserId())) {
			return bankAccountRepository.getOne(user.getUserId());
		}
		return null;
	}

	public void saveBankDetails(BankRegistrationDAO bankRegistrationDAO) {
		User user = getAuthenticatedUser();
		if (user != null && !bankAccountRepository.existsById(user.getUserId())) {
			addBankDetails(user, bankRegistrationDAO);
			return;
		} else {
			updateBankDetails(user, bankRegistrationDAO);
		}
	}

	private void updateBankDetails(User user, BankRegistrationDAO bankRegistrationDAO) {
		if (user != null && bankAccountRepository.existsById(user.getUserId())) {
			System.out.println("Deleting existing address for user id: " + user.getUserId());
			bankAccountRepository.deleteById(user.getUserId());
		}
		addBankDetails(user, bankRegistrationDAO);
	}

	private void addBankDetails(User user, BankRegistrationDAO bankRegistrationDAO) {
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