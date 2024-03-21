package com.cgtmse.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table( name="FYP_DATA")
public class YourActivity {
	
	@Column( name = "email")
	private String email;
	@Column( name = "plaintextmessage")
	private String plainTextMessage;
	@Id
	@Column ( name = "encryptedmessage")
	private String encryptedMessage;
	@Column( name = "decryptedmessage")
	private String decryptedMessage;
	
	public YourActivity(String email, String plainTextMessage, String encryptedMessage, String decryptedMessage) {
		super();
		this.email = email;
		this.plainTextMessage = plainTextMessage;
		this.encryptedMessage = encryptedMessage;
		this.decryptedMessage = decryptedMessage;
	}

	public YourActivity() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPlainTextMessage() {
		return plainTextMessage;
	}

	public void setPlainTextMessage(String plainTextMessage) {
		this.plainTextMessage = plainTextMessage;
	}

	public String getEncryptedMessage() {
		return encryptedMessage;
	}

	public void setEncryptedMessage(String encryptedMessage) {
		this.encryptedMessage = encryptedMessage;
	}

	public String getDecryptedMessage() {
		return decryptedMessage;
	}

	public void setDecryptedMessage(String decryptedMessage) {
		this.decryptedMessage = decryptedMessage;
	}
	
	
	
//	public YourActivity() {
//	
//	}
//
//	public YourActivity(String email, String singleEncryptionContent, String hybridEncryptionContent,
//			String singleDecryptionContent, String hybridDecryptionContent) {
//
//		this.email = email;
//		this.singleEncryptionContent = singleEncryptionContent;
//		this.hybridEncryptionContent = hybridEncryptionContent;
//		this.singleDecryptionContent = singleDecryptionContent;
//		this.hybridDecryptionContent = hybridDecryptionContent;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getSingleEncryptionContent() {
//		return singleEncryptionContent;
//	}
//
//	public void setSingleEncryptionContent(String singleEncryptionContent) {
//		this.singleEncryptionContent = singleEncryptionContent;
//	}
//
//	public String getHybridEncryptionContent() {
//		return hybridEncryptionContent;
//	}
//
//	public void setHybridEncryptionContent(String hybridEncryptionContent) {
//		this.hybridEncryptionContent = hybridEncryptionContent;
//	}
//
//	public String getSingleDecryptionContent() {
//		return singleDecryptionContent;
//	}
//
//	public void setSingleDecryptionContent(String singleDecryptionContent) {
//		this.singleDecryptionContent = singleDecryptionContent;
//	}
//
//	public String getHybridDecryptionContent() {
//		return hybridDecryptionContent;
//	}
//
//	public void setHybridDecryptionContent(String hybridDecryptionContent) {
//		this.hybridDecryptionContent = hybridDecryptionContent;
//	}

}
