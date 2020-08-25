package com.seller.portal.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.seller.portal.entities.Address;
import com.seller.portal.entities.User;
import com.seller.portal.repositories.UserRepository;
import com.seller.portal.service.AddressRegistrationSevice;
import com.seller.portal.validators.AddressRegistrationDto;

@Controller
@RequestMapping("/register_address")
public class AddressRegistrationController {

	@Autowired
	private AddressRegistrationSevice addressService;

	@Autowired
	private UserRepository userRepository;
	
	private User getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			return userRepository.findByEmail(authentication.getName());
		}
		return null;
	}
	
	@ModelAttribute("address")
	public AddressRegistrationDto addressRegistrationDAO() {
		return new AddressRegistrationDto();
	}

	@GetMapping
	public ModelAndView viewAddressRegistrationForm(ModelAndView modelAndView) {

		Address addressEnity = addressService.getUserAddress(getAuthenticatedUser());
		AddressRegistrationDto address = new AddressRegistrationDto();

		if (addressEnity != null) {
			address.setLineOne(addressEnity.getLineOne());
			address.setLineTwo(addressEnity.getLineTwo());
			address.setCity(addressEnity.getCity());
			address.setPostCode(addressEnity.getPostCode());
			address.setCountry(addressEnity.getCountry());
		}

		modelAndView.addObject("address", address);
		modelAndView.setViewName("register_address");
		return modelAndView;
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("address") @Valid AddressRegistrationDto aDao,
			BindingResult result) {
		if (result.hasErrors()) {
			return "register_address";
		} else {
			addressService.saveAddress(getAuthenticatedUser(), aDao);
			return "redirect:/register_address?success";
		}
	}
}
