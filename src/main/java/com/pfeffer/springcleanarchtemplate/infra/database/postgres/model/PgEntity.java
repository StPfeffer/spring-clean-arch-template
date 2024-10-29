package com.pfeffer.springcleanarchtemplate.infra.database.postgres.model;

import com.pfeffer.springcleanarchtemplate.domain.entity.Identifiable;

import java.io.Serializable;

/**
 * Interface representing a PostgreSQL entity with a Long type identifier.
 * <p>
 * This interface extends {@link Identifiable} with a Long ID and ensures that
 * all entities implementing this interface can be serialized.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface PgEntity
        extends Identifiable<Long>, Serializable {

    /**
     * Returns the identifier of the entity.
     *
     * @return the entity ID, which may be {@code null} if the entity is new
     */
    @Override
    Long getId();

    /**
     * Sets the identifier of the entity.
     *
     * @param id the new ID of the entity
     */
    void setId(Long id);

}
