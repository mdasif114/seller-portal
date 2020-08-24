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

import com.seller.portal.service.AddressRegistrationSevice;
import com.seller.portal.validators.AddressRegistrationDAO;

@Controller
@RequestMapping("/register_address")
public class AddressRegistrationController {

	@Autowired
	private AddressRegistrationSevice addressService;

	@ModelAttribute("address")
	public AddressRegistrationDAO addressRegistrationDAO() {
		return new AddressRegistrationDAO();
	}

	@GetMapping
	public ModelAndView viewAddressRegistrationForm(ModelAndView modelAndView) {

		AddressRegistrationDAO address = new AddressRegistrationDAO();
		if (addressService.getUserAddress() != null) {
			address.setLineOne(addressService.getUserAddress().getLineOne());
			address.setLineTwo(addressService.getUserAddress().getLineTwo());
			address.setCity(addressService.getUserAddress().getCity());
			address.setPostCode(addressService.getUserAddress().getPostCode());
			address.setCountry(addressService.getUserAddress().getCountry());
		}
		modelAndView.addObject("address", address);
		modelAndView.setViewName("register_address");
		return modelAndView;
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("address") @Valid AddressRegistrationDAO aDao,
			BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("From if block of AddressRegistrationController");
			return "register_address";
		} else {
			System.out.println("From else block of AddressRegistrationController");
			addressService.saveAddress(aDao);
			return "redirect:/register_address?success"; // success is a flag defined in user_registration form as
															// param.success
		}
	}
}
