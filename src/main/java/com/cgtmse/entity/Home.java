package com.cgtmse.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Home {
	
	@Id
	private String plainText;
	private String cryptoText;
	private String plainTextD;
    private String cryptoTextD;
    private String fileInput;
    
	public Home() {
		super();
	}

	public Home(String plainText, String cryptoText,String plainTextD,String cryptoTextD, String fileInput  ) {
		super();
		this.plainText = plainText;
		this.cryptoText = cryptoText;
		this.plainTextD = plainTextD;
		this.cryptoTextD = cryptoTextD;
		this.fileInput = fileInput;
	}

	public String getPlainTextD() {
		return plainTextD;
	}

	public void setPlainTextD(String plainTextD) {
		this.plainTextD = plainTextD;
	}

	public String getCryptoTextD() {
		return cryptoTextD;
	}

	public void setCryptoTextD(String cryptoTextD) {
		this.cryptoTextD = cryptoTextD;
	}

	public String getPlainText() {
		return plainText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public String getCryptoText() {
		return cryptoText;
	}

	public void setCryptoText(String cryptoText) {
		this.cryptoText = cryptoText;
	}

	public String getFileInput() {
		return fileInput;
	}

	public void setFileInput(String fileInput) {
		this.fileInput = fileInput;
	}
	

}
