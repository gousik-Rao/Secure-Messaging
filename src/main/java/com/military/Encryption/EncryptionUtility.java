package com.military.Encryption;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class EncryptionUtility {

//	To encrypt a message
	public static String encrypt(String messageContent) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			
//			Ensuring the key exists in the file using KeyManager methods
			KeyManager.ensureKeyExists();
			SecretKey getKey = KeyManager.loadKeyFromFile(); // Loading key from the file
			
//			Getting the secret key and initializing it for encryption
			cipher.init(Cipher.ENCRYPT_MODE, getKey);
			return Base64.getEncoder().encodeToString(cipher.doFinal(messageContent.getBytes()));
		}catch(Exception e) {
			e.printStackTrace();	
		}
		return null;
	}
	
//	To decrypt a message
	public static String decrypt(String encryptedMessage) {
		try {
			Cipher cipher = Cipher.getInstance("AES");
			
//			Ensuring the key exists in the file
			KeyManager.ensureKeyExists();
			SecretKey getKey = KeyManager.loadKeyFromFile();
			
//			Getting secret key and initializing it for decryption
			cipher.init(Cipher.DECRYPT_MODE, getKey);
			return new String(cipher.doFinal(Base64.getDecoder().decode(encryptedMessage)));
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static SecretKey generateKey() throws NoSuchAlgorithmException{
		return KeyGenerator.getInstance("AES").generateKey();
	}
}
