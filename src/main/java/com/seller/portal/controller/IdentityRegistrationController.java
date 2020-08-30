package com.seller.portal.controller;

import com.seller.portal.service.IdentityRegistrationService;
import com.seller.portal.validators.IdentityRegistrationDto;
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
@RequestMapping("/identityRegistration")
public class IdentityRegistrationController {

    @Autowired
    private IdentityRegistrationService identityService;

    @GetMapping
    public ModelAndView viewIdentityRegistration(ModelAndView modelAndView) {
        IdentityRegistrationDto identityDto;
        try {
            identityDto = identityService.getIdentityDetails();
            log.info("display identity details on GUI ", identityDto);
            modelAndView.addObject("identity", identityDto);
            modelAndView.setViewName("identityRegistration");
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
    public String registerUserAccount(@ModelAttribute("identity") @Valid IdentityRegistrationDto identityDto,
                                      BindingResult result) {
        try {
            if (result.hasErrors()) {
                return "identityRegistration";
            } else {
                identityService.saveIdentityDetails(identityDto);
                return "redirect:/identityRegistration?success";
            }
        } catch (Exception ex) {
            return "internalServerError";
        }
    }
}
