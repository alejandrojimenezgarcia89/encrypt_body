package com.example.encryption.encryptiontest.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import com.example.encryption.encryptiontest.model.RequestBodyDTO;
import com.example.encryption.encryptiontest.model.RequestBodyDTOEncryptedFields;
import com.example.encryption.encryptiontest.model.ResponseBodyDTO;
import com.example.encryption.encryptiontest.utils.annotations.Decrypt;
import com.example.encryption.encryptiontest.utils.annotations.Encrypt;
import com.example.encryption.encryptiontest.utils.mappers.BodyMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
class TestController {

	private final BodyMapper mapper;

	@GetMapping("/{employeeId}")
	public ResponseEntity<?> testGetPathVariable(@PathVariable @Decrypt Long employeeId ){
		System.out.println("executing TestController.testGetPath() - {}"+employeeId);
		return ResponseEntity.ok(employeeId);
	}
	@GetMapping()
	public ResponseEntity<?> testGetRequestParam(@RequestParam @Decrypt Long employeeId ){
		System.out.println("executing TestController.testGetPath() - {}"+employeeId);
		return ResponseEntity.ok(employeeId);
	}
	
	// @PostMapping
	// @Encrypt
	// public ResponseEntity<ResponseBodyDTO> testPost(@RequestBody @Decrypt RequestBodyDTO test ){
	// 	System.out.println("executing TestController.testPost() - {}",test);
	// 	return ResponseEntity.ok(test);
	// }
	@PostMapping("/encrypted_body")
	@Encrypt
	public ResponseEntity<ResponseBodyDTO> testPostEncryptedBody(@RequestBody @Decrypt RequestBodyDTO body ){
		System.out.println("executing TestController.testPostEncryptedBody() - {}"+mapper.requestToResponse(body).getEmail());
		System.out.println("executing TestController.testPostEncryptedBody() - {}"+mapper.requestToResponse(body).getPublicData());
		return ResponseEntity.ok(mapper.requestToResponse(body));
	}
	@PostMapping("/encrypted_fields")
	@Encrypt
	public ResponseEntity<ResponseBodyDTO> testPostEncryptedFields(@RequestBody @Decrypt RequestBodyDTOEncryptedFields body ) throws UnsupportedEncodingException{
		System.out.println("executing TestController.testPostEncryptedFields() - {}"+mapper.requestEncryptedFieldsToResponse(body).getEmail());
		// System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getEmail().getBytes(),StandardCharsets.ISO_8859_1));
		// System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getEmail().getBytes(),StandardCharsets.US_ASCII));
		System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getEmail().getBytes(),StandardCharsets.UTF_8));
		// System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getEmail().getBytes(),"Windows-1252"));
		System.out.println("executing TestController.testPostEncryptedFields() - {}"+mapper.requestEncryptedFieldsToResponse(body).getPublicData());
		// System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getPublicData().getBytes()));
		System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getPublicData().getBytes(),StandardCharsets.UTF_8));
		// System.out.println("executing TestController.testPostEncryptedFields() - {}"+new String(mapper.requestEncryptedFieldsToResponse(body).getPublicData().getBytes(),"Windows-1252"));
		return ResponseEntity.ok(mapper.requestEncryptedFieldsToResponse(body));
	}
	
}
