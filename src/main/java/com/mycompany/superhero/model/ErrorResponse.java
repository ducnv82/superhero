/*
 * Copyright (c) Duc Nguyen Van. - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited.
 * Proprietary and confidential.
 */

package com.mycompany.superhero.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * Model for error response.
 *
 * @author duc
 */
public final class ErrorResponse {

    private final String message;
    private final String errorCode;
    private final String httpStatusCode;
    private final String details;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private final LocalDateTime time;

    private final List<FieldErrorMessage> fieldErrorMessages;

    private ErrorResponse(final Builder builder) {
        message = builder.message;
        fieldErrorMessages = builder.fieldErrorMessages;
        errorCode = builder.errorCode;
        httpStatusCode = builder.httpStatusCode;
        details = builder.details;
        time = builder.time;
    }

    /**
     * ErrorResponse builder.
     *
     * @param message message
     * @return builder for ErrorResponse
     */
    public static Builder anErrorResponse(final String message) {
        return new Builder(message);
    }

    /**
     * Get message.
     *
     * @return message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Get list of {@link FieldErrorMessage}s.
     *
     * @return list of FieldError
     */
    public List<FieldErrorMessage> getFieldErrorMessages() {
        return fieldErrorMessages != null ? Collections.unmodifiableList(fieldErrorMessages) : new ArrayList<>();
    }

    /**
     * Get errorCode.
     *
     * @return errorCode
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Get HTTP status code.
     *
     * @return HTTP status code
     */
    public String getHttpStatusCode() {
        return httpStatusCode;
    }

    /**
     * Get details.
     *
     * @return details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Get time.
     *
     * @return time
     */
    public LocalDateTime getTime() {
        return time != null ? time : null;
    }

    /**
     * ErrorResponse builder.
     */
    public static final class Builder {

        private final String message;
        private final List<FieldErrorMessage> fieldErrorMessages;

        private String errorCode;
        private String httpStatusCode;
        private String details;
        private LocalDateTime time;

        private Builder(final String message) {
            this.message = message;
            fieldErrorMessages = new ArrayList<>();
        }

        /**
         * Add {@link FieldErrorMessage}.
         *
         * @param fieldErrorMessage FieldErrorMessage
         * @return builder
         */
        public Builder addFieldErrorMessage(final FieldErrorMessage fieldErrorMessage) {
            fieldErrorMessages.add(fieldErrorMessage);
            return this;
        }

        /**
         * With error code.
         *
         * @param errorCode errorCode
         * @return builder
         */
        public Builder withErrorCode(final String errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        /**
         * With HTTP status code.
         *
         * @param httpStatusCode HTTP status code
         * @return builder
         */
        public Builder withHttpStatusCode(final String httpStatusCode) {
            this.httpStatusCode = httpStatusCode;
            return this;
        }

        /**
         * With details.
         *
         * @param details details
         * @return builder
         */
        public Builder withDetails(final String details) {
            this.details = details;
            return this;
        }

        /**
         * With time.
         *
         * @param time time
         * @return builder
         */
        public Builder withTime(final LocalDateTime time) {
            this.time = time != null ? time : null;
            return this;
        }

        /**
         * Build ErrorResponse.
         *
         * @return ErrorResponse instance
         */
        public ErrorResponse build() {
            return new ErrorResponse(this);
        }
    }
}
