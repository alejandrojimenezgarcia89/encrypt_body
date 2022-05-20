package com.example.encryption.encryptiontest.service;

import java.util.Base64;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.google.gson.Gson;

import org.springframework.stereotype.Service;

@Service
public class Base64Service implements IEncryptionService {

    @Override
    public String encrypt(Object body) {
        return Base64.getEncoder().encodeToString(new Gson().toJson(body).toString().getBytes());
    }

    @Override
    public byte[] decrypt(byte[] body) {
        return Base64.getDecoder().decode(body);
    }
}
