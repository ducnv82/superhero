/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.controller;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.mycompany.superhero.controller.SuperheroResponseEntityExceptionHandler;
import com.mycompany.superhero.model.ErrorResponse;
import com.mycompany.superhero.model.FieldErrorMessage;
import mockit.Injectable;
import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.Tested;
import org.hamcrest.Matcher;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasProperty;
import static org.springframework.context.i18n.LocaleContextHolder.getLocale;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

public class SuperheroResponseEntityExceptionHandlerTest {

    @Tested
    private SuperheroResponseEntityExceptionHandler superheroExceptionHandler;

    @Injectable
    private MessageSource validationMessageSource;

    @Test
    public void testHandleConstraintViolationException(@Mocked final ConstraintViolation violation) throws Exception {
        final Set<ConstraintViolation<?>> violations = new HashSet<>();
        violations.add(violation);

        final ConstraintViolationException exception = new ConstraintViolationException(violations);

        final String fieldName = "field1";
        final String fieldErrorMessage = "Field error message";
        final String validationErrorMessage = "Validation error";

        new NonStrictExpectations() {{
            validationMessageSource.getMessage("validation.error", null, getLocale());
            result = validationErrorMessage;

            violation.getPropertyPath(); result = PathImpl.createPathFromString(fieldName);
            violation.getMessage(); result = fieldErrorMessage;
        }};

        final ResponseEntity<Object> response = superheroExceptionHandler.handleConstraintViolationException(exception);
        final HttpStatus httpStatus = BAD_REQUEST;

        assertThat(response.getStatusCode(), equalTo(httpStatus));

        final ErrorResponse errorResponse = (ErrorResponse) response.getBody();
        assertThat(errorResponse.getHttpStatusCode(), equalTo(httpStatus.toString()));
        assertThat(errorResponse.getMessage(), equalTo(validationErrorMessage));

        final List<FieldErrorMessage> fieldErrorMessages = ((ErrorResponse) response.getBody()).getFieldErrorMessages();
        assertThat(fieldErrorMessages.size(), equalTo(1));
        assertThat(fieldErrorMessages.get(0), hasFieldAndMessage(fieldName, fieldErrorMessage));
    }

    private Matcher<FieldErrorMessage> hasFieldAndMessage(final String fieldName, final String fieldErrorMessage) {
        return allOf(hasProperty("field", equalTo(fieldName)),
                     hasProperty("message", equalTo(fieldErrorMessage)));
    }
}
