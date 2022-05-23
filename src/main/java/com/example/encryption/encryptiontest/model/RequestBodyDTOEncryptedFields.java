package com.example.encryption.encryptiontest.model;

import java.io.Serializable;

import com.example.encryption.encryptiontest.utils.annotations.Decrypt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBodyDTOEncryptedFields implements Serializable {

    @Decrypt
    private String email;
    @Decrypt
    private String employeeId;
    private String publicData;
}
