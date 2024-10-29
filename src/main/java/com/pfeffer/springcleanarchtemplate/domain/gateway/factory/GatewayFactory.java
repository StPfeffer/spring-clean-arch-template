package com.pfeffer.springcleanarchtemplate.domain.gateway.factory;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;
import com.pfeffer.springcleanarchtemplate.domain.gateway.support.Gateway;

/**
 * The {@code GatewayFactory} interface provides methods to retrieve
 * implementations of the {@link Gateway} interface. It allows for flexibility
 * in obtaining different gateway implementations based on the specified type
 * and name, supporting the principles of dependency injection and inversion of
 * control.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface GatewayFactory {

    /**
     * Retrieves an implementation of a {@link Gateway} based on the provided
     * class type.
     * <p>
     * This method searches for a single {@link Gateway} implementation that
     * matches the specified class type. If multiple implementations are found,
     * an exception will be thrown to prevent ambiguity. If no implementations
     * are found, an exception will also be thrown.
     *
     * @param type the class type of the {@link Gateway} to retrieve
     * @param <BO> the type of the business object that the Gateway operates on
     * @param <ID> the type of the identifier used by the Gateway
     * @param <T>  the type of the {@link Gateway}
     * @return the {@link Gateway} implementation corresponding to the provided
     * class type
     * @see #getGateway(Class, String)
     */
    <BO extends EntityBO, ID, T extends Gateway<BO, ID>> T getGateway(Class<T> type);

    /**
     * Retrieves a specific implementation of a {@link Gateway} based on the
     * provided class type using the provided name.
     * <p>
     * This method searches for a single {@link Gateway} implementation that
     * matches the specified class type and name. This is useful in scenarios
     * where there are multiple implementations available, and you want to
     * obtain one specifically.
     *
     * @param type the class type of the {@link Gateway} to retrieve
     * @param name the qualifier name of the specific {@link Gateway}
     *             implementation
     * @param <BO> the type of the business object that the Gateway operates on
     * @param <ID> the type of the identifier used by the Gateway
     * @param <T>  the type of the {@link Gateway}
     * @return the specific {@link Gateway} implementation corresponding to the
     * provided class type and name
     * @see #getGateway(Class)
     */
    <BO extends EntityBO, ID, T extends Gateway<BO, ID>> T getGateway(Class<T> type, String name);

    /**
     * Retrieves a specific implementation of a {@link Gateway} based on the
     * provided name.
     * <p>
     * This method fetches a {@link Gateway} implementation using a name without
     * the need to specify the class type explicitly. It is useful when multiple
     * gateway implementations exist, and the desired one can be identified by
     * its name.
     *
     * @param name the qualifier name of the specific {@link Gateway}
     *             implementation
     * @param <BO> the type of the business object that the Gateway operates on
     * @param <ID> the type of the identifier used by the Gateway
     * @param <T>  the type of the {@link Gateway}
     * @return the specific {@link Gateway} implementation corresponding to the
     * provided name
     * @see #getGateway(Class)
     * @see #getGateway(Class, String)
     */
    <BO extends EntityBO, ID, T extends Gateway<BO, ID>> T getGateway(String name);

}

