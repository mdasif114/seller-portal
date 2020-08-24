package com.seller.portal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seller.portal.service.BankRegistrationService;
import com.seller.portal.validators.BankRegistrationDAO;

@Controller
@RequestMapping("/register_bank_account")
public class BankRegistrationController {

	@Autowired
	private BankRegistrationService bankRegistrationService;

	@ModelAttribute("bank")
	public BankRegistrationDAO bankRegistrationDAO() {
		return new BankRegistrationDAO();
	}

	@GetMapping
	public ModelAndView viewBankDetailsForm(ModelAndView modelAndView) {
		BankRegistrationDAO bank = new BankRegistrationDAO();
		if (bankRegistrationService.getBankDetails() != null) {
			bank.setAccountName(bankRegistrationService.getBankDetails().getAccountName());
			bank.setAccountNumber((bankRegistrationService.getBankDetails().getAccountNumber()));
			bank.setBankName(bankRegistrationService.getBankDetails().getBankName());
			bank.setSwiftCode(bankRegistrationService.getBankDetails().getSwiftCode());
		}
		modelAndView.addObject("bank", bank);
		modelAndView.setViewName("register_bank_account");
		return modelAndView;
	}
	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("bank") @Valid BankRegistrationDAO bankDao,
			BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("From if block of BankRegistrationController");
			return "register_bank_account";
		} else {
			System.out.println("From else block of BankRegistrationController");
			bankRegistrationService.saveBankDetails(bankDao);
			return "redirect:/register_bank_account?success"; // success is a flag defined in user_registration form as
															// param.success
		}
	}
}
