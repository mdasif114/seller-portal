package com.seller.portal.controller;

import com.seller.portal.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/dashboard")
public class DashboardController extends DashboardService {

    @Autowired
    private HttpSession session;

    @GetMapping
    public String isRegistrationCompleted() {
        try {
            checkRegistrationStatus();
            return "dashboard";
        } catch (IllegalStateException ise) {
            log.error("Authentication details not found", ise);
            return "redirect:/login";
        }
    }
}
