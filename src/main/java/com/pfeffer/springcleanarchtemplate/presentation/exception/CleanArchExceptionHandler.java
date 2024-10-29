package com.pfeffer.springcleanarchtemplate.presentation.exception;

import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;
import com.pfeffer.springcleanarchtemplate.presentation.exception.dto.ExceptionDTO;
import com.pfeffer.springcleanarchtemplate.presentation.exception.dto.ExceptionDTOFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CleanArchExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(LocalizedException.class)
    public ResponseEntity<Object> threatKmFacilException(LocalizedException e, WebRequest request) {
        ExceptionDTO dto = ExceptionDTOFactory.create(e, request);

        logger.info(e.getMessage(), e);

        return handleExceptionInternal(e, dto, new HttpHeaders(), HttpStatus.valueOf(e.getError().getHttpStatus()), request);
    }

    @ExceptionHandler(InternalValidationException.class)
    public ResponseEntity<Object> threatInternalValidationException(InternalValidationException e, WebRequest request) {
        ExceptionDTO dto = ExceptionDTOFactory.create(e, request);

        logger.error(e.getMessage(), e);

        return handleExceptionInternal(e, dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    public ResponseEntity<Object> threatUnsupportedOperationException(UnsupportedOperationException e, WebRequest request) {
        ExceptionDTO dto = ExceptionDTOFactory.create(e, request);

        logger.error(e.getMessage(), e);

        return handleExceptionInternal(e, dto, new HttpHeaders(), HttpStatus.NOT_IMPLEMENTED, request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> threatException(Exception e, WebRequest request) {
        ExceptionDTO dto = ExceptionDTOFactory.create(e, request);

        logger.error(e.getMessage(), e);

        return handleExceptionInternal(e, dto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

}