package com.seller.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@GetMapping("/")
	public String root() {
		return "dashboard";
	}
	/*
	 * @GetMapping("/login") public String login() { return "login"; }
	 */

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/user")
	public String userIndex() {
		return "user/index";
	}

	@GetMapping("/register_address")
	public String registerAddress() {
		return "register_address";
	}

	@GetMapping("/register_identity")
	public String registerIdentity() {
		return "register_identity";
	}

	@GetMapping("/register_bank_account")
	public String registerBankAccount() {
		return "register_bank_account";
	}

}