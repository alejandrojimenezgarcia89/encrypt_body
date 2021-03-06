package com.example.encryption.encryptiontest;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.example.encryption.encryptiontest.client.TestClient;
import com.example.encryption.encryptiontest.model.RequestBodyDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.stereotype.Component;

@EnableFeignClients
@SpringBootApplication
public class EncryptionTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(EncryptionTestApplication.class, args);
	}

}

@Component
class CommandLineAppStartupRunner implements CommandLineRunner {
	@Autowired
	private TestClient testClient;
	@Autowired
	private IEncryptionService aesService;

	@Override
	public void run(String... args) throws Exception {
        System.out.println(Charset.defaultCharset().displayName());

		RequestBodyDTO requestBody=RequestBodyDTO.builder().email("aljimenezgá.ext@acciona.com").employeeId(30007206L).publicData("Dato público").build();

		String encrypted = aesService.encrypt(new String("aljimenezgá.ext@acciona.com".getBytes()));
		System.out.println("aljimenezgá.ext@acciona.com encrypted " + encrypted);
		byte[] decrypted =aesService.decrypt(encrypted);
		System.out.println("aljimenezgá.ext@acciona.com decrypted " + new String(decrypted));
		String encrypted2 = aesService.encrypt(new String(decrypted));
		System.out.println("aljimenezgá.ext@acciona.com encrypted2 " + new String(encrypted2));
		byte[] decrypted2 =aesService.decrypt(encrypted2);
		System.out.println("aljimenezgá.ext@acciona.com decrypted2" + new String(decrypted2));
// 		String key = "peekaboopeekaboo";
// 		SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
//     IvParameterSpec ivParameterSpec = new IvParameterSpec("0123456789012345".getBytes());
//     String algorithm = "AES/CBC/PKCS5Padding";
// 	String testEncoded = aesService
// 				.encrypt(requestBody);
// System.out.println("testEncoded " + testEncoded);

String encryptedBody = aesService.encrypt(requestBody);
System.out.println(" encryptedBody " + encryptedBody);
byte[] decryptedBody =aesService.decrypt(encryptedBody);
System.out.println(" decryptedBody " + new String(decryptedBody));
String encryptedBody2 = aesService.encrypt(new String(decryptedBody));
System.out.println(" encryptedBody2 " + new String(encryptedBody2));
byte[] decryptedBody2 =aesService.decrypt(encryptedBody2);
System.out.println(" decryptedBody2" + new String(decryptedBody2));

String response = "utUqnHSDc8WrG4e03QPXmIGXmzdyTD1z89cZhlke1vDVJizKm2kYNLlnpp3vdAUow1vKX0fYHPYqLRtDhCnR2WrZK3UeviRtHMxb5KkfOPhlQSVm/JXkH4TULTRTQ8iJ";
byte[] responseDecrypted =aesService.decrypt(response);
System.out.println(" responseDecrypted" + new String(responseDecrypted));
		// SealedObject so=AESUtils.encryptObject(algorithm, requestBody, secretKeySpec, ivParameterSpec);
		// System.out.println("so "+so.getObject(secretKeySpec));
		// System.out.println("so "+so.toString());
		// Serializable rb=AESUtils.decryptObject(algorithm, so, secretKeySpec, ivParameterSpec);
		// System.out.println("rb "+rb);
		// SealedObject so2=AESUtils.encryptObject(algorithm, rb, secretKeySpec, ivParameterSpec);
		// System.out.println("so "+so2.getObject(secretKeySpec));
		// System.out.println("so "+so2.toString());
		// Serializable rb2=AESUtils.decryptObject(algorithm, so2, secretKeySpec, ivParameterSpec);
		// System.out.println("rb "+rb2);

		// String test = new String("áíóñòùèüas");
		// System.out.println("test "+test);
		// System.out.println("test "+new String(test.getBytes()));
		// System.out.println("test "+new String(new String(test.getBytes()).getBytes()));
		// System.out.println("aljimenezga.ext@acciona.com " + aesService.encrypt("aljimenezga.ext@acciona.com"));
		// testClient.testPostEncryptedFields(testEncoded);
		// testClient.testPostEncryptedResponse(testEncoded);

	}
}
