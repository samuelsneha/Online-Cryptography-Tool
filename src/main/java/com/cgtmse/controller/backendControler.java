package com.cgtmse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgtmse.service.cryptoService;

@RestController
@RequestMapping("/hybrid")
public class backendControler {
	
	
	@Autowired
	cryptoService cryptoservice;
	
	@GetMapping( "/testHybrid")
	ResponseEntity<?> testHybrid(){
		return ResponseEntity.ok("ok test is successful");
	}
	
	
	@GetMapping( "/encryptHybrid")
	ResponseEntity<?> encryptHybrid(){
		try {
			cryptoservice.createRSAKeys();
			String RSAresult = cryptoservice.encryptRSAData();
			String AESResult = cryptoservice.encryptAESFile();
			return ResponseEntity.ok( AESResult +"done till here");
		}catch( Exception e ) {
			System.out.println( e );
			return ResponseEntity.ok("done till here");
		}
	}
	
	
	@GetMapping( "/decryptHybrid")
	ResponseEntity<?> decryptHybrid(){
		try {
			String AESResult = cryptoservice.decryptAESFile();
			String RSAResult = cryptoservice.decryptRSAData();
			return ResponseEntity.ok( RSAResult +"done till here");
		}catch( Exception e ) {
			System.out.println( e );
			return ResponseEntity.ok("done till here");
		}
	}
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@GetMapping("/firstEncryption")
	public String firstEncryption( @RequestParam String variable ){
		cryptoservice.createRSAKeys();
		//String resutSingleEncryption = cryptoservice.firstEncryption( variable );
		String resutSingleEncryption = cryptoservice.hybridEncryption( variable );
		return resutSingleEncryption;
	}
	
	@GetMapping("/hybridEncryption")
	public String hybridEncryption( @RequestParam String variable ) {
		cryptoservice.createRSAKeys();
		String resutSingleEncryption = cryptoservice.hybridEncryption( variable );
		System.out.println( " is the result of single encryption " +resutSingleEncryption);
		String resultHybridEncryption = cryptoservice.firstEncryption(resutSingleEncryption );
		System.out.println( " is the result of hybrid encryption " +resultHybridEncryption);
		return resultHybridEncryption;
	}
	
	@GetMapping("/firstDecryption")
	public String firstDecryption( @RequestParam String variable ) {
		//cryptoservice.createRSAKeys();
		String resultSingleEncryption = cryptoservice.firstDecryption(variable);
		return resultSingleEncryption;
		
	}

	@GetMapping( "/hybridDecryption")
	public String hybridDecryption( @RequestParam String variable ) {
		System.out.println( "variable is "+variable);
//		cryptoservice.createRSAKeys();	
		String resultSingleDecryption = cryptoservice.hybridDecryption(variable);
		System.out.println( " is the result of hybrid decryption " +resultSingleDecryption);
		String resultHybridDecryption = cryptoservice.firstDecryption(resultSingleDecryption);
		System.out.println( " is the result of single decryption " +resultHybridDecryption);
		return resultHybridDecryption;
	}
}
