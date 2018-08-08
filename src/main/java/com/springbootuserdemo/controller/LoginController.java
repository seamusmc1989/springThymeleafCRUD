package com.springbootuserdemo.controller;

import com.springbootuserdemo.model.UserProfile;
import com.springbootuserdemo.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
		

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	public static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
	public ModelAndView login(){

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		return modelAndView;
	}
	
	@RequestMapping(value={"/accessDenied"}, method = RequestMethod.GET)
	public ModelAndView accessdenied(){
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("accessDenied");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserProfile user = myUserDetailsService.findUserByUsername(auth.getName());
		
	    Long userId = user.getId();
	    modelAndView.addObject("userName", "Welcome " + user.getName());
		modelAndView.addObject("userId", userId);
		
		return modelAndView;
	}
	
}
