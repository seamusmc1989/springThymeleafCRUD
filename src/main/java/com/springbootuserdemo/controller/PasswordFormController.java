package com.springbootuserdemo.controller;

import com.springbootuserdemo.model.UserProfile;
import com.springbootuserdemo.model.UserPasswordDto;
import com.springbootuserdemo.service.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.validation.Valid;
import java.security.Principal;

@Controller
public class PasswordFormController {

	public static final Logger log = LoggerFactory.getLogger(PasswordFormController.class);

    private final UserPasswordValidator userPasswordValidator;
	private final MyUserDetailsService myUserDetailsService;

    public PasswordFormController(UserPasswordValidator userPasswordValidator, MyUserDetailsService myUserDetailsService) {
        this.userPasswordValidator = userPasswordValidator;
        this.myUserDetailsService = myUserDetailsService;
    }

	@InitBinder("userPasswordDto")
    public void initUserPasswordBinder(WebDataBinder binder) {
        binder.addValidators(userPasswordValidator);
    }

    //Only allow user to update their own userProfile
	private ModelAndView checkUserRoles(ModelAndView modelAndView, String loggedInUser, UserProfile userPassed, String viewName)
	{

		if(userPassed.getUsername().equals(loggedInUser))
		{
			log.info("should give permission as editing their own record");
			modelAndView.setViewName(viewName);
		}
		else
		{
			log.info("should redirect to access denied page");
			modelAndView =  new ModelAndView("redirect:/accessDenied");

		}

		return modelAndView;

	}

	@RequestMapping(value = "/admin/updatePassword/{id}", method = RequestMethod.GET)
	public ModelAndView updateUserPassword(@PathVariable Long id, Principal principal) {

		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin/updateUserPassword");
		UserPasswordDto userPasswordDto = new UserPasswordDto();
		userPasswordDto.setUserId(id);
		modelAndView.addObject("userPasswordDto", userPasswordDto);
		
		String loggedInUser = principal.getName();
		UserProfile user = myUserDetailsService.getUserById(id);
		String viewName = "admin/updateUserPassword";
		
		modelAndView = checkUserRoles(modelAndView, loggedInUser, user, viewName);
		
		return modelAndView;
	}
	
	@RequestMapping(value = "/admin/updateUserPassword", method = RequestMethod.POST)
	public ModelAndView updateUserPasswordPost(@Valid @ModelAttribute("userPasswordDto") UserPasswordDto userPasswordDto, BindingResult bindingResult, ModelAndView modelAndView) {

		if(bindingResult.hasErrors()){
			log.info("bindingResult has errors");
			modelAndView.addObject("userPasswordDto", userPasswordDto);
			modelAndView.setViewName("admin/updateUserPassword");
        }
		else
		{
			myUserDetailsService.updateUserPassword(userPasswordDto.getUserId(), userPasswordDto.getPassword());
			userPasswordDto = new UserPasswordDto();
			modelAndView.addObject("userPasswordDto", userPasswordDto);
			String successMessage = "User Succussfully Updated";
			modelAndView.addObject("successMessage", successMessage);
			modelAndView.setViewName("admin/updateUserPassword");
		}
		
		return modelAndView;
	}
	
}
