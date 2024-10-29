package com.pfeffer.springcleanarchtemplate.domain.exception.email;

import com.pfeffer.springcleanarchtemplate.domain.exception.ErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;

import java.util.Locale;

public class InvalidEmailException extends LocalizedException {

    public InvalidEmailException(ErrorCode error, Object... args) {
        super(error, args);
    }

    public InvalidEmailException(ErrorCode error, Locale locale, Object... args) {
        super(error, locale, args);
    }

}
