package com.pfeffer.springcleanarchtemplate.domain.gateway.support;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;
import com.pfeffer.springcleanarchtemplate.domain.pagination.PageContent;

import java.util.List;

/**
 * A generic gateway interface for retrieving paginated entities from a
 * database.
 * <p>
 * This an extension to {@link PagingAndSortingGateway} returning {@link List}
 * instead of {@link Iterable} where applicable.
 *
 * @param <BO> the type of the business object (entity) that this gateway
 *             manages.
 * @author Mateus Pfeffer
 * @see EntityBO
 * @see PageContent
 * @since 1.0
 */
public interface ListPagingAndSortingGateway<BO extends EntityBO>
        extends PagingAndSortingGateway<BO> {

    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort optional parameters in the format of "field1,asc" or
     *             "field2,desc"; must not be {@code null}
     * @return all entities sorted by the given options
     */
    @Override
    List<BO> findAll(String... sort);

}
