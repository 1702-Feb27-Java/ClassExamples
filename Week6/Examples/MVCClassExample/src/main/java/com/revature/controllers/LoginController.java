package com.revature.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.User;
import com.revature.service.UserService;

@Controller
@RequestMapping(value = "/login")
public class LoginController {
	
	@Autowired
	User emptyUser;
	
	@Autowired
	UserService userService;
	
	@ModelAttribute("someInfo")
	public String addInfoToRequestScope(){
		
		System.out.println("adding somthing to the modelmap");
		return "This is the information added";
		
	}

	@RequestMapping(method = RequestMethod.GET)
	public String getLoginPage(ModelMap modelMap){
		
		System.out.println(modelMap.get("someInfo"));
		System.out.println("THis was a get request");
		modelMap.addAttribute("user", emptyUser);
		return "login";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String doLogin(@Valid User user, BindingResult bindingResult, ModelMap modelMap, HttpSession session){
		
		System.out.println("This was a post request");
		if (bindingResult.hasErrors()){
			
			return "login";
			
		}
		User authUser = userService.auth(user);
		if (authUser != null) {
			
			System.out.println(user.getUsername());
			modelMap.addAttribute("user", user);
			session.setAttribute("alsoUser", user);
			return "home";
			
		}
		else{
			modelMap.addAttribute("errorMessage", "Username/password incorrect");
			return "login";
		}
	}
	
}
