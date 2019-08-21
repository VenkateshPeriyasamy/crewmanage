package com.example.crew.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.crew.repo.UserRepo;

@Component
public class AuthenticationComponent implements UserDetailsService {

	@Autowired
    UserRepo repository;
 

    

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	
    
    	//System.out.println(username);
        com.example.crew.model.User manager = repository.findByUsername(username);
       // System.out.println(manager);
        return new User(manager.getUsername(), manager.getPassword(),
                AuthorityUtils.createAuthorityList("Employee"));
    }

    
    
}
