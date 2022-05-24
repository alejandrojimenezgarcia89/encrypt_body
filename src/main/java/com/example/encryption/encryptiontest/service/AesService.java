package com.example.encryption.encryptiontest.service;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AesService implements IEncryptionService {
    
    private static final String AES_ALGORITHM = "AES/CBC/PKCS5Padding"; 
    
    @Value("${encrypt.key}")
    private String key;
    @Value("${encrypt.iv}")
    private String iv;

    @Override
    public String encrypt(final Object body) {
        String encryptedBody = null;
        
        try {
            byte[] bodyBytes =  body instanceof String ? ((String) body).getBytes() : new ObjectMapper().writeValueAsBytes(body);
            
            byte[] cipherText = getCipher(Cipher.ENCRYPT_MODE).doFinal(bodyBytes);
            encryptedBody= new String(Base64.getEncoder().encode(cipherText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBody;
       
    }

    @Override
    public byte[] decrypt(final String body) {
        try {
            return getCipher(Cipher.DECRYPT_MODE).doFinal(Base64.getDecoder().decode(body.getBytes()));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new byte[] {};
        }
    }

    private Cipher getCipher(int model)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec,ivParameterSpec);
        return cipher;
    }
}