package com.springbootuserdemo.model;

public enum UserRoleEnum {

    SUPER("SUPER ADMIN"), //0
    REG("REG"), //1
    ADMIN("ADMIN"); //2

    private String title;

    UserRoleEnum(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }  
    
    public static UserRoleEnum get(String title) {
    	for (UserRoleEnum ur : UserRoleEnum.values()) {
    		if (ur.getTitle().equals(title)) {
    			return ur;
    		}
    	}
    	return null;
    }
    
}
