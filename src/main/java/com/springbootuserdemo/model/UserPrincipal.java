package com.springbootuserdemo.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserPrincipal extends User {

   private static final long serialVersionUID = -2652363425193262449L;
	private final UserProfile user;

    public UserPrincipal(UserProfile user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(), authorities);
        this.user = user;
    }

    public UserPrincipal(UserProfile user, boolean enabled, boolean accountNonExpired,
                         boolean credentialsNonExpired,
                         boolean accountNonLocked,
                         Collection<? extends GrantedAuthority> authorities) {
        super(user.getUsername(), user.getPassword(),
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
    }
    
    public UserProfile getUser() {
        return this.user;
    }
}
