package com.pfeffer.springcleanarchtemplate.presentation.exception.dto;

import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

public final class ExceptionDTOFactory {

    public static ExceptionDTO create(String message, WebRequest request) {
        ExceptionInfoDTO exceptionInfo = new ExceptionInfoDTO.Builder(
                message,
                "000",
                500,
                request.getDescription(false)
        ).build();

        return new ExceptionDTO(exceptionInfo);
    }

    public static ExceptionDTO create(String message, LocalDateTime timestamp, WebRequest request) {
        ExceptionInfoDTO exceptionInfo = new ExceptionInfoDTO.Builder(
                message,
                "000",
                500,
                request.getDescription(false)
        ).timestamp(timestamp).build();

        return new ExceptionDTO(exceptionInfo);
    }

    public static ExceptionDTO create(LocalizedException e, WebRequest request) {
        ExceptionInfoDTO exceptionInfo = new ExceptionInfoDTO.Builder(
                e.getMessage(),
                e.getError().getKey(),
                e.getError().getHttpStatus(),
                request.getDescription(false)
        ).build();

        return new ExceptionDTO(exceptionInfo);
    }

    public static ExceptionDTO create(Exception e, WebRequest request) {
        ExceptionInfoDTO exceptionInfo = new ExceptionInfoDTO.Builder(
                e.getMessage(),
                "000",
                500,
                request.getDescription(false)
        ).build();

        return new ExceptionDTO(exceptionInfo);
    }

}
