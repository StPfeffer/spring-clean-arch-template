package com.pfeffer.springcleanarchtemplate.domain.utils.type;

import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;

import java.text.MessageFormat;

/**
 * The {@link StringUtils} class defines certain words related to
 * String handling.
 * <p>{@link StringUtils} handles {@code null} input Strings quietly. That is
 * to say that a {@code null} input will return {@code null}. Where a
 * {@code boolean} or {@code int} is being returned details vary by method.
 * <p>
 * A side effect of the {@code null} handling is that a
 * {@link NullPointerException} should be considered a bug in
 * {@link StringUtils}.
 *
 * @author Mateus Pfeffer
 * @see String
 * @since 1.0
 */
public final class StringUtils {

    private StringUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Gets the message using {@link String#format(String, Object...)
     * String.format(message, values)} if the values are not empty, otherwise
     * return the message unformatted.
     * <p>
     * This method exists to allow validation
     * methods declaring a String message and varargs parameters to be used
     * without any message parameters when the message contains special
     * characters.
     *
     * @param message the {@link String#format(String, Object...)} exception
     *                message if invalid, not null
     * @param args    the optional values for the formatted message
     * @return formatted message using {@link String#format(String, Object...)
     * String.format(message, values)} if the values are not empty, otherwise
     * return the unformatted
     */
    public static String getMessageFormat(String message, Object... args) {
        InternalValidation.notNull(message, "message cannot be null");

        return ArrayUtils.isEmpty(args) ? message : MessageFormat.format(message, args);
    }

    /**
     * Check that the given {@code String} is neither {@code null} nor of length 0.
     * <p>Note: this method returns {@code true} for a {@code String} that
     * purely consists of whitespace.
     *
     * @param cs the {@code String} to check (can be {@code null})
     * @return {@code true} if the {@code String} is not {@code null} and has length
     */
    public static boolean hasLength(CharSequence cs) {
        return (cs != null && !cs.isEmpty());
    }

}
