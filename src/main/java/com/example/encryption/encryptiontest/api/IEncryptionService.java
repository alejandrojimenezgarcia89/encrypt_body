package com.example.encryption.encryptiontest.api;

public interface IEncryptionService {
    public String encrypt(Object bodyDecoded);
    // public byte[] decrypt(byte[] bodyEncoded);
    public byte[] decrypt(String bodyEncoded);
}