package com.example.encryption.encryptiontest.utils.mappers;

import com.example.encryption.encryptiontest.model.RequestBodyDTO;
import com.example.encryption.encryptiontest.model.RequestBodyDTOEncryptedFields;
import com.example.encryption.encryptiontest.model.ResponseBodyDTO;

import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, 
nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface BodyMapper {
    
    public ResponseBodyDTO requestToResponse(RequestBodyDTO request);

    public ResponseBodyDTO requestEncryptedFieldsToResponse(RequestBodyDTOEncryptedFields body);
}
