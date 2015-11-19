package pl.ksb.agape.util;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CryptoToolsTest {

	private String password;

	@Before
	public void generateRandomPassword() {
		password = CryptoTools.getInstance().randomPassword();

	}

	@Test
	public void createCorrectHashFromPassword() {
		Assert.assertEquals(CryptoTools.getInstance().passwordHash("SuPerPower#PaSS29"),
				"d911a304f4368ca7c597f4b13a05a591806c972e639207f9cbee750457079480");

	}

	@Test
	public void isRandomPasswordNotNull() {
		Assert.assertNotNull(password);
	}

	@Test
	public void hasRandomPasswordValidLength() {
		Assert.assertTrue(password.length() >= 8);
	}

	@Test
	public void containsRandomPasswordNumeric() {

		Assert.assertTrue(password.matches(".*\\D.*"));
	}

}
