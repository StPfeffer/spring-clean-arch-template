package com.pfeffer.springcleanarchtemplate.domain.gateway.support;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;
import com.pfeffer.springcleanarchtemplate.domain.pagination.PageContent;
import com.pfeffer.springcleanarchtemplate.domain.pagination.Pagination;

/**
 * A generic gateway interface for performing paging and sorting operations
 * on entities of type {@code BO}. This interface allows for retrieving
 * all entities with optional sorting, as well as providing paginated
 * results based on specified pagination parameters.
 *
 * @param <BO> the type of the business object (entity) that this gateway
 *             manages.
 * @author Mateus Pfeffer
 * @see EntityBO
 * @since 1.0
 */
public interface PagingAndSortingGateway<BO extends EntityBO> {

    /**
     * Returns all entities sorted by the given options.
     * <p>
     * The sorting parameters should be provided in the format of "field1,asc"
     * or "field2,desc", where "asc" indicates ascending order and "desc"
     * indicates descending order.
     *
     * @param sort optional sorting parameters in the format of "field1,asc" or
     *             "field2,desc";
     * @return all entities sorted by the given options
     */
    Iterable<BO> findAll(String... sort);

    /**
     * Retrieves a paginated list of all entities, optionally sorted by
     * specified fields.
     *
     * @param pagination The pagination parameters that define the page number,
     *                   the size of the page, and optional sorting criteria.
     * @return A {@link PageContent} object containing the paginated list of
     * businesses objects representing the entities.
     */
    PageContent<BO> findAll(Pagination pagination);

}
