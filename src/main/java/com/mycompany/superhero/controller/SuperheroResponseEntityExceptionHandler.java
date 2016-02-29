/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.controller;

import javax.annotation.Resource;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.mycompany.superhero.model.ErrorResponse;
import com.mycompany.superhero.model.FieldErrorMessage;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static com.mycompany.superhero.model.ErrorResponse.anErrorResponse;
import static java.time.LocalDateTime.now;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * {@link ResponseEntityExceptionHandler} which adds exception handling for additional exceptions.
 * <br/>
 * This class is also a centralized exception handler to handle and construct customized exception response.
 *
 * @author duc
 */
@ControllerAdvice
public class SuperheroResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    protected MessageSource validationMessageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolationException(
            final ConstraintViolationException constraintViolationException) {

        final HttpStatus httpStatus = BAD_REQUEST;

        final ErrorResponse.Builder errorResponseBuilder =  anErrorResponse(
                        validationMessageSource.getMessage("validation.error", null, getLocale()))
                        .withHttpStatusCode(BAD_REQUEST.toString()).withTime(now());

        for (ConstraintViolation<?> constraintViolation : constraintViolationException.getConstraintViolations()) {
            errorResponseBuilder.addFieldErrorMessage(
                    new FieldErrorMessage(constraintViolation.getPropertyPath().toString(),
                                          constraintViolation.getMessage())
            );
        }

        return new ResponseEntity<>(errorResponseBuilder.build(), httpStatus);
    }
}
