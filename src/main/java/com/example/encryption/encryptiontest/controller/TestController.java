package com.example.encryption.encryptiontest.controller;

import java.io.UnsupportedEncodingException;
import com.example.encryption.encryptiontest.model.RequestBodyDTO;
import com.example.encryption.encryptiontest.model.RequestBodyDTOEncryptedFields;
import com.example.encryption.encryptiontest.model.ResponseBodyDTOEntryptedFields;
import com.example.encryption.encryptiontest.utils.annotations.Decrypt;
import com.example.encryption.encryptiontest.utils.annotations.Encrypt;
import com.example.encryption.encryptiontest.utils.mappers.BodyMapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
class TestController {

	private final BodyMapper mapper;

	@PostMapping("/encrypted_body_and_encrypted_response")
	@Encrypt
	// request: utUqnHSDc8WrG4e03QPXmIGXmzdyTD1z89cZhlke1vDVJizKm2kYNLlnpp3vdAUogd/Unb8IxUo85VUXuAv/f4bdoeoyjRdy/roiQW+smSPG1KPhIGz3ojRIVKsyp3ak
	// descrypted: {"email":"aljimenezgá.ext@acciona.com","employeeId":30007206,"publicData":"Dato público"}
	public ResponseEntity<RequestBodyDTO> testPostEncryptedBodyAndEncryptedResponse(
			@RequestBody @Decrypt RequestBodyDTO body) {

		System.out.println("testPostEncryptedBodyAndEncryptedResponse - RequestBodyDTO(decrypted): " + body);
		//  RequestBodyDTO(decrypted): RequestBodyDTO(email=aljimenezgá.ext@acciona.com, employeeId=30007206, publicData=Dato público)

		return ResponseEntity.ok(body);
		// response: utUqnHSDc8WrG4e03QPXmIGXmzdyTD1z89cZhlke1vDVJizKm2kYNLlnpp3vdAUogd/Unb8IxUo85VUXuAv/f4bdoeoyjRdy/roiQW+smSPG1KPhIGz3ojRIVKsyp3ak
		// decrypted: {"email":"aljimenezgá.ext@acciona.com","employeeId":30007206,"publicData":"Datopúblico"}
	}

	@PostMapping("/encrypted_body_and_encrypted_fields_response")
	@Encrypt
	// request: utUqnHSDc8WrG4e03QPXmIGXmzdyTD1z89cZhlke1vDVJizKm2kYNLlnpp3vdAUogd/Unb8IxUo85VUXuAv/f4bdoeoyjRdy/roiQW+smSPG1KPhIGz3ojRIVKsyp3ak
	// descrypted: {"email":"aljimenezgá.ext@acciona.com","employeeId":30007206,"publicData":"Dato público"}
	public ResponseEntity<ResponseBodyDTOEntryptedFields> testPostEncryptedBodyAndEncryptedFieldsResponse(
			@RequestBody @Decrypt RequestBodyDTO body) {

		System.out.println("testPostEncryptedBodyAndEncryptedFieldsResponse - RequestBodyDTO(decrypted): " + body);
		//  RequestBodyDTO(decrypted): RequestBodyDTO(email=aljimenezgá.ext@acciona.com, employeeId=30007206, publicData=Dato público)

		return ResponseEntity.ok(mapper.requestToResponseEntryptedFields(body));
		// response:
		// {
		// "email": "xj2pBzI9R0+sDGs+6wT/sklqfhTpXH05ajp7DoPzqew=", //descrypted:"aljimenezgá.ext@acciona.com"
		// "employeeId": "8LLnHMv3yciF/ge7SLUmwQ==", 				//descrypted: 30007206
		// "publicData": "Dato público"
		// }
	}

	@PostMapping("/encrypted_fields_request_and_encrypted_response")
	@Encrypt
	// request:
	// {
	// "email": "xj2pBzI9R0+sDGs+6wT/sklqfhTpXH05ajp7DoPzqew=", //descrypted: "aljimenezgá.ext@acciona.com"
	// "employeeId": "8LLnHMv3yciF/ge7SLUmwQ==", 			    //descrypted: 30007206
	// "publicData": "Dato público"
	// }
	public ResponseEntity<RequestBodyDTO> testPostEncryptedFieldsRequestAndEncryptedResponse(
			@RequestBody @Decrypt RequestBodyDTOEncryptedFields body) throws UnsupportedEncodingException {

		System.out.println("testPostEncryptedFieldsRequestAndEncryptedResponse - RequestBodyDTOEncryptedFields(decrypted): "+ body);
		// RequestBodyDTOEncryptedFields(decrypted): RequestBodyDTOEncryptedFields(email=aljimenezgá.ext@acciona.com, employeeId=30007206, publicData=Dato público)

		return ResponseEntity.ok(mapper.requestEncryptedFieldsToRequestBodyDTO(body));
		// response: utUqnHSDc8WrG4e03QPXmIGXmzdyTD1z89cZhlke1vDVJizKm2kYNLlnpp3vdAUogd/Unb8IxUo85VUXuAv/f4bdoeoyjRdy/roiQW+smSPG1KPhIGz3ojRIVKsyp3ak
		// decrypted: {"email":"aljimenezgá.ext@acciona.com","employeeId":30007206,"publicData":"Datopúblico"}
	}

	@PostMapping("/encrypted_fields_request_and_encrypted_fields_response")
	@Encrypt
	// request:
	// {
	// "email": "xj2pBzI9R0+sDGs+6wT/sklqfhTpXH05ajp7DoPzqew=", //descrypted: "aljimenezgá.ext@acciona.com"
	// "employeeId": "8LLnHMv3yciF/ge7SLUmwQ==", 			    //descrypted: 30007206
	// "publicData": "Dato público"
	// }
	public ResponseEntity<ResponseBodyDTOEntryptedFields> testPostEncryptedFieldsRequestAndEncryptedFieldsResponse(
			@RequestBody @Decrypt RequestBodyDTOEncryptedFields body) throws UnsupportedEncodingException {

		System.out.println("testPostEncryptedFieldsRequestAndEncryptedFieldsResponse - RequestBodyDTOEncryptedFields(decrypted): "+ body);
		// RequestBodyDTOEncryptedFields(decrypted): RequestBodyDTOEncryptedFields(email=aljimenezgá.ext@acciona.com, employeeId=30007206, publicData=Dato público)

		return ResponseEntity.ok(mapper.requestEncryptedFieldsToResponseEntryptedFields(body));
		// response:
		// {
		// "email": "xj2pBzI9R0+sDGs+6wT/sklqfhTpXH05ajp7DoPzqew=", //descrypted:
		// "aljimenezgá.ext@acciona.com"
		// "employeeId": "8LLnHMv3yciF/ge7SLUmwQ==", //descrypted: 30007206
		// "publicData": "Dato público"
		// }
	}
}
