package com.example.encryption.encryptiontest.client;

import com.example.encryption.encryptiontest.model.RequestBodyTest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="testClient",url = "http://localhost:8080")
public interface TestClient {
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public RequestBodyTest testPost(String encodedBoyd);
}
