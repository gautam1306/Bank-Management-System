package com.bank.customer;
import org.apache.commons.codec.binary.Base64;
public class PasswordEncoder {
	public  String  encode(String password) {
		String encrypted;
		encrypted =Base64.encodeBase64String(password.getBytes());		
		return encrypted;
	}
}
