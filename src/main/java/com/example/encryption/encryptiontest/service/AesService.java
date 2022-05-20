package com.example.encryption.encryptiontest.service;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.google.gson.Gson;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AesService implements IEncryptionService {

    @Value("${encrypt.key}")
    private String key;

    @Override
    public String encrypt(Object body) {
        return AESUtils.encrypt(new Gson().toJson(body).toString().getBytes(), key);
    }

    @Override
    public byte[] decrypt(byte[] body) {
        return AESUtils.decrypt(body, key);
    }
}

class AESUtils {
    private static final String AES_ALGORITHM = "AES/ECB/PKCS5Padding";
    // 获取 cipher
    private static Cipher getCipher(byte[] key, int model) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
        
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(model, secretKeySpec);
        return cipher;
    }
    // AES加密
    public static String encrypt(byte[] data, String key)  {
        Cipher cipher;
        try {
            cipher = getCipher(key.getBytes(), Cipher.ENCRYPT_MODE);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        
    }
    // AES解密
    public static byte[] decrypt(byte[] data, String key)  {
        
        try {
            Cipher cipher = getCipher(key.getBytes(), Cipher.DECRYPT_MODE);
            return cipher.doFinal(Base64.getDecoder().decode(data));
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[]{};
        }
    }}
