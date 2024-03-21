package com.cgtmse.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cgtmse.entity.User;
import com.cgtmse.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public User saveUser( User user) {
		System.out.println( " reached service ");
		User u = userRepository.save(user);
		return u;
	}
		
	@Override
	public boolean findUser( String email ) {
	    boolean ans = userRepository.existsById(email);
		System.out.println( ans +" ans is ");
		if( ans ) {
			System.out.println("user exists");
		}else {
			System.out.println("user does not exists");
		}
		return ans;
	}



//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		// TODO Auto-generated method stub
//		return null;
//	}


}
