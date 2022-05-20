package com.example.encryption.encryptiontest.controller;

import com.example.encryption.encryptiontest.model.RequestBodyTest;
import com.example.encryption.encryptiontest.utils.annotations.Decrypt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {
	@GetMapping
	public ResponseEntity<?> testGet(@RequestBody @Decrypt RequestBodyTest test ){
		System.out.println("executing TestController.testGet() - "+test);
		return ResponseEntity.ok(test);
	}
	@PostMapping
	public ResponseEntity<?> testPost(@RequestBody @Decrypt RequestBodyTest test ){
		System.out.println("executing TestController.testPost() - "+test);
		return ResponseEntity.ok(test);
	}
}
