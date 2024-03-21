package com.cgtmse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cgtmse.entity.User;
import com.cgtmse.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println(" reached load user by username");
		User user = null;
	        try {
	        	user = userRepository.findByEmail(username);
	        	System.out.println( " email is "+user.getEmail() + " password is " +user.getPassword());
				if( user == null ) {
					throw new UsernameNotFoundException("Invalid username or password");
				}
	        	
	        } catch( UsernameNotFoundException e ) {
	        	System.out.println( e );
	        }
            return new MyUserPrincipal(user);			
	
	}

}
