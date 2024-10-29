package com.pfeffer.springcleanarchtemplate.presentation.exception.dto;

import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;

public record ExceptionDTO(ExceptionInfoDTO error) {

    public ExceptionDTO {
        InternalValidation.notNull(error, "ExceptionInfoDTO cannot be null");
    }

}
