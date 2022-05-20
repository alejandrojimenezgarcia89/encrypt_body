package com.example.encryption.encryptiontest;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.example.encryption.encryptiontest.client.TestClient;
import com.example.encryption.encryptiontest.model.RequestBodyTest;
import com.google.gson.Gson;

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
		// String testEncoded = Base64.getEncoder().encodeToString(new Gson()
		// 		.toJson(RequestBodyTest.builder().email("aljimenezga.ext@acciona.com").employeeId(30007206L).build())
		// 		.toString().getBytes());
		// System.out.println("testEncoded " + testEncoded);
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

		String testEncoded = aesService
				.encrypt(RequestBodyTest.builder().email("aljimenezga.ext@acciona.com").employeeId(30007206L).build());
		System.out.println("testEncoded " + testEncoded);
		testClient.testPost(testEncoded);

	}
}
