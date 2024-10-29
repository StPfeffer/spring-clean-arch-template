package com.pfeffer.springcleanarchtemplate.domain.utils;

import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;

/**
 * Validation utility class that assists in validating arguments.
 * <p>
 * Useful for identifying programmer errors early and clearly at runtime.
 * <p>
 * For example, if the contract of a public method states it does not allow
 * {@code null} arguments, {@code InternalValidation} can be used to validate
 * that contract. Doing this clearly indicates a contract violation when it
 * occurs and protects the class's invariants.
 * <p>
 * Typically used to validate method arguments rather than configuration
 * properties, to check for cases that are usually programmer errors rather
 * than configuration errors. In contrast to configuration initialization code,
 * there is usually no point in falling back to defaults in such methods.
 * <p>
 * This class is similar to JUnit's validation library. If an argument value is
 * deemed invalid, an {@link InternalValidationException} is thrown.
 * <p>
 * For example:
 * <pre>{@code
 * InternalValidation.notNull(clazz, "The class must not be null");
 * InternalValidation.isTrue(i > 0, "The value must be greater than zero");
 * }</pre>
 *
 * @implNote Mainly for internal use; this class should <b>NOT</b> be used to
 * validate external data.
 * @see Validation external data validation
 */
public final class InternalValidation {

    private InternalValidation() {
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
     * @throws InternalValidationException if the object is {@code null}
     */
    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new InternalValidationException(message);
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
     * @throws InternalValidationException if {@code expression} is {@code false}
     */
    public static void isTrue(boolean expression, String message) {
        if (!expression) {
            throw new InternalValidationException(message);
        }
    }

}
