package com.pfeffer.springcleanarchtemplate.domain.utils;

import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;

/**
 * A utility class for validating external arguments in a structured manner.
 * <p>
 * This class serves as a decorator for {@link InternalValidation},
 * converting all exceptions thrown by methods in {@link InternalValidation}
 * into {@link IllegalArgumentException}. This is particularly useful in
 * scenarios where external inputs need to be validated before processing
 * to ensure data integrity and application stability.
 * <p>
 * Commonly utilized to validate external data such as pagination parameters,
 * not-null properties in Data Transfer Objects (DTOs), and other user inputs.
 * This validation helps to enforce business rules and guarantees that invalid
 * arguments do not propagate through the application.
 * <p>
 * When an argument value is found to be invalid, an
 * {@link IllegalArgumentException} is thrown with a descriptive message to aid
 * in identifying the nature of the validation failure.
 * <p>
 * Example usage:
 * <pre>{@code
 * Validation.isTrue(pageNumber > 0, "The pageNumber must be greater than zero");
 * Validation.notNull(dto, "The DTO must not be null");
 * }</pre>
 *
 * @see InternalValidation for internal data validation utilities.
 */
public final class Validation {

    private Validation() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Validates that an object is not {@code null}.
     * <p>
     * <pre>{@code
     * InternalValidation.notNull(clazz, "The class must not be null");
     * }</pre>
     *
     * @param object  the object to check
     * @param message the exception message to use if the validation fails
     * @throws IllegalArgumentException if the object is {@code null}
     */
    public static void notNull(Object object, String message) {
        try {
            InternalValidation.notNull(object, message);
        } catch (InternalValidationException e) {
            handleException(e);
        }
    }

    /**
     * Validates a boolean expression, throwing an {@code InternalValidationException}
     * if the expression evaluates to {@code false}.
     * <p>
     * <pre>{@code
     * InternalValidation.isTrue(i > 0, "The value must be greater than zero");
     * }</pre>
     *
     * @param expression a boolean expression
     * @param message    the exception message to use if the validation fails
     * @throws IllegalArgumentException if {@code expression} is {@code false}
     */
    public static void isTrue(boolean expression, String message) {
        try {
            InternalValidation.isTrue(expression, message);
        } catch (InternalValidationException e) {
            handleException(e);
        }
    }

    private static void handleException(InternalValidationException e) {
        throw InternalValidationException.toIllegalArgumentException(e);
    }

}
