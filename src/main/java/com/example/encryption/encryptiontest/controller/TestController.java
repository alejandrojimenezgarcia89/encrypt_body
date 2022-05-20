package com.example.encryption.encryptiontest.controller;

import javax.websocket.server.PathParam;

import com.example.encryption.encryptiontest.model.RequestBodyTest;
import com.example.encryption.encryptiontest.utils.annotations.Decrypt;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

	@GetMapping("/{employeeId}")
	public ResponseEntity<?> testGetPathVariable(@PathVariable @Decrypt Long employeeId ){
		System.out.println("executing TestController.testGetPath() - "+employeeId);
		return ResponseEntity.ok(employeeId);
	}
	@GetMapping()
	public ResponseEntity<?> testGetRequestParam(@RequestParam @Decrypt Long employeeId ){
		System.out.println("executing TestController.testGetPath() - "+employeeId);
		return ResponseEntity.ok(employeeId);
	}
	@PostMapping
	public ResponseEntity<?> testPost(@RequestBody @Decrypt RequestBodyTest test ){
		System.out.println("executing TestController.testPost() - "+test);
		return ResponseEntity.ok(test);
	}
}
