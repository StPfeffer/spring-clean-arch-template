package com.pfeffer.springcleanarchtemplate.domain.entity;

import com.pfeffer.springcleanarchtemplate.domain.exception.ErrorCode;
import com.pfeffer.springcleanarchtemplate.domain.exception.LocalizedException;

import java.io.Serial;
import java.io.Serializable;

/**
 * Abstract base class for business objects (BOs) representing entities.
 * This class defines the core methods that must be implemented by all
 * entity business objects, ensuring that each entity has an ID and a
 * validation mechanism.
 * <p>
 * Provides convenience methods for handling the entity ID as a String.
 * <p>
 * <b>Usage:</b>
 * <pre>
 *     {@code
 *     public class VehicleBO extends EntityBO {
 *         private Long id;
 *
 *         @Override
 *         protected void validate() throws KmFacilException {
 *             // Validation logic here
 *         }
 *
 *         @Override
 *         protected Long getId() {
 *             return id;
 *         }
 *
 *         @Override
 *         protected void setId(Long id) {
 *             this.id = id;
 *         }
 *     }
 *     }
 * </pre>
 * <p>
 * Each subclass must provide implementations for retrieving, setting, and
 * validating the ID. Additionally, the ID can be handled as a String via
 * {@code getStringId()} and {@code setStringId(String id)}.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public abstract class EntityBO implements Serializable {

    @Serial
    private static final long serialVersionUID = -7685221178828844466L;

    /**
     * Retrieves the ID of the entity.
     *
     * @return the ID of the entity as a {@link Long}
     * @see #getStringId()
     */
    public abstract Long getId();

    /**
     * Validates the entity to ensure it meets all business rules and
     * constraints.
     * <p>
     * If validation fails, this method should throw a {@link LocalizedException}
     * with the appropriate {@link ErrorCode}.
     *
     * @throws LocalizedException if the entity does not pass validation
     */
    public abstract void validate() throws LocalizedException;

    /**
     * Retrieves the ID of the entity as a String.
     * <p>
     * This is a convenience method that converts the Long ID to its String
     * representation.
     *
     * @return the ID of the entity as a {@link String}
     * @see #getId()
     */
    public String getStringId() {
        return String.valueOf(getId());
    }

}
