package org.thethingsnetwork.util.security;

import org.junit.Assert;
import org.junit.Test;

public class CryptoTest {

	// https://github.com/TheThingsNetwork/croft/blob/master/server.go
	public static final byte [] SEMTECH_DEFAULT_KEY = new byte [] {
			0x2B, 0x7E, 0x15, 0x16, 0x28, (byte) 0xAE, (byte) 0xD2, (byte) 0xA6, 
			(byte) 0xAB, (byte) 0xF7, 0x15, (byte) 0x88, 0x09, (byte) 0xCF, 0x4F, 0x3C
	};

	public static final byte [] IV = new byte [] {
			0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0,
			0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0, 0x0
	};
	
	// example data from a ttn message
	public static final String TEXT_RAW   = "QAQBSFoAhAABzUi9WfIrdF1DhDu2eLVk4A==";
	public static final String TEXT       = "MDAwNjAwMUMwMDNC";
	public static final String TEXT_PLAIN = "0006001C003B"; 
	
	@Test
    public void testDecoder() throws Exception {
    	String decryptedText = Crypto.decrypt(TEXT_RAW, SEMTECH_DEFAULT_KEY, IV);
    	String plainText = Crypto.toPlainText(decryptedText);
    	
    	Assert.assertEquals("decrypted text does not match", TEXT, decryptedText);
    	Assert.assertEquals("base64 decoded text does not match", TEXT_PLAIN, plainText);
    }
}
