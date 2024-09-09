package ai.rnt.customerFeedback.util;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import ai.rnt.customerFeedback.controller.LoginController;

@Component
public class RSAToJwtDecoder {

//	private RSAToJwtDecoder() {
//	}
//
//	@Value("${rsa.rsakey}")
//	private String rsa;
//
//	private static String aesSecretKey;
//	private static String aesAlgorithm;
//
//	@Value("${aes.secret.key}")
//	public void setAlgorithmSecretKey(String aesSecretKey) {
//		RSAToJwtDecoder.aesSecretKey = aesSecretKey;
//	}
//
//	@Value("${aes.algorithm}")
//	public void setAlgorithm(String aesAlgorithm) {
//		RSAToJwtDecoder.aesAlgorithm = aesAlgorithm;
//	}
//
//	public String rsaToJwtDecoder(String inputString) throws InvalidKeyException, NoSuchAlgorithmException,
//			NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
//		PrivateKey privateKey = LoginController.keystore.get(inputString);
//		Cipher decryptionCipher = Cipher.getInstance(rsa);
//		decryptionCipher.init(Cipher.DECRYPT_MODE, privateKey);
//		return new String(decryptionCipher.doFinal(Base64.getDecoder().decode(inputString)));
//
//	}
//
//	public String decryptTokan(String value) throws Exception {
//		byte[] encryptedValue = Base64.getDecoder().decode(value);
//		SecretKeySpec secretKey = new SecretKeySpec(aesSecretKey.getBytes(StandardCharsets.UTF_8), aesAlgorithm);
//		Cipher cipher = Cipher.getInstance(aesAlgorithm);
//		cipher.init(Cipher.DECRYPT_MODE, secretKey);
//		byte[] decryptedBytes = cipher.doFinal(encryptedValue);
//		return new String(decryptedBytes, StandardCharsets.UTF_8);
//	}
}
