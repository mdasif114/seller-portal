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

import com.seller.portal.entity.User;
import com.seller.portal.service.UserRegistrationService;
import com.seller.portal.validators.UserRegistrationDto;

@Controller
@RequestMapping("/user_registration")
public class UserRegistrationController {

	@Autowired
	private UserRegistrationService userService;

	@ModelAttribute("user")
	public UserRegistrationDto userRegistrationDto() {
		return new UserRegistrationDto();
	}

	@GetMapping
	public String showRegistrationForm(Model model) {
		// model.addAttribute("user", new UserRegistrationDto()); --> you can also add
		// this way instead of using @ModelAttribute, thymleaf use this class to store
		// form details
		return "user_registration";
	}

	@PostMapping
	public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
			BindingResult result) {

		User existing = userService.findByEmail(userDto.getEmail());

		if (existing != null && existing.getMobileNumber() != null) {
			result.rejectValue("mobileNumber", null, "There is an account already registered with this mobile number");
		}

		if (existing != null && existing.getEmail() != null) {
			result.rejectValue("email", null, "There is an account already registered with this email");
		}

		if (result.hasErrors()) {
			System.out.println("From if block of UserRegistrationController");
			return "user_registration";
		} else {
			System.out.println("From else block of UserRegistrationController");
			userService.save(userDto);
			return "redirect:/user_registration?success"; // success is a flag defined in user_registration form as
														 // param.success
		}
	}
}