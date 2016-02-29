/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.model;


/**
 * Model for field error.
 *
 * @author duc
 */
public class FieldErrorMessage {

    private final String field;
    private final String message;

    /**
     * Construct new FieldError.
     *
     * @param field field
     * @param message message
     */
    public FieldErrorMessage(final String field, final String message) {
        this.field = field;
        this.message = message;
    }

    /**
     * Get field.
     *
     * @return field
     */
    public String getField() {
        return field;
    }

    /**
     * Get message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

}
