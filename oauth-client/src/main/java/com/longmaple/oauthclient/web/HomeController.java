package com.longmaple.oauthclient.web;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Principal principal, Model model) {
		model.addAttribute("username", principal.getName());
		return "welcome";
	}
	
	@GetMapping("/index")
	public String index() {
		return "/index";
	}
}
