package com.seller.portal.controller;

import com.seller.portal.service.BankRegistrationService;
import com.seller.portal.validators.BankRegistrationDto;
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
@RequestMapping("/bankAccountRegistration")
public class BankRegistrationController {

    @Autowired
    private BankRegistrationService bankRepoService;

    @GetMapping
    public ModelAndView viewBankDetails(ModelAndView modelAndView) {
        BankRegistrationDto bankdto;
        try {
            bankdto = bankRepoService.getBankDetails();
            modelAndView.addObject("bank", bankdto);
            modelAndView.setViewName("bankAccountRegistration");
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
    public String registerUserAccount(@ModelAttribute("bank") @Valid BankRegistrationDto bankDto,
                                      BindingResult result) {

        try {
            if (result.hasErrors()) {
                return "bankAccountRegistration";
            } else {
                bankRepoService.saveBankDetails(bankDto);
                return "redirect:/bankAccountRegistration?success";
            }
        } catch (Exception ex) {
            return "internalServerError";
        }
    }
}
