package com.pfeffer.springcleanarchtemplate.domain.exception;

import com.pfeffer.springcleanarchtemplate.domain.entity.enums.IEnum;

/**
 * Interface representing a standardized error code in the application.
 * Implementations of this interface should provide a unique error key, a
 * message key for localization, and an associated HTTP status code.
 * <p>
 * This interface is intended to be used for defining various error types
 * that can occur within the application, allowing for consistent error
 * handling and localization.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface ErrorCode extends IEnum {

    /**
     * Retrieves the key for a message in the application's resource bundle.
     * The message key is used to look up a localized error message, making
     * it easier to support multiple languages and regions. This key must be
     * present in the resource bundle used by the application.
     *
     * @return a {@link String} representing the message key for localization.
     */
    String getMessageKey();

    /**
     * Retrieves the HTTP status code associated with this error.
     * This status code indicates the HTTP response status that should be
     * returned to the client when this error occurs, enabling proper
     * communication of error conditions in API responses.
     *
     * @return an {@code int} representing the HTTP status code.
     */
    int getHttpStatus();

}
