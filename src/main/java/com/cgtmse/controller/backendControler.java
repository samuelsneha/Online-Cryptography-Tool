package com.cgtmse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cgtmse.entity.Input;
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
	
	
	@PostMapping("/firstEncryption")
	public String firstEncryption( @RequestBody Input variable ){
		cryptoservice.createRSAKeys();
		//String resutSingleEncryption = cryptoservice.firstEncryption( variable );
		String resutSingleEncryption = cryptoservice.hybridEncryption( variable.getText() ); //AES
		return resutSingleEncryption;
	}
	
	@PostMapping("/hybridEncryption")
	public String hybridEncryption( @RequestBody Input variable ) {
		cryptoservice.createRSAKeys();
		String resutSingleEncryption = cryptoservice.hybridEncryption( variable.getText() ); //AES
		System.out.println( " is the result of single encryption " +resutSingleEncryption);
		String resultHybridEncryption = cryptoservice.firstEncryption(resutSingleEncryption ); //RSA
		System.out.println( " is the result of hybrid encryption " +resultHybridEncryption);
		return resultHybridEncryption;
	}
	
	@PostMapping("/firstDecryption")
	public String firstDecryption( @RequestBody Input variable ) {
		//cryptoservice.createRSAKeys();
		String resultSingleEncryption = cryptoservice.firstDecryption(variable.getText()); //AES
		return resultSingleEncryption;
		
	}

	@PostMapping( "/hybridDecryption")
	public String hybridDecryption( @RequestBody Input variable ) {
		System.out.println( "variable is "+variable);
//		cryptoservice.createRSAKeys();	
		String resultSingleDecryption = cryptoservice.hybridDecryption(variable.getText()); //RSA
		System.out.println( " is the result of hybrid decryption " +resultSingleDecryption);
		String resultHybridDecryption = cryptoservice.firstDecryption(resultSingleDecryption); //AES
		System.out.println( " is the result of single decryption " +resultHybridDecryption);
		return resultHybridDecryption;
	}
}
