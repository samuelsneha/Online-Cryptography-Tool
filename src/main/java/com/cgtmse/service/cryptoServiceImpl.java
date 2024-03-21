package com.cgtmse.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

@Service
public class cryptoServiceImpl implements cryptoService{
	
	PublicKey   publickey;
	PrivateKey  privatekey;
	String resultRSA, resultAES, decryptAES, decryptRSA;
	static String secret = "MpH4wQKr350ujU0cpjkpiCHvw2cHen2c";
	static String IVS = "1ZYkPhljnpzYJXW8";
	
	@Override
	public void createRSAKeys() {
		try {
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(4096);
			KeyPair keypair = keyPairGenerator.generateKeyPair();
			publickey = keypair.getPublic();
			privatekey = keypair.getPrivate();
			System.out.println("*****" +publickey.toString());
			System.out.println("*****" +privatekey.toString());
			System.out.println("public key is " +publickey);
			System.out.println("private key is "+privatekey);
			
		}catch( Exception e ) {
			e.printStackTrace();
		}
	}
	
	@Override
	public String encryptRSAData() {
		File file1 = new File("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Encrypted Outputs\\encryptedText.txt");
		try {
			 FileInputStream inputStream = new FileInputStream("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\sample.txt");
			 Cipher cipher1 = Cipher.getInstance("RSA");
			 cipher1.init(Cipher.ENCRYPT_MODE,publickey );
			 byte[] encrypt1 = cipher1.doFinal(inputStream.readAllBytes());		 
			 resultRSA =  new String(Base64.getEncoder().encodeToString(encrypt1));
			 System.out.println("RSA result is "+resultRSA);
			 FileWriter writer = new FileWriter(file1);
			 writer.write(resultRSA);
	         writer.close();
	         inputStream.close();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultRSA;
	}
	
	 private static Key generateKey(String secret) throws Exception {
			byte[] decoded = Base64.getDecoder().decode(secret.getBytes());
			Key key = new SecretKeySpec(decoded, "AES");
			return key;
	 }
	 private static AlgorithmParameterSpec generateIv(String iv) {
			try {
				return new IvParameterSpec(iv.getBytes("UTF-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			return null;
	 }
	
	@Override
	public String encryptAESFile() throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
		    cipher.init(Cipher.ENCRYPT_MODE, generateKey(secret), generateIv(IVS) );
		    FileInputStream inputStream = new FileInputStream("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Encrypted Outputs\\encryptedText.txt");
			File file1 = new File("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Encrypted Outputs\\encryptedAES.txt");
		        byte[] output = cipher.doFinal(inputStream.readAllBytes());
		        resultAES =  new String(Base64.getEncoder().encodeToString(output));
		        System.out.println( "AES result is "+resultAES);
		        FileWriter writer1 = new FileWriter(file1);
				writer1.write(resultAES);
				writer1.close();
		        inputStream.close();
		}catch( Exception e ) {
			System.out.println( e );
		}
		return resultAES;
	}
	
	
	@Override
	public String decryptAESFile() throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
		    cipher.init(Cipher.DECRYPT_MODE, generateKey(secret), generateIv(IVS) );
		    FileInputStream inputStream = new FileInputStream("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Encrypted Outputs\\encryptedAES.txt");
			File file1 = new File("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Decrypted Outputs\\decryptedAES.txt");
	        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(inputStream.readAllBytes()));
	        decryptAES =  new String(plainText,"UTF-8");
	        System.out.println( "AES decrypt is "+decryptAES);
	        FileWriter writer1 = new FileWriter(file1);
			writer1.write(decryptAES);
			writer1.close();
		    inputStream.close();
		}catch( Exception e ) {
			System.out.println( e );
		}
		return decryptAES;
	}
	
	@Override
	public String decryptRSAData() {
		File file1 = new File("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Decrypted Outputs\\decryptedText.txt");
		try {
			  FileInputStream inputStream = new FileInputStream("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Decrypted Outputs\\decryptedAES.txt");
			  Cipher cipher = Cipher.getInstance("RSA");
			  cipher.init(Cipher.DECRYPT_MODE,privatekey );		  
			  byte[] decrypt2 = cipher.doFinal(Base64.getDecoder().decode(inputStream.readAllBytes()));
			  decryptRSA = new String(decrypt2, "UTF-8");
			  System.out.println("RSA Decrypt is "+decryptRSA);	
			  FileWriter writer1 = new FileWriter(file1);
			  writer1.write(decryptRSA);
			  writer1.close();
			  inputStream.close();
		}catch( Exception e ) {
			e.printStackTrace();
		}
		return decryptRSA;
	}
	
	
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	@Override
	public String firstEncryption( String message )  {
		String resultRSA="";
		try {
//			    Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
//		        cipher.init(Cipher.ENCRYPT_MODE, generateKey(secret), generateIv(IVS) );
//		        byte[] output = cipher.doFinal(message.getBytes());
//		        resultAES =  new String(Base64.getEncoder().encodeToString(output));
//		        System.out.println( "AES result is "+resultAES);
				Cipher cipher1 = Cipher.getInstance("RSA");
				cipher1.init(Cipher.ENCRYPT_MODE,publickey );
				System.out.println("*****" +publickey.toString());
				resultRSA =  Base64.getMimeEncoder().encodeToString(cipher1.doFinal(message.getBytes()));
				System.out.println( "RSA result is "+resultRSA);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return resultAES;
		return resultRSA;
	}
	
	@Override
	public String hybridEncryption( String message ) {
		String resultAES ="";
		try {
//			 Cipher cipher1 = Cipher.getInstance("RSA");
//			 cipher1.init(Cipher.ENCRYPT_MODE,publickey );
//			 byte[] encrypt1 = cipher1.doFinal(message.getBytes());		 
//			 resultRSA =  new String(Base64.getEncoder().encodeToString(encrypt1));			 
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
		    cipher.init(Cipher.ENCRYPT_MODE, generateKey(secret), generateIv(IVS) );
		    resultAES =Base64.getMimeEncoder().encodeToString(cipher.doFinal(message.getBytes()));  
		    System.out.println( "AES result is "+resultAES);
		    return resultAES;
		}catch ( Exception e ) {
			System.out.println( e );
			return resultAES;
		}
	    //return resultAES;
		//return resultRSA;
	}
	
	@Override
	public String firstDecryption( String message ) {
		String decryptAES=""; 
		try {
			message=message.replaceAll(" ", "+");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
		    cipher.init(Cipher.DECRYPT_MODE, generateKey(secret), generateIv(IVS) );
		    byte[] decrypt2 = cipher.doFinal(Base64.getDecoder().decode(message.trim().getBytes()));
		    decryptAES = new String( decrypt2, "UTF-8");
	        System.out.println( "AES decrypt is "+decryptAES);
			
//			  Cipher cipher = Cipher.getInstance("RSA");
//			  cipher.init(Cipher.DECRYPT_MODE,privatekey );		
//			  System.out.println ( privatekey );
//			  byte[] decrypt2 = cipher.doFinal(Base64.getMimeEncoder().encode(message.trim().getBytes()));
//			  System.out.println( decrypt2 +" is decrypt2");
//			  decryptRSA = new String(decrypt2, "UTF-8");
//			  System.out.println("RSA Decrypt is "+decryptRSA);	
		}catch( Exception e ) {
			System.out.println( e +"error in AES decryption" );
		}
		return decryptAES;
		//return decryptRSA;
	}
	
    @Override
    public String hybridDecryption( String message ) {
    	try {
    		message=message.replaceAll(" ", "+");
 //   		  System.out.println ( " message is "+message);
			  Cipher cipher = Cipher.getInstance("RSA");
			  cipher.init(Cipher.DECRYPT_MODE,privatekey );		
			  System.out.println("*****" +privatekey.toString());
//			  System.out.println ( privatekey );
			  byte[] decrypt2 = cipher.doFinal(Base64.getDecoder().decode(message.getBytes(StandardCharsets.UTF_8)));
//			  System.out.println( decrypt2 +" is decrypt2");
			  decryptRSA = new String(decrypt2, "UTF-8");
			  System.out.println("RSA Decrypt is "+decryptRSA);	
    		
//				Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); 
//			    cipher.init(Cipher.DECRYPT_MODE, generateKey(secret), generateIv(IVS) );
//		        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode( message.getBytes()));
//		        decryptAES =  new String(plainText,"UTF-8");
//		        System.out.println( "AES decrypt is "+decryptAES);
    	}catch ( Exception e ) {
    		System.out.println( e + "error in RSA decryption");
    	}
    	return decryptRSA;
    	//return decryptAES;
    }
    
    @Override
	public String encryptAESFile(byte[] bytesArray, String fileType ) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException{
		//File file = new File("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\Outputs\\Encrypted Outputs\\encryptedPDF.txt");
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //AES/CBC/PKCS5Padding
		    cipher.init(Cipher.ENCRYPT_MODE, generateKey(secret), generateIv(IVS) );
		    //FileInputStream inputStream = new FileInputStream("C:\\Users\\SNEHA SAMUEL\\Documents\\workspace-spring-tool-suite-4-4.20.0.RELEASE\\firstStage\\src\\main\\resources\\sample.pdf");
		    //byte[] temp = inputStream.readAllBytes();
		    //System.out.println(" and temp in encrypton is "+temp);
		    byte[] encrypt = cipher.doFinal(bytesArray);	
			String result =  new String(Base64.getEncoder().encodeToString(encrypt));
			System.out.println( "encryption of pdf is " +result); //jpg -> bytes ->string
			File file = new File("C:\\Users\\EncryptedOutput.txt");
			FileWriter writer1 = new FileWriter(file);
			writer1.write(result);
			writer1.close();
			//inputStream.close();
			if( fileType.equalsIgnoreCase("application/pdf")) {
				System.out.println( " its pdf ");
				decryptAESFile( fileType );
			} 
			else if( fileType.equalsIgnoreCase("text/csv") ) {
				System.out.println( " its csv ");
				decryptAESFile( fileType );
			}
			else if( fileType.equalsIgnoreCase("image/jpeg") ) {
				System.out.println( " its jpeg ");
				decryptAESFile( fileType );
			}
			else if( fileType.equalsIgnoreCase("video/mp4")) {
				System.out.println( " its mp4 ");
				decryptAESFile( fileType );
			}
			else {
				System.out.println( " none of it matched"); //? throw some alert or flash when this is matched
				System.out.println(fileType);
			}
		}catch( Exception e ) {
			System.out.println( e );
		}
		return "ok";
	}
    
