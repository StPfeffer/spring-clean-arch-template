package com.pfeffer.springcleanarchtemplate.domain.exception;

/**
 * Exception thrown to indicate a validation error within internal processing
 * logic. This exception is intended for use in scenarios where internal
 * classes encounter issues that do not stem from user input or interaction.
 * <p>
 * For example, it may be thrown when internal validation checks fail, such as
 * verifying that an object is not null or that the expected internal state is
 * met. This exception helps to isolate internal errors from user-facing
 * exceptions, thereby improving the robustness of the application.
 * <p>
 * This class extends {@link RuntimeException}, making it an unchecked exception
 * that can be thrown without being declared in a method's throws clause.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class InternalValidationException extends RuntimeException {

    /**
     * Constructs a new {@link InternalValidationException} with the specified
     * detail message.
     *
     * @param message the detail message (which is saved for later retrieval
     *                by the {@link Throwable#getMessage()} method).
     */
    public InternalValidationException(String message) {
        super(message);
    }

    /**
     * Converts a {@link InternalValidationException} into a new instance of
     * {@link IllegalArgumentException}
     *
     * @param exception the exception that will be converted
     * @return a new {@link IllegalArgumentException} with the same message as
     * the {@code exception}.
     */
    public static IllegalArgumentException toIllegalArgumentException(InternalValidationException exception) {
        return new IllegalArgumentException(exception.getMessage(), exception);
    }

}
