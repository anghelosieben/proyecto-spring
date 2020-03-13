package com.aplication.proyecto.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
	@GetMapping({"/login","/"})
	public String login(Model model,Principal principal,RedirectAttributes flash,
			@RequestParam(value="logout",required = false) String logout,
			@RequestParam(value="error",required = false) String error) {
		if(error!=null)
			model.addAttribute("error", "error carajo!!!");	
		
		if(principal!=null)
			flash.addAttribute("info", "ya ha iniciado sesion");
		
		if(logout!=null) 
			model.addAttribute("logout","se cerro la sesion!!");
		
		return "login";
	}
}
