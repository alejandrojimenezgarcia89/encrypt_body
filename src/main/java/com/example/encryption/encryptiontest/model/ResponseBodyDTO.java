package com.example.encryption.encryptiontest.model;

import com.example.encryption.encryptiontest.utils.annotations.Encrypt;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseBodyDTO {

    @Encrypt
    private String email;
    @Encrypt
    private String employeeId;
    private String publicData;
}
