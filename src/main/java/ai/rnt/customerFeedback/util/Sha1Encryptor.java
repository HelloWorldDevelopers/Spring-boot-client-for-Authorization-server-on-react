package ai.rnt.customerFeedback.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import ai.rnt.customerFeedback.exception.CRMException;

@Service
public class Sha1Encryptor {
	public Sha1Encryptor() {
	}

	@Value("${rsa.sha1}")
	private String sha1 = "SHA-1";

	public void setSha1(String sha1) {
		this.sha1 = sha1;
	}

	public String encryptThisString(String input) {
		try {
			MessageDigest md = MessageDigest.getInstance(sha1);
			byte[] messageDigest = md.digest(input.getBytes());
			BigInteger no = new BigInteger(1, messageDigest);
			StringBuilder hashtext = new StringBuilder(no.toString(16));
			while (hashtext.length() < 32) {
				hashtext = hashtext.append("0" + hashtext);
			}
			return hashtext.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new CRMException(e);
		}
	}

}
