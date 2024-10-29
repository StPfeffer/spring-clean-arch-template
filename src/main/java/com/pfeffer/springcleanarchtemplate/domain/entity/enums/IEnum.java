package com.pfeffer.springcleanarchtemplate.domain.entity.enums;

import com.pfeffer.springcleanarchtemplate.domain.utils.type.EnumUtils;

/**
 * Interface representing an enumeration with a unique key.
 * <p>
 * This interface provides a contract for enums that include a unique key for
 * identifying each enum value. It also includes methods for parsing enums by
 * their key and checking if a key is contained within the enum.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface IEnum {

    /**
     * Parses and retrieves an enum instance by its unique key.
     * <p>
     * This static method uses the provided key and class type to find and
     * return the corresponding enum instance. If no matching enum is found,
     * it returns {@code null}.
     *
     * @param key   the unique key associated with the enum instance
     * @param clazz the class type of the enum to parse
     * @return the {@link IEnum} instance matching the key, or {@code null} if
     * no match is found
     */
    static <E extends IEnum> E parseByKey(String key, Class<E> clazz) {
        return EnumUtils.parseByKey(clazz, key);
    }

    /**
     * Retrieves the unique key that identifies this enum.
     * <p>
     * This key serves as a unique identifier for the specific enum and can
     * be used to differentiate between various enums.
     *
     * @return a {@link String} representing the unique enum key.
     */
    String getKey();

}