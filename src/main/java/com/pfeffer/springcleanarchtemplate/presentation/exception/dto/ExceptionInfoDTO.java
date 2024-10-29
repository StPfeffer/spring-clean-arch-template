package com.pfeffer.springcleanarchtemplate.presentation.exception.dto;

import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;

import java.time.LocalDateTime;
import java.util.List;

public class ExceptionInfoDTO {

    private final String message;

    private final LocalDateTime timestamp;

    private final String errorCode;

    private final int httpStatus;

    private final String path;

    private final List<String> details;

    private ExceptionInfoDTO(Builder builder) {
        this.message = builder.message;
        this.timestamp = builder.timestamp != null ? builder.timestamp : LocalDateTime.now();
        this.errorCode = builder.errorCode;
        this.httpStatus = builder.httpStatus;
        this.path = builder.path;
        this.details = builder.details;

        InternalValidation.notNull(message, "message cannot be null");
        InternalValidation.notNull(errorCode, "errorCode cannot be null");
        InternalValidation.isTrue(httpStatus > 0, "httpStatus must be greater than 0");
        InternalValidation.notNull(path, "path cannot be null");
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public String getPath() {
        return path;
    }

    public List<String> getDetails() {
        return details;
    }

    public static class Builder {

        private final String message;

        private final String errorCode;

        private final int httpStatus;

        private final String path;

        private LocalDateTime timestamp;

        private List<String> details;

        public Builder(String message, String errorCode, int httpStatus, String path) {
            this.message = message;
            this.errorCode = errorCode;
            this.httpStatus = httpStatus;
            this.path = path;
        }

        public Builder timestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder details(List<String> details) {
            this.details = details;
            return this;
        }

        public ExceptionInfoDTO build() {
            return new ExceptionInfoDTO(this);
        }

    }

}
