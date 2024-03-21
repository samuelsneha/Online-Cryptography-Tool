package com.cgtmse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CryptoService {
	
	@Id
	private String plainText;
	private String cryptoText;
	
	
	

}
