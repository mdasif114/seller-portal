package com.seller.portal.controller;

import com.seller.portal.service.UserRegistrationService;
import com.seller.portal.validators.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

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
        return "user_registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationDto userDto,
                                      BindingResult result) {

        if (isMobileNumberRegistered(userDto)) {
            result.rejectValue("mobileNumber", null, "This mobile number is already registered");
        }

        if (isEmailRegistered(userDto)) {
            result.rejectValue("email", null, "This email is already registered");
        }

        if (result.hasErrors()) {
            return "user_registration";
        } else {
            userService.save(userDto);
            return "redirect:/login?success";
        }
    }

    private boolean isMobileNumberRegistered(@Valid UserRegistrationDto userDto) {
        return userService.findByMobileNumber(userDto.getMobileNumber()) != null;
    }

    private boolean isEmailRegistered(@Valid UserRegistrationDto userDto) {
        return userService.findByEmail(userDto.getEmail()) != null;
    }
}