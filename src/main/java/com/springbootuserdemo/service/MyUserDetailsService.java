package com.springbootuserdemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.springbootuserdemo.repository.RoleRepository;
import com.springbootuserdemo.repository.UserProfileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.springbootuserdemo.model.UserProfile;
import com.springbootuserdemo.model.UserPrincipal;
import com.springbootuserdemo.model.UserRole;
import com.springbootuserdemo.model.UserRoleEnum;

@Transactional
@Service
public class MyUserDetailsService implements UserDetailsService {
 
    private UserProfileRepository userRepository;
    private RoleRepository roleRepository;
    
    @Autowired
    public MyUserDetailsService(UserProfileRepository userRepository, RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }

  
	public static final Logger log = LoggerFactory.getLogger(MyUserDetailsService.class);

    
    public List<UserProfile> getAllUsers()
    {    	
    	return userRepository.findAllByActive();
    }

    public UserProfile getUserById(Long userId)
    {    	
		return userRepository.findOne(userId);
    }
    
    public UserProfile updateUserPassword(Long userId, String updatedPassword)
    {
    	UserProfile user =  userRepository.findOne(userId);
		user.setPassword(updatedPassword);
		user.setChangePasswordInd(true);

		return userRepository.save(user);
    }
    
    public UserProfile disableUser(Long userId)
    {
		UserProfile user =  userRepository.findOne(userId);
		user.setActiveInd(false);		
		return userRepository.save(user);
    }

    
    
	public UserProfile findUserByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}
	
	
	public List<UserRole> getUserRoles(Long userId)
	{
		return roleRepository.getRolesByUserId(userId);		
	}
	
	
	@Override     
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {
        UserProfile user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown User");
        }
        
        List<String> userRoles = new ArrayList<String>();
        List<UserRole> userRolesList = roleRepository.getRolesByUserId(user.getId());

        //default role
        userRoles.add("ADMIN");

        userRolesList.forEach(item-> {
                log.info("item role.name is: " + item.getRole().name());
                userRoles.add(item.getRole().name());
            }
          );
        
        boolean enabled = true;
        boolean accountNonExpired = true;
        boolean credentialsNotExpired = true;
        boolean accountNonLocked = true;
        UserPrincipal principal = new UserPrincipal(
                user,
                enabled, accountNonExpired, credentialsNotExpired, accountNonLocked,
                getAuthorities(userRoles)
        );
                
        return principal;
    }
    
    private List<GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream()
                .map(r -> new SimpleGrantedAuthority(r))
                .collect(Collectors.toList());
    }
    
}