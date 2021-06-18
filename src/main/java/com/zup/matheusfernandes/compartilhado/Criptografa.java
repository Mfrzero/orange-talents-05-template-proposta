package com.zup.matheusfernandes.compartilhado;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;

public class Criptografa {
	
	private static String senha = "senha";
	private static String salt = "H5U3G43U5B3I7DTG3";
	
	@SuppressWarnings("deprecation")
	private static TextEncryptor textEncryptor = Encryptors.queryableText(senha, salt);
	
	public static String encrypt(String string) {
		return textEncryptor.encrypt(string);
	}
	public String deencrypt(String string) {
		return textEncryptor.decrypt(string);
	}
			

}
