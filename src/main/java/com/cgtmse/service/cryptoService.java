package com.cgtmse.service;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public interface cryptoService {
	
	public void createRSAKeys();
	
	public String encryptRSAData();
	
	public String encryptAESFile()throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
	public String decryptAESFile() throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
	public String decryptRSAData();
	
	String firstEncryption( String message );
	
	String hybridEncryption( String message );
	
	String firstDecryption( String message );
	
	String hybridDecryption(  String message );
	
	public String encryptAESFile( byte[] bytesArray, String fileType ) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException;
	
//	public String decryptAESPdf() throws IOException, NoSuchPaddingException,
//    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
//    BadPaddingException, IllegalBlockSizeException;
	
	

}
