package com.example.encryption.encryptiontest;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.example.encryption.encryptiontest.utils.DecryptTest;
import com.example.encryption.encryptiontest.utils.annotations.Decrypt;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice // Don't forget the @RestControllerAdvice annotation. It will take effect for all RestControllers.
@Slf4j
@RequiredArgsConstructor
public class DecryptAdvice extends RequestBodyAdviceAdapter {

    private final IEncryptionService aesService;
    /**
        * If this method returns false, the `beforeBodyRead` method will not be executed. 
        */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        // If the parameter is annotated with `@Decrypt` then it needs to be decoded.
        return methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    /**
        * This method will be executed before spring mvc reads the request body. We can do some pre-processing of the request body here.
        */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {

        try (InputStream inputStream = inputMessage.getBody()) {

            // Read request body
            byte[] body = StreamUtils.copyToByteArray(inputStream);
            
            System.out.println("raw: "+ new String(body));

            // Base64 Decode
            byte[] decodedBody = aesService.decrypt(body);
            
            System.out.println("decode: "+ new String(decodedBody, StandardCharsets.UTF_8));

            // Return the decoded body
            return new DecryptTest(inputMessage.getHeaders(), new ByteArrayInputStream(decodedBody));
        }
    }
}