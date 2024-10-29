package com.pfeffer.springcleanarchtemplate.domain.entity;

/**
 * Interface representing an identifier for an object.
 *
 * @param <T> the type of the identifier
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface Identifiable<T> {

    /**
     * Returns the identifier of the object.
     *
     * @return the identifier, which may be {@code null} if the object is new
     */
    T getId();

}
