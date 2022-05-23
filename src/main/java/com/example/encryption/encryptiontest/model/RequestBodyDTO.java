package com.example.encryption.encryptiontest.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestBodyDTO implements Serializable {

    private String email;
    private Long employeeId;
    private String publicData;
}
