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

import com.seller.portal.service.IdentityRegistrationService;
import com.seller.portal.validators.AddressRegistrationDAO;
import com.seller.portal.validators.IdentityRegistrationDAO;

@Controller
@RequestMapping("/register_identity")
public class IdentityRegistrationController {

	@Autowired
	private IdentityRegistrationService identityRegistrationService;

	@ModelAttribute("identity")
	public IdentityRegistrationDAO identityRegistrationDAO() {
		return new IdentityRegistrationDAO();
	}

	@GetMapping
	public ModelAndView viewAddressRegistrationForm(ModelAndView modelAndView) {

		IdentityRegistrationDAO identity = new IdentityRegistrationDAO();

		if (identityRegistrationService.getIdentityDetails() != null) {
			identity.setDocumentNumber(identityRegistrationService.getIdentityDetails().getDocumentNumber());
			identity.setDocumentType((identityRegistrationService.getIdentityDetails().getDocumentType()));
		}
		
		modelAndView.addObject("identity", identity);
		modelAndView.setViewName("register_identity");
		return modelAndView;
	}

	
	@PostMapping
	public String registerUserAccount(@ModelAttribute("identity") @Valid IdentityRegistrationDAO identityDao,
			BindingResult result) {
		if (result.hasErrors()) {
			System.out.println("From if block of IdentityRegistrationController");
			return "register_identity";
		} else {
			System.out.println("From else block of IdentityRegistrationController");

			if (identityRegistrationService.saveIdentityDetails(identityDao)) {
				return "redirect:/register_identity?success";
			} else {
				return "redirect:/login";
			}
		}
	}
}
