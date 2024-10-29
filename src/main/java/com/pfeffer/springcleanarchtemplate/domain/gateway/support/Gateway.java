package com.pfeffer.springcleanarchtemplate.domain.gateway.support;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;

/**
 * A generic gateway interface for performing CRUD operations and retrieving
 * entities from a database. This interface provides methods for managing
 * entities based on their identifiers and properties, and for listing and
 * paginating entities.
 * <p>
 * Note: This interface is designed to work with both JPA-based databases
 * (e.g., PostgreSQL, MySQL) and non-JPA databases (e.g., MongoDB) by extending
 * the relevant gateway interfaces for each implementation.
 *
 * @param <BO> the type of the business object (entity) that this gateway
 *             manages.
 * @param <ID> the type of the identifier of the entity that this gateway
 *             manages.
 * @author Mateus Pfeffer
 * @see EntityBO
 * @since 1.0
 */
public interface Gateway<BO extends EntityBO, ID>
        extends ListCrudGateway<BO, ID>, ListPagingAndSortingGateway<BO> {

}
