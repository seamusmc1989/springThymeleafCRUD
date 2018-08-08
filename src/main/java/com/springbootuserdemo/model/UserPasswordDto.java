package com.springbootuserdemo.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UserPasswordDto implements Serializable{

    private static final long serialVersionUID = -2221852531645649922L;
    private long userId;
    private String password;
    private String oldPassword;
    private String repeatedPassword;

    public UserPasswordDto() {
    }

    public UserPasswordDto(long userId) {
        this.userId = userId;
    }

    public UserPasswordDto( long userId, String password, String oldPassword, String repeatedPassword) {
        this.password = password;
        this.oldPassword = oldPassword;
        this.repeatedPassword = repeatedPassword;
        this.userId = userId;
    }

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatedPassword() {
		return repeatedPassword;
	}

	public void setRepeatedPassword(String repeatedPassword) {
		this.repeatedPassword = repeatedPassword;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
    
}
