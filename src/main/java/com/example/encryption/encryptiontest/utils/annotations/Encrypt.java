package com.example.encryption.encryptiontest.utils.annotations;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Si está a nivel de método (Controller), se encriptará la respuesta (Activa el filtro CustomResponseBodyAdvice)
 * Si está a nivel de field del DTO de la respuesta, se encriptará el campo
 * Si no estuviera a nivel de DTO y sólo a nivel de método, se encriptaría toda la respuesta
 */
@Retention(RUNTIME)
@Target({ElementType.FIELD,ElementType.METHOD})
public @interface Encrypt {

}