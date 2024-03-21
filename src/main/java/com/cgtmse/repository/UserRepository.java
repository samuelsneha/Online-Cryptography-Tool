package com.cgtmse.repository;

import org.springframework.data.repository.CrudRepository;

import com.cgtmse.entity.User;

public interface UserRepository extends CrudRepository< User, String>{

	User findByEmail( String email);
}    
