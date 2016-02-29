# TheThingsNetwork Decoder for Java

Minimalistic Java implementation to decrypt/decode the data provided by TheThingsNetwork in MQTT messages.

The goal of the excercise is to decrypt the content of *data_raw* as shown below to create *data* and to decode (base64) this into *data_plain* as shown in the example below.

```
{
  "snr": 8.5,
  "data_plain": "0006001C003B",
  "gateway_eui": "FFFEB827EB296CE9",
  "node_eui": "5A480105",
  "data_raw": "QAQBSFoAhAABzUi9WfIrdF1DhDu2eLVk4A==",
  "data": "MDAwNjAwMUMwMDNC",
  "rssi": -105,
  "datarate": "SF7BW125",
  "time": "2016-02-24T19:26:48.478Z",
  "frequency": 867.9
}  
```

Decoding and decryption is performed in class ```Crypto```. 
The usage of this class is demonstrated in test class ```CryptoTest```, see the snipped below:

```java
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
```

