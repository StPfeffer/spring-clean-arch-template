package com.pfeffer.springcleanarchtemplate.domain.utils.type;

import com.pfeffer.springcleanarchtemplate.domain.entity.enums.IEnum;

/**
 * Utility class for operations involving Java enums that implement the
 * {@link IEnum} interface. Provides methods to parse enums by their key, check
 * for enum existence within a collection, and compare enums for equality.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public final class EnumUtils {

    private EnumUtils() {
        throw new UnsupportedOperationException("This class cannot be instantiated");
    }

    /**
     * Parses an enum constant of the specified {@code enumClass} by its key.
     * <p>
     * If the provided key is {@code null} or empty, or if no matching enum
     * constant is found, {@code null} is returned.
     *
     * @param <E>       the type of enum, extending {@link IEnum}
     * @param enumClass the class object of the enum type
     * @param key       the key to search for, which can be {@code null} or
     *                  empty
     * @return the enum constant with the specified key, or {@code null} if
     * not found or key is empty
     */
    public static <E extends IEnum> E parseByKey(Class<E> enumClass, String key) {
        if (!StringUtils.hasLength(key)) {
            return null;
        }

        for (E value : enumClass.getEnumConstants()) {
            if (value.getKey().equalsIgnoreCase(key)) {
                return value;
            }
        }

        return null;
    }

}
