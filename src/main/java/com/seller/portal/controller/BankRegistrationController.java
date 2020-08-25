package com.seller.portal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seller.portal.entities.Bank;
import com.seller.portal.service.BankRegistrationService;
import com.seller.portal.validators.BankAccountRegistrationDto;

@Controller
@RequestMapping("/register_bank_account")
public class BankRegistrationController {

	@Autowired
	private BankRegistrationService bankService;

	@ModelAttribute("bank")
	public BankAccountRegistrationDto bankRegistrationDAO() {
		return new BankAccountRegistrationDto();
	}

	@GetMapping
	public ModelAndView viewBankDetailsForm(ModelAndView modelAndView) {

		Bank bankEnity = (Bank) bankService.getBankDetails();
		BankAccountRegistrationDto bank = new BankAccountRegistrationDto();

		if (bankEnity != null) {
			bank.setAccountName(bankEnity.getAccountName());
			bank.setAccountNumber(bankEnity.getAccountNumber());
			bank.setBankName(bankEnity.getBankName());
			bank.setSwiftCode(bankEnity.getSwiftCode());
		}

		modelAndView.addObject("bank", bank);
		modelAndView.setViewName("register_bank_account");
		return modelAndView;
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("bank") @Valid BankAccountRegistrationDto bankDao,
			BindingResult result) {

		if (result.hasErrors()) {
			return "register_bank_account";
		} else {
			bankService.saveBankDetails(bankDao);
			return "redirect:/register_bank_account?success"; // success is a flag defined in user_registration form as
																// param.success
		}
	}
}