//    @Override
	public String decryptAESFile( String fileType ) throws IOException, NoSuchPaddingException,
    NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
    BadPaddingException, IllegalBlockSizeException {
    	try {
    		File file1 = new File("C:\\Users\\DecryptedOutput.pdf");//? what if this position is not available
    		File file2 = new File("C:\\Users\\DecryptedOutput.csv");
    		File file3 = new File("C:\\Users\\DecryptedOutput.jpg");
    		File file4 = new File("C:\\Users\\DecryptedOutput.mp4");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding"); //AES/CBC/PKCS5Padding
		    cipher.init(Cipher.DECRYPT_MODE, generateKey(secret), generateIv(IVS) );
		    FileInputStream inputStream = new FileInputStream("C:\\Users\\EncryptedOutput.txt");
		    byte[] temp = inputStream.readAllBytes();
		    System.out.println( " and temp in decryption is " +temp);
	        byte[] plainText = cipher.doFinal(Base64.getDecoder().decode(temp));
	        System.out.println("plainText is "+plainText);
	        //String decryptAES =  new String(plainText,"UTF-8");
	        //System.out.println( "result is "+decryptAES);
	        if( fileType.equalsIgnoreCase("application/pdf")) {
				System.out.println( " its pdf ");
		        FileOutputStream output = new FileOutputStream(file1);
		        output.write(plainText);
			    inputStream.close();
			} 
			else if( fileType.equalsIgnoreCase("text/csv") ) {
				System.out.println( " its csv ");
		        FileOutputStream output = new FileOutputStream(file2);
		        output.write(plainText);
			    inputStream.close();
			}
			else if( fileType.equalsIgnoreCase("image/jpeg") ) {
				System.out.println( " its jpeg ");
		        FileOutputStream output = new FileOutputStream(file3);
		        output.write(plainText);
			    inputStream.close();
			}
			else if( fileType.equalsIgnoreCase("video/mp4") ) {
				System.out.println( " its mp4 ");
		        FileOutputStream output = new FileOutputStream(file4);
		        output.write(plainText);
			    inputStream.close();
			}
			else {
				System.out.println( " none of it matched. So no decryption");
				System.out.println(fileType);
			}
		}catch( Exception e ) {
			System.out.println( e );
		}
		return "ok";
    }
    
    
    
}
