package com.pfeffer.springcleanarchtemplate.domain.entity.vo;

import java.time.LocalDateTime;

/**
 * Value Object representing the date and time when an entity was created.
 * <p>
 * This class encapsulates a {@link LocalDateTime} instance, which stores the
 * creation timestamp. If the provided {@code value} is {@code null}, the
 * current date and time will be used, as determined by {@link LocalDateTime#now()}.
 * <p>
 * The purpose of this class is to provide a type-safe and consistent way to
 * handle the creation timestamp of entities, ensuring that a non-null value is
 * always available.
 *
 * @param value the {@link LocalDateTime} representing the creation timestamp.
 *              If {@code null}, the current date and time will be used
 * @author Mateus Pfeffer
 * @since 1.0
 */
public record CreatedAtVO(LocalDateTime value) {

    /**
     * Constructs a new {@link CreatedAtVO} instance.
     * <p>
     * If the provided {@code value} is {@code null}, this constructor assigns
     * the current date and time using {@link LocalDateTime#now()} to the
     * {@code value} field. Otherwise, it assigns the provided {@code value}.
     *
     * @param value the {@link LocalDateTime} representing the creation
     *              timestamp, or {@code null} to use the current date and time
     */
    public CreatedAtVO(LocalDateTime value) {
        this.value = value != null ? value : LocalDateTime.now();
    }

}
