package com.skilldistillery.threadedcc;

import java.util.concurrent.Callable;

public class CaesarCypherDecrypter implements Callable<String> {
	private String decryptedMessage;
	private String originalMessage;
	private int shift;
	

	@Override
	public String call() {
		return decryptText(originalMessage);
	}

	CaesarCypherDecrypter() {
	}

	public CaesarCypherDecrypter(String message, int shift) {
		this.shift = shift;
		this.originalMessage = message;
		decryptedMessage = decryptText(message);
	}

	public String decryptText(String text) {
		this.originalMessage = text;
		char[] decodedText = new char[text.length()];
		for (int i = 0; i < text.length(); i++) {
			decodedText[i] = decryptCharacter(text.charAt(i));
		}
		this.decryptedMessage = new String(decodedText);
		return this.decryptedMessage;
	}

	public char decryptCharacter(char originalChar) {
		int decodedChar;
		if (originalChar >= 'A' && originalChar <= 'Z') {
			decodedChar = originalChar - shift;
			if (decodedChar < 'A') {
				decodedChar = decodedChar + 26;
			}
		} else if (originalChar >= 'a' && originalChar <= 'z') {
			decodedChar = originalChar - shift;
			if (decodedChar < 'a') {
				decodedChar = decodedChar + 26;
			}
		} else {
			decodedChar = originalChar;
		}
		return (char) decodedChar;
	}

	String getOriginalMessage() {
		return originalMessage;
	}

	void setOriginalMessage(String originalMessage) {
		this.originalMessage = originalMessage;
	}

	int getShift() {
		return shift;
	}

	void setShift(int shift) {
		this.shift = shift;
	}

	public String getDecryptedMessage() {
		return decryptedMessage;
	}
}
