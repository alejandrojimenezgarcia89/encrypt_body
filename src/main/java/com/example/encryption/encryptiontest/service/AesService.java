package com.example.encryption.encryptiontest.service;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AesService implements IEncryptionService {
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    @Value("${encrypt.key}")
    private String key;

    @Override
    public String encrypt(final Object body) {
        String encryptedBody = null;
        
        try {
            
            System.out.println("body "+body);
            String bodyStr = body instanceof String ? (String) body : new Gson().toJson(body);
            System.out.println("bodyStr "+bodyStr);
            System.out.println("bodyStr "+new String(bodyStr.getBytes(),StandardCharsets.UTF_8));
            byte[] cipherText = getCipher(Cipher.ENCRYPT_MODE).doFinal(bodyStr.getBytes());
            System.out.println("new String(Base64.getEncoder().encode(cipherText) "+new String(Base64.getEncoder().encode(cipherText)));
            encryptedBody= new String(Base64.getEncoder().encode(cipherText));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encryptedBody;
       
    }

    @Override
    public byte[] decrypt(final String body) {
        try {
            return getCipher(Cipher.DECRYPT_MODE).doFinal(Base64.getDecoder()
            .decode(body));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return new byte[] {};
        }
    }

    // 获取 cipher
    private Cipher getCipher(int model)
            throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        return cipher;
    }
}