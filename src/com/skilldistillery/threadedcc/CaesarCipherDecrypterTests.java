package com.skilldistillery.threadedcc;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class CaesarCipherDecrypterTests {

	private CaesarCypherDecrypter ccd;

	@Before
	public void init() {
		ccd = new CaesarCypherDecrypter();
	}

	@Test
	public void testWordDecryption() {
		ccd.setShift(3);
		assertEquals("DOGGY", ccd.decryptText("GRJJB"));
	}

	@Test
	public void testPhraseDecryption() {
		String phrase = "OR FHER GB QEVAX LBHE BINYGVAR";
		String expected = "BE SURE TO DRINK YOUR OVALTINE";
		ccd.setShift(13);
		assertEquals(expected, ccd.decryptText(phrase));
	}

	@Test
	public void testPhraseDecryptionCaseSensitive() {
		String phrase = "Or fher gb qevax lbhe Binygvar";
		String expected = "Be sure to drink your Ovaltine";
		ccd.setShift(13);
		assertEquals(expected, ccd.decryptText(phrase));
	}

	@Test
	public void testPhraseDecryptionCaseSensitivePreserveNonLetters() {
		String phrase = "Or fher, gb  qevax lbhe Binygvar!";
		String expected = "Be sure, to  drink your Ovaltine!";
		ccd.setShift(13);
		assertEquals(expected, ccd.decryptText(phrase));
	}

	@Test
	public void testCharacterDecryption() {
		ccd.setShift(3);
		assertEquals('A', ccd.decryptCharacter('D'));
		assertEquals('Z', ccd.decryptCharacter('C'));
	}
}