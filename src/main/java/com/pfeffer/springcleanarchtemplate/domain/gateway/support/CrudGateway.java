package com.pfeffer.springcleanarchtemplate.domain.gateway.support;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;
import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;

import java.util.Optional;

/**
 * Interface for generic CRUD operations on a gateway for a specific type.
 *
 * @param <BO> the type of the business object (entity) that this gateway
 *             manages.
 * @param <ID> the type of the identifier of the entity that this gateway
 *             manages.
 * @author Mateus Pfeffer
 * @see EntityBO
 * @since 1.0
 */
public interface CrudGateway<BO extends EntityBO, ID> {

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@code null}.
     * @return the entity with the given id or {@link Optional#empty()} if none
     * found.
     * @throws InternalValidationException if the id is {@code null}.
     */
    Optional<BO> findById(ID id);

    /**
     * Saves a given entity. Use the returned instance for further operations as
     * the save operation might have changed the entity instance completely.
     *
     * @param bo must not be {@code null}.
     * @return the saved entity; will never be {@code null}.
     * @throws IllegalArgumentException in case the given {@code entity} is
     *                                  {@code null}.
     */
    BO save(BO bo);

    /**
     * Saves all given entities.
     *
     * @param bos must not be {@code null} nor must it contain {@code null}.
     * @return the saved entities; will never be {@code null}. The returned
     * {@link Iterable} will have the same size as the {@link Iterable} passed
     * as an argument.
     * @throws IllegalArgumentException in case the given {@link Iterable
     *                                  entities} or one of its entities is
     *                                  {@code null}.
     */
    Iterable<BO> saveAll(Iterable<? extends BO> bos);

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@code null}.
     * @return {@code true} if an entity with the given id exists, {@code false}
     * otherwise.
     * @throws InternalValidationException if the id is {@code null}.
     */
    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    Iterable<BO> findAll();

    /**
     * Returns all instances of the type {@code BO} with the given IDs.
     * <p>
     * If some or all ids are not found, no entities are returned for these IDs.
     * <p>
     * Note that the order of elements in the result is not guaranteed.
     *
     * @param ids must not be {@code null} nor contain any {@code null} values.
     * @return guaranteed to be not {@code null}. The size can be equal or less
     * than the number of given {@code ids}.
     * @throws IllegalArgumentException in case the given {@link Iterable ids}
     *                                  or one of its items is {@code null}.
     */
    Iterable<BO> findByIds(Iterable<ID> ids);

    /**
     * Returns the number of entities available.
     *
     * @return the number of entities.
     */
    long count();

    /**
     * Deletes the entity with the given id.
     * <p>
     * If the entity is not found in the persistence store it is silently ignored.
     *
     * @param id must not be {@code null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@code null}
     */
    void deleteById(ID id);

    /**
     * Deletes a given entity.
     *
     * @param bo must not be {@code null}.
     * @throws IllegalArgumentException in case the given entity is {@code null}.
     */
    void delete(BO bo);

    /**
     * Deletes all instances of the type {@code BO} with the given IDs.
     * <p>
     * Entities that aren't found in the persistence store are silently ignored.
     *
     * @param ids must not be {@code null}. Must not contain {@code null}
     *            elements.
     * @throws IllegalArgumentException in case the given {@code ids} or one of
     *                                  its elements is {@code null}.
     */
    void deleteAllById(Iterable<? extends ID> ids);

    /**
     * Deletes the given entities.
     *
     * @param bos must not be {@code null}. Must not contain {@code null}
     *            elements.
     * @throws IllegalArgumentException in case the given {@code entities} or
     *                                  one of its entities is {@code null}.
     */
    void deleteAll(Iterable<? extends BO> bos);

    /**
     * Deletes all entities.
     */
    void deleteAll();

}
