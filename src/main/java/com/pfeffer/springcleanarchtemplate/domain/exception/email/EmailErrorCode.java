package com.pfeffer.springcleanarchtemplate.domain.exception.email;

import com.pfeffer.springcleanarchtemplate.domain.entity.enums.IEnum;
import com.pfeffer.springcleanarchtemplate.domain.exception.ErrorCode;

/**
 * Enumeration representing standard error codes for emails used within the
 * application.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public enum EmailErrorCode implements ErrorCode {

    BLACKLISTED("001", "email.blacklisted", 404),
    INVALID_ADDRESS("002", "email.invalid_address", 400),
    LOCAL_PART_TOO_LONG("003", "email.local_part_too_long", 400),
    TOO_LONG("004", "email.email_too_long", 400),
    LOCAL_PART_TOO_SHORT("005", "email.local_part_too_short", 400);

    private final String key;

    private final String messageKey;

    private final int httpStatus;

    /**
     * Constructs a new {@code EmailErrorCode} with the specified key, message
     * key, and HTTP status code.
     *
     * @param key        the unique key identifying the error code
     * @param messageKey the key used to look up the localized email error
     *                   message
     * @param httpStatus the HTTP status code associated with this error
     */
    EmailErrorCode(String key, String messageKey, int httpStatus) {
        this.key = key;
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    public static boolean contains(String key) {
        return IEnum.parseByKey(key, EmailErrorCode.class) != null;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getMessageKey() {
        return messageKey;
    }

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

}
