package org.vm93.beu2w1.config;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.AttributeConverter;

@Configuration
public class accessCodeConverter implements AttributeConverter<String, String> {


	private static final String ALGORITH = "AES/ECB/PKCS5Padding";
	private static final byte[] KEY = "MySuperSecretKey".getBytes();

	@Override
	public String convertToDatabaseColumn(String attribute) {
		if (attribute == null) {
			return "ENC";
		}
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITH);
			c.init(Cipher.ENCRYPT_MODE, key);
			return Base64.getEncoder().encodeToString((c.doFinal((attribute.getBytes()))));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public String convertToEntityAttribute(String dbData) {
		if (dbData == null) {
			return "DEC";
		}
		Key key = new SecretKeySpec(KEY, "AES");
		try {
			Cipher c = Cipher.getInstance(ALGORITH);
			c.init(Cipher.DECRYPT_MODE, key);
			return new String(c.doFinal(Base64.getDecoder().decode(dbData)));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
