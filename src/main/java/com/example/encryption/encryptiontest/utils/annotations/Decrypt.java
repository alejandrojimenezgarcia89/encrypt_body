package com.example.encryption.encryptiontest.utils.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Si está a nivel de método (Controller), se desencriptará el request body (Activa el filtro CustomRequestBodyAdvice)
 * Si está a nivel de field del DTO del request body, se desencriptará el campo 
 * Si no estuviera a nivel de DTO y sólo a nivel de método, se desencriptaría todo el request body
 */
@Retention(RUNTIME)
@Target({ElementType.PARAMETER,ElementType.FIELD})
public @interface Decrypt {}