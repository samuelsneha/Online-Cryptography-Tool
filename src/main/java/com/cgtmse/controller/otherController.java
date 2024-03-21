package com.cgtmse.controller;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.List;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cgtmse.entity.FileUpload;
import com.cgtmse.entity.Registration;
import com.cgtmse.entity.YourActivity;
import com.cgtmse.service.cryptoService;
import com.cgtmse.service.saveDataService;

@RestController
@RequestMapping("/save")
public class otherController {
	
//	@GetMapping("/yourActivity")
//	public String yourActivityGet( Model model ) {
//		System.out.println("reached yourActivity get");
//		model.addAttribute("registration", new Registration()); //doing "user", new User() wont redirect you to /register
//		return "registration";
//	}
	
//	@GetMapping("/yourActivity")
//	public String yourActivityGet( Model model ) {
//		try {
//			System.out.println("reached yourActivity get");
//		//	System.out.println(principal.getName());
//			model.addAttribute( "yourActivity" , new YourActivity());
//			return "yourActivity";
//		} catch ( Exception e ) {
//			System.out.println( e );
//			return "sample";
//		}
//	}
	
	@Autowired
	saveDataService savedataservice;
	
	@Autowired
	cryptoService cryptoservice;
	
	
	@PostMapping("/saveData")
	public YourActivity saveData( @RequestBody YourActivity yourActivityData ){
		System.out.println( "reached saveData controller");
		System.out.println("$$$" +yourActivityData.getPlainTextMessage());
		System.out.println("$$$" +yourActivityData.getEncryptedMessage());
		System.out.println("$$$" +yourActivityData.getDecryptedMessage());
		System.out.println("$$$" +yourActivityData.getEmail());
		YourActivity yourActivityObj = new YourActivity();
		yourActivityObj.setPlainTextMessage(yourActivityData.getPlainTextMessage());
		yourActivityObj.setEncryptedMessage(yourActivityData.getEncryptedMessage());
		yourActivityObj.setDecryptedMessage(yourActivityData.getDecryptedMessage());
		yourActivityObj.setEmail(yourActivityData.getEmail());
		YourActivity yourActivitySavedData =  savedataservice.saveData(yourActivityObj);
        return yourActivitySavedData;
	}
	
	@GetMapping("/showTable")
	public List<YourActivity> showTable( @RequestParam String email ) {
		System.out.println( " reached show table" );
		System.out.println( "email" +email );
		List<YourActivity> tableRes = savedataservice.showTable(email);
		return tableRes;
	}
	



}
