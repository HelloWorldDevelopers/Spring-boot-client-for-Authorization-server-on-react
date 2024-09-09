package ai.rnt.customerFeedback.util;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TokanEncrypterDecrypter {

	private static String aesAlgorithm;
	private static String aesSecretKey;
	private static String cipherInstance;

	@Value("${aes.secret.key}")
	public void setAlgorithmSecretKey(String aesSecretKey) {
		TokanEncrypterDecrypter.aesSecretKey = aesSecretKey;
	}

	@Value("${aes.algorithm}")
	public void setAlgorithm(String aesAlgorithm) {
		TokanEncrypterDecrypter.aesAlgorithm = aesAlgorithm;
	}

	@Value("${aes.transformation}")
	private String transformation;

	@Value("${cipher.get.instance}")
	public void setCipherInstance(String cipherInstance) {
		TokanEncrypterDecrypter.cipherInstance = cipherInstance;
	}

	public String encryptTokan(String token) throws Exception {
		SecretKeySpec secretKey = new SecretKeySpec(aesSecretKey.getBytes(StandardCharsets.UTF_8), aesAlgorithm);
		Cipher cipher = Cipher.getInstance(aesAlgorithm);
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedBytes = cipher.doFinal(token.getBytes(StandardCharsets.UTF_8));
		return Base64.getEncoder().encodeToString(encryptedBytes);
	}

	public String decryptTokan(String value) throws Exception {
		byte[] encryptedValue = Base64.getDecoder().decode(value);
		SecretKeySpec secretKey = new SecretKeySpec(aesSecretKey.getBytes(StandardCharsets.UTF_8), aesAlgorithm);
		Cipher cipher = Cipher.getInstance(aesAlgorithm);
		cipher.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedBytes = cipher.doFinal(encryptedValue);
		return new String(decryptedBytes, StandardCharsets.UTF_8);
	}

	public String decrypt(String encryptedText) {
		try {
			byte[] keyBytes = aesSecretKey.getBytes(StandardCharsets.UTF_8);
			SecretKeySpec secretKey = new SecretKeySpec(keyBytes, "AES");
			byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
			byte[] ivBytes = new byte[16];
			System.arraycopy(keyBytes, 0, ivBytes, 0, 16);
			IvParameterSpec iv = new IvParameterSpec(ivBytes);
			Cipher cipher = Cipher.getInstance(cipherInstance);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			byte[] decryptedBytes = cipher.doFinal(encryptedBytes);
			return new String(decryptedBytes, "UTF-8");
		} catch (Exception e) {
			return null;
		}
	}

	public List<String> encryptRoleList(List<String> roleListFromJsonArray) {
		return roleListFromJsonArray.stream().map(role -> {
			try {
				return encryptTokan(role);
			} catch (Exception e) {
				return null;
			}
		}).collect(Collectors.toList());

	}

}
