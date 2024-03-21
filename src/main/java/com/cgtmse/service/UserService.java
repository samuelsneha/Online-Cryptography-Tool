package com.cgtmse.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cgtmse.entity.User;

public interface UserService {
	
	User saveUser( User user);
	
	boolean findUser( String email );

	//UserDetails loadUserByUsername( String username )throws UsernameNotFoundException;
}
