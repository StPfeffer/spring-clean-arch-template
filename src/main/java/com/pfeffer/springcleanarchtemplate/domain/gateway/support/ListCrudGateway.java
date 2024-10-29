package com.pfeffer.springcleanarchtemplate.domain.gateway.support;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;

import java.util.List;

/**
 * A gateway interface that provides CRUD operations for a list of entities
 * of type {@code BO}.
 *
 * @param <BO> the type of the business object (entity) that this gateway
 *             manages.
 * @param <ID> the type of the identifier of the entity that this gateway
 *             manages.
 * @author Mateus Pfeffer
 * @see EntityBO
 * @since 1.0
 */
public interface ListCrudGateway<BO extends EntityBO, ID>
        extends CrudGateway<BO, ID> {

    /**
     * Saves all the given entities.
     *
     * @param bos an {@link Iterable} containing the entities to be saved;
     *            must not be {@code null} and must not contain {@code null}
     *            elements.
     * @return a {@link List} of saved entities; will never be {@code null}.
     * The returned list contains entities with updated state, such as assigned
     * identifiers.
     */
    @Override
    List<BO> saveAll(Iterable<? extends BO> bos);

    /**
     * Retrieves all instances of the entity type managed by the gateway.
     *
     * @return a {@link List} containing all entities of type {@code BO} present
     * in the gateway. If no entities are found, an empty list is returned.
     * The list is never {@code null}.
     */
    @Override
    List<BO> findAll();

    /**
     * Retrieves all instances of the entity type {@code BO} with the specified
     * identifiers.
     * <p>
     * If some or all IDs are not found, the corresponding entities are not
     * included in the result.
     * <p>
     * The order of elements in the result is not guaranteed to match the order
     * of the provided identifiers.
     *
     * @param ids an {@link Iterable} of identifiers; must not be {@code null}
     *            nor contain any {@code null} values.
     * @return a {@link List} containing the entities matching the provided
     * identifiers. The size of the list can be less than the number of given
     * IDs if some entities are not found. The list is guaranteed to be not
     * {@code null}.
     */
    @Override
    List<BO> findByIds(Iterable<ID> ids);

}
