package com.springbootuserdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.springbootuserdemo.model.UserProfile;
import com.springbootuserdemo.model.UserPasswordDto;
import com.springbootuserdemo.service.MyUserDetailsService;

@Component
public class UserPasswordValidator implements Validator {

    private static final Logger logger = LoggerFactory.getLogger(UserPasswordValidator.class);	
    private MyUserDetailsService myUserDetailsService;
    
    public UserPasswordValidator(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(UserPasswordDto.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        logger.info("Validating {}", target);
        UserPasswordDto form = (UserPasswordDto) target;
        validatePasswords(errors, form);
    }


    private void validatePasswords(Errors errors, UserPasswordDto form) {
    	logger.info(">>>>>>>>validate passwords");
    	
    	//need to check all fields aren't null or empty here...     	
    	if(form.getPassword().isEmpty() || form.getOldPassword().isEmpty() || form.getRepeatedPassword().isEmpty()) 
    	{
    		logger.info("should return no password can be empty error");
            errors.reject("password.no_empty", "No Passwords can be empty");
    	}
    	
    	//check if new dto passwords match
        if (!form.getPassword().equals(form.getRepeatedPassword())) {
        	
        	logger.info("should return password.no_match error");
            errors.reject("password.no_match", "Passwords do not match");
        }
        
        //check oldPassword logic is valid
        UserProfile currentUser = myUserDetailsService.getUserById(form.getUserId());
        String oldPassword = currentUser.getPassword();
        
        if(!form.getOldPassword().equals(oldPassword))
        {
        	errors.reject("password.old_match", "Old Password is incorrect");	
        }
        
    }

}
