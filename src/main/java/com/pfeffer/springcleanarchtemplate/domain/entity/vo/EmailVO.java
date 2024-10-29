package com.pfeffer.springcleanarchtemplate.domain.entity.vo;

import com.pfeffer.springcleanarchtemplate.domain.exception.email.EmailErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.exception.email.InvalidEmailException;

import java.util.regex.Pattern;

/**
 * Value Object representing an email address.
 * <p>
 * This class encapsulates an email address as a {@link String} and ensures that
 * it adheres to a standard email format through validation. The validation is
 * performed using a regular expression pattern, which checks for the typical
 * structure of an email address, including the presence of an "@" symbol and a
 * valid domain.
 * <p>
 * Upon creation, the email address is validated to ensure that it  conforms to
 * the expected format.
 * <p>
 * <b>Example usage:</b>
 * <pre>
 * {@code
 * EmailVO email = new EmailVO("example@domain.com");
 * }
 * </pre>
 * <p>
 * <b>Regular Expression Used:</b>
 * <pre>
 * ^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$
 * </pre>
 * <p>
 * This pattern allows alphanumeric characters, special characters typically
 * found in email addresses, and ensures the presence of a valid domain after
 * the "@" symbol.
 *
 * @param value the email address as a {@link String}. It must match the
 *              standard email format
 * @author Mateus Pfeffer
 * @since 1.0
 */
public record EmailVO(String value) {

    private static final Pattern PATTERN = Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?");

    private static final int MAX_LOCAL_PART_LENGTH = 64;

    private static final int MIN_LOCAL_PART_LENGTH = 5;

    private static final int MAX_EMAIL_LENGTH = 254;

    /**
     * Constructs a new {@link EmailVO} instance.
     * <p>
     * This constructor performs multiple validations on the provided email
     * address ensuring the {@link EmailVO} always represents a valid email
     * address.
     *
     * @param value the email address as a {@link String}. Must not be {@code
     *              null} and must pass all the defined validation checks.
     * @throws InvalidEmailException if the email does not match the expected
     *                               format
     */
    public EmailVO {
        if (value != null) {
            String[] parts = value.split("@");
            String localPart = parts[0];

            if (localPart.length() > MAX_LOCAL_PART_LENGTH) {
                throw new InvalidEmailException(EmailErrorCode.LOCAL_PART_TOO_LONG, value);
            }

            if (localPart.length() < MIN_LOCAL_PART_LENGTH) {
                throw new InvalidEmailException(EmailErrorCode.LOCAL_PART_TOO_SHORT, value);
            }

            if (value.length() > MAX_EMAIL_LENGTH) {
                throw new InvalidEmailException(EmailErrorCode.TOO_LONG, value);
            }

            if (!PATTERN.matcher(value).matches()) {
                throw new InvalidEmailException(EmailErrorCode.INVALID_ADDRESS, value);
            }

        }
    }

}
