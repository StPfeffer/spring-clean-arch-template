package com.pfeffer.springcleanarchtemplate.domain.entity;

import java.time.temporal.TemporalAccessor;

/**
 * Interface representing auditable entities with creation and update timestamps.
 * <p>
 * This interface provides methods for getting and setting the creation and
 * update timestamps, which are represented by a type extending {@link
 * TemporalAccessor}.
 *
 * @param <T> the type of the timestamp, which must extend {@link
 *            TemporalAccessor}
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface Auditable<T extends TemporalAccessor> {

    /**
     * Returns the timestamp of when the entity was created.
     *
     * @return the creation timestamp, will never be {@code null}
     */
    T getCreatedAt();

    /**
     * Sets the timestamp of when the entity was created.
     *
     * @param createdAt the creation timestamp to set, should not be
     *                  {@code null}
     */
    void setCreatedAt(T createdAt);

    /**
     * Returns the timestamp of when the entity was last updated.
     *
     * @return the last update timestamp, or {@code null} if not set
     */
    T getUpdatedAt();

    /**
     * Sets the timestamp of when the entity was last updated.
     *
     * @param updatedAt the last update timestamp to set
     */
    void setUpdatedAt(T updatedAt);

}
