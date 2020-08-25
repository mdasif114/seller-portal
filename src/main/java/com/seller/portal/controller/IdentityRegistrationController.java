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

import com.seller.portal.service.IdentityRegistrationService;
import com.seller.portal.validators.IdentityRegistrationDto;

@Controller
@RequestMapping("/register_identity")
public class IdentityRegistrationController {

	@Autowired
	private IdentityRegistrationService identityService;

	@ModelAttribute("identity")
	public IdentityRegistrationDto identityRegistrationDAO() {
		return new IdentityRegistrationDto();
	}

	@GetMapping
	public ModelAndView viewAddressRegistrationForm(ModelAndView modelAndView) {

		IdentityRegistrationDto identity = new IdentityRegistrationDto();

		if (identityService.getIdentityDetails() != null) {
			identity.setDocumentNumber(identityService.getIdentityDetails().getDocumentNumber());
			identity.setDocumentType((identityService.getIdentityDetails().getDocumentType()));
		}

		modelAndView.addObject("identity", identity);
		modelAndView.setViewName("register_identity");
		return modelAndView;
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("identity") @Valid IdentityRegistrationDto identityDao,
			BindingResult result) {
		if (result.hasErrors()) {
			return "register_identity";
		}

		return identityService.saveIdentityDetails(identityDao) ? "redirect:/register_identity?success"
				: "redirect:/login";
	}
}
