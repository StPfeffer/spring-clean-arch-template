package com.pfeffer.springcleanarchtemplate.domain.utils;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * Utility class for retrieving localized messages based on a key and locale.
 * <p>
 * This class provides a method to fetch messages from resource bundles, allowing
 * for internationalization (i18n) of application messages.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public final class MessageUtils {

    private static final String MESSAGES_BUNDLE_NAME = "messages";

    private static final String ENTITIES_BUNDLE_NAME = "entities";

    private MessageUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Retrieves a localized message corresponding to the provided message key
     * and locale.
     * <p>
     * This method looks up the message in a resource bundle using the specified
     * locale. If the key does not exist in the resource bundle, a
     * {@link MissingResourceException} may be thrown.
     *
     * @param messageKey the key for the desired message
     * @param locale     the {@link Locale} in which to retrieve the message
     * @return the localized message as a {@link String}
     * @throws MissingResourceException if no resource bundle for the specified
     *                                  base name can be found or if the
     *                                  specified key is not found in the bundle
     */
    public static String getMessageForLocale(String messageKey, Locale locale) {
        return ResourceBundle.getBundle(MESSAGES_BUNDLE_NAME, locale)
                .getString(messageKey);
    }

    /**
     * Retrieves a localized entity name corresponding to the provided entity
     * key and locale.
     * <p>
     * This method looks up the entity in a resource bundle using the specified
     * locale. If the key does not exist in the resource bundle, a {@link
     * MissingResourceException} may be thrown.
     *
     * @param entityKey the key for the desired entity
     * @param locale    the {@link Locale} in which to retrieve the entity name
     * @return the localized entity name as a {@link String}
     * @throws MissingResourceException if no resource bundle for the specified
     *                                  name can be found or if the specified
     *                                  key is not found in the bundle.
     */
    public static String getEntityNameForLocale(String entityKey, Locale locale) {
        return ResourceBundle.getBundle(ENTITIES_BUNDLE_NAME, locale)
                .getString(entityKey);
    }

}
