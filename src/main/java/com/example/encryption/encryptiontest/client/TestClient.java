package com.example.encryption.encryptiontest.client;

import com.example.encryption.encryptiontest.model.RequestBodyDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="testClient",url = "http://localhost:8080")
public interface TestClient {
	
	// @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	// public RequestBodyDTO testPostEncryptedFields(String encryptedBody);

	@PostMapping(path = "/encrypted_body",consumes = MediaType.APPLICATION_JSON_VALUE)
	public Object testPostEncryptedResponse(String encryptedBody);
}
