package com.example.encryption.encryptiontest.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RequestBodyTest {

    private String email;
    private Long employeeId;
}
