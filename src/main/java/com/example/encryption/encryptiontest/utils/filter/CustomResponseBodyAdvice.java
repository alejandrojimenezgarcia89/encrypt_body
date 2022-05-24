package com.example.encryption.encryptiontest.utils.filter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.example.encryption.encryptiontest.utils.annotations.Encrypt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class CustomResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    private final IEncryptionService aesService;
    
    /**
     * If this method returns false, the `beforeBodyWrite` method will not be
     * executed.
     */

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.hasMethodAnnotation(Encrypt.class);
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
            Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
            ServerHttpResponse response) {
        boolean hasFieldAnnotation = false;
        for (Field field : body.getClass().getDeclaredFields()) {
            for (Annotation fieldAnnotation : field.getAnnotations()) {
                if (fieldAnnotation.annotationType().equals(Encrypt.class)) {
                    try {
                        field.setAccessible(true);
                        field.set(body, aesService.encrypt(field.get(body)));
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                    hasFieldAnnotation = true;
                }
            }
        }
        if (hasFieldAnnotation) {
            return body;
        } else {
            return aesService.encrypt(body);
        }

    }

}