package pl.ksb.agape.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoTools {

	public static CryptoTools getInstance() {
		return new CryptoTools();
	}

	private String byteArrayToHexString(byte[] b) {
		String result = "";
		for (int i = 0; i < b.length; i++) {
			result += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
		}
		return result;
	}

	private byte[] hash(String password) throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		byte[] passBytes = password.getBytes();
		byte[] passHash = sha256.digest(passBytes);
		return passHash;
	}

	public String passwordHash(String password) {
		try {
			return byteArrayToHexString(hash(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;

	}

}
