package com.example.encryption.encryptiontest.client;

import com.example.encryption.encryptiontest.model.RequestBodyTest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="testClient",url = "http://localhost:8080")
public interface TestClient {
	
	@PostMapping
	public RequestBodyTest testPost(@RequestBody String encodedBoyd);
}
