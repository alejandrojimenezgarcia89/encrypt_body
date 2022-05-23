// package com.example.encryption.encryptiontest.service;

// import java.util.Base64;

// import com.example.encryption.encryptiontest.api.IEncryptionService;
// import com.fasterxml.jackson.core.JsonProcessingException;
// import com.fasterxml.jackson.databind.ObjectMapper;

// import org.springframework.stereotype.Service;

// @Service
// public class Base64Service implements IEncryptionService {

//     @Override
//     public String encrypt(Object body) {
//         ObjectMapper mapper = new ObjectMapper();
//         String encryptedBody = null;
//         try {
//             encryptedBody = Base64.getEncoder().encodeToString(mapper.writeValueAsString(body).getBytes());
//         } catch (JsonProcessingException e) {
//             // TODO Auto-generated catch block
//             e.printStackTrace();
//         } 
//         return encryptedBody;
//     }

//     @Override
//     public byte[] decrypt(byte[] body) {
//         return Base64.getDecoder().decode(body);
//     }
// }
