package com.cgtmse.service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface cryptoService {
	
	public void createRSAKeys();
	
	public String encryptRSAData();
	
	public String encryptAESFile()throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
	public String decryptAESFile() throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
	public ResponseEntity<?> decryptAESFile( byte[] bytesArray, String fileType, RedirectAttributes redirAttrs ) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
	public String decryptRSAData();
	
	String firstEncryption( String message );
	
	String hybridEncryption( String message );
	
	String firstDecryption( String message );
	
	String hybridDecryption(  String message );
	
	public ResponseEntity<?> encryptAESFile( byte[] bytesArray, String fileType, RedirectAttributes redirAttrs ) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
//	public String decryptAESPdf() throws IOException, NoSuchPaddingException,
//    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
//    BadPaddingException, IllegalBlockSizeException;
	
	

}
