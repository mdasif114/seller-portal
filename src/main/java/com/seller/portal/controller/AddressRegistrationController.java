package com.seller.portal.controller;

import com.seller.portal.service.AddressRegistrationSevice;
import com.seller.portal.validators.AddressRegistrationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/addressRegistration")
public class AddressRegistrationController {

    @Autowired
    private AddressRegistrationSevice addressService;

    @GetMapping
    public ModelAndView showUserAddress(ModelAndView modelAndView) {
        AddressRegistrationDto addressEnity;
        try {
            addressEnity = addressService.getUserAddress();
            modelAndView.addObject("address", addressEnity);
            modelAndView.setViewName("addressRegistration");
        } catch (IllegalStateException ise) {
            log.error("Failed to retrieve userid from session state. ", ise);
            modelAndView.setViewName("login");
        } catch (IllegalArgumentException iae) {
            log.error("Failed to retrieve data based on given user id. ", iae);
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @PostMapping
    public String registerUserAddress(@ModelAttribute("address") @Valid AddressRegistrationDto aDto,
                                      BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "addressRegistration";
            } else {
                addressService.saveUserAddress(aDto);
                return "redirect:/addressRegistration?success";
            }
        } catch (Exception ex) {
            return "internalServerError";
        }
    }
}
