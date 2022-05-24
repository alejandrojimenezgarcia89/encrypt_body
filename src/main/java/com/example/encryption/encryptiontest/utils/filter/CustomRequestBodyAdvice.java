package com.example.encryption.encryptiontest.utils.filter;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import com.example.encryption.encryptiontest.api.IEncryptionService;
import com.example.encryption.encryptiontest.utils.annotations.Decrypt;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice // Don't forget the @RestControllerAdvice annotation. It will take effect for
                      // all RestControllers.
@Slf4j
@RequiredArgsConstructor
public class CustomRequestBodyAdvice extends RequestBodyAdviceAdapter {

    private final IEncryptionService aesService;

    /**
     * If this method returns false, the `beforeBodyRead` method will not be
     * executed.
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) {
        // System.out.println("CustomRequestBodyAdvice.supports");
        // If the parameter is annotated with `@Decrypt` then it needs to be decoded.
        return methodParameter.hasParameterAnnotation(Decrypt.class);
    }

    /**
     * This method will be executed before spring mvc reads the request body. We can
     * do some pre-processing of the request body here.
     */
    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType,
            Class<? extends HttpMessageConverter<?>> converterType) throws IOException {
        boolean hasFieldAnnotation = false;
        try (InputStream inputStream = inputMessage.getBody()) {

            byte[] body = StreamUtils.copyToByteArray(inputStream);
            Object bodyObject = null;

            for (Field field : parameter.getParameterType().getDeclaredFields()) {
                for (Annotation fieldAnnotation : field.getAnnotations()) {
                    if (fieldAnnotation.annotationType().equals(Decrypt.class)) {
                        try {
                            if(bodyObject==null){
                                bodyObject = new ObjectMapper().readValue(body, parameter.getParameterType());
                            }
                            field.setAccessible(true);
                            String encryptedField = (String) field.get(bodyObject);
                            field.set(bodyObject, new String(aesService.decrypt(encryptedField)));
                        } catch (IllegalArgumentException | IllegalAccessException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                        hasFieldAnnotation = true;
                    }
                }
            }
            byte[] decodedBody;
            if (hasFieldAnnotation) {
                decodedBody = new ObjectMapper().writeValueAsBytes(bodyObject);
            } else {
                byte[] decrypted = aesService.decrypt(new String(body));
                Object objectDecrypted= new ObjectMapper().readValue(decrypted , parameter.getParameterType());
                decodedBody=new ObjectMapper().writeValueAsBytes(objectDecrypted);
            }

            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    return new ByteArrayInputStream(decodedBody);
                }

                @Override
                public HttpHeaders getHeaders() {
                    return inputMessage.getHeaders();
                }
            };
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
    }
}