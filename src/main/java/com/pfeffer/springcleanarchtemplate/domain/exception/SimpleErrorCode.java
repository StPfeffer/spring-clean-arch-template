package com.pfeffer.springcleanarchtemplate.domain.exception;

import com.pfeffer.springcleanarchtemplate.domain.entity.enums.IEnum;

/**
 * Enumeration representing standard error codes used within the application.
 * <p>
 * This enum defines various error codes that correspond to common error
 * conditions encountered in the application.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public enum SimpleErrorCode implements ErrorCode {

    REQUIRED_FIELD("001", "required.field", 400),
    INTERNAL_SERVER_ERROR("002", "internal.server_error", 500),
    REQUEST_DTO_NULL("003", "request_dto.null", 400),
    STRING_CANT_BE_LONGER_THAN("004", "string.cant_be_longer_than", 400),
    STRING_CANT_BE_SHORTER_THAN("005", "string.cant_be_shorter_than", 400),
    INTEGER_CANT_BE_LOWER_THAN("006", "integer.cant_be_lower_than", 400),
    NOT_FOUND("007", "not_found", 404),
    ALREADY_EXISTS("008", "already_exists", 400),
    STRING_MUST_BE_BETWEEN("009", "string.must_be_between", 400);

    private final String key;

    private final String messageKey;

    private final int httpStatus;

    /**
     * Constructs a new {@code SimpleErrorCode} with the specified key, message
     * key, and HTTP status code.
     *
     * @param key        the unique key identifying the error code
     * @param messageKey the key used to look up the localized error message
     * @param httpStatus the HTTP status code associated with this error
     */
    SimpleErrorCode(String key, String messageKey, int httpStatus) {
        this.key = key;
        this.messageKey = messageKey;
        this.httpStatus = httpStatus;
    }

    public static boolean contains(String key) {
        return IEnum.parseByKey(key, SimpleErrorCode.class) != null;
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
