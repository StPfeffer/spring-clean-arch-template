package com.pfeffer.springcleanarchtemplate.domain.exception;

import com.pfeffer.springcleanarchtemplate.domain.utils.MessageUtils;
import com.pfeffer.springcleanarchtemplate.domain.utils.type.ArrayUtils;
import com.pfeffer.springcleanarchtemplate.domain.utils.type.StringUtils;

import java.util.Locale;

/**
 * A custom runtime exception that supports localization of error messages.
 * <p>
 * This exception is used to represent errors with messages that can be
 * localized based on a provided {@link Locale}. The message is constructed
 * using a message key and optional arguments, which are formatted to produce
 * the final error message.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class LocalizedException extends RuntimeException {

    private final ErrorCode error;

    private final String messageKey;

    private final Locale locale;

    private final Object[] args;

    /**
     * Constructs a new {@code KmFacilLocalizedException} with the specified
     * {@link ErrorCode} and optional arguments.
     * <p>
     * The message is localized using the message key associated with the
     * {@code error} and the default locale. The optional arguments are used
     * for message formatting.
     *
     * @param error the {@link ErrorCode} representing the error type
     * @param args  optional arguments to format the message
     */
    public LocalizedException(ErrorCode error, Object... args) {
        this(error, Locale.getDefault(), args);
    }

    /**
     * Constructs a new {@code KmFacilLocalizedException} with the specified
     * message key, locale, and optional arguments.
     * <p>
     * The message is retrieved using the provided {@code messageKey} and
     * localized according to the provided {@code locale}. The optional
     * arguments are used to format the message.
     *
     * @param error  the {@link ErrorCode} representing the error type
     * @param locale the locale for localization of the message
     * @param args   optional arguments to format the message
     */
    public LocalizedException(ErrorCode error, Locale locale, Object... args) {
        this.error = error;
        this.messageKey = error.getMessageKey();
        this.locale = locale;
        this.args = args;
    }

    /**
     * Returns the localized error message.
     * <p>
     * Internally, this method will call {@link #getLocalizedMessage()}.
     *
     * @return the localized error message
     */
    @Override
    public String getMessage() {
        return getLocalizedMessage();
    }

    /**
     * Retrieves the localized message for the specified {@code messageKey} and
     * {@code locale}.
     * <p>
     * This method leverages {@link MessageUtils#getMessageForLocale(String,
     * Locale)} to obtain the message associated with the given {@code messageKey}
     * and formats it using any optional arguments provided during the
     * construction of this instance.
     * <p>
     * The message may include placeholders, such as "{entity}". If so, this method
     * assumes that the first argument in {@link LocalizedException#args} is a
     * valid entity name key. This entity name key is passed to {@link
     * MessageUtils#getEntityNameForLocale(String, Locale)} to retrieve the
     * entity name in the specified {@code locale}, replacing the "{entity}"
     * placeholder with the localized entity name.
     * <p>
     * Finally, the message is formatted using {@link StringUtils#getMessageFormat
     * (String, Object...)} to apply any remaining arguments.
     * <p>
     * <b>Note:</b> Ensure that the first argument in
     * {@link LocalizedException#args} is a valid key for retrieving the
     * localized entity name.
     *
     * @return the formatted localized message, with placeholders replaced and
     * arguments applied
     * @implNote If the message contains the placeholder "{entity}", the method
     * assumes that the first argument in {@link LocalizedException#args}
     * corresponds to an entity name key. The entity name is retrieved via
     * {@link MessageUtils#getEntityNameForLocale(String, Locale)} and is used to
     * replace the "{entity}" placeholder.
     */
    @Override
    public String getLocalizedMessage() {
        String localizedMessage = MessageUtils.getMessageForLocale(messageKey, locale);

        if (localizedMessage.contains("{entity}") && !ArrayUtils.isEmpty(args)) {
            String entityKey = args[0].toString();

            String entityName = MessageUtils.getEntityNameForLocale(entityKey, locale);

            localizedMessage = localizedMessage.replace("{entity}", entityName);
        }

        return StringUtils.getMessageFormat(localizedMessage, args);
    }

    public ErrorCode getError() {
        return error;
    }

    public String getMessageKey() {
        return messageKey;
    }

    public Locale getLocale() {
        return locale;
    }

    public Object[] getArgs() {
        return args;
    }

}
