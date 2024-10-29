package com.pfeffer.springcleanarchtemplate.infra.database.pattern;

import com.pfeffer.springcleanarchtemplate.domain.entity.EntityBO;
import com.pfeffer.springcleanarchtemplate.domain.gateway.factory.GatewayFactory;
import com.pfeffer.springcleanarchtemplate.domain.gateway.support.Gateway;
import org.springframework.beans.factory.BeanNotOfRequiredTypeException;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpringGatewayFactory implements GatewayFactory {

    private final ApplicationContext applicationContext;

    /**
     * Constructs a {@code SpringGatewayFactory} instance.
     *
     * @param applicationContext the Spring application context, used to access
     *                           registered beans of type {@link Gateway}
     */
    public SpringGatewayFactory(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Retrieves a new implementation of a {@link Gateway} based on the provided
     * class type.
     *
     * @param type the class type of the {@link Gateway} to retrieve
     * @param <BO> the type of the business object that the Gateway
     *             operates on
     * @param <ID> the type of the identifier used by the Gateway
     * @param <T>  the type of the {@link Gateway}
     * @return a new {@link Gateway} implementation corresponding to the
     * provided class type
     * @see #getGateway(Class, String)
     */
    @Override
    public <BO extends EntityBO, ID, T extends Gateway<BO, ID>> T getGateway(Class<T> type) {
        return applicationContext.getBean(type);
    }

    /**
     * Retrieves a specific implementation of a {@link Gateway} based on the
     * provided class type using the provided name.
     *
     * @param type the class type of the {@link Gateway} to retrieve
     * @param name the qualifier name of the specific {@link Gateway}
     *             implementation
     * @param <BO> the type of the business object that the Gateway operates on
     * @param <ID> the type of the identifier used by the Gateway
     * @param <T>  the type of the {@link Gateway}
     * @return the specific {@link Gateway} implementation corresponding to the
     * provided name
     * @see #getGateway(Class)
     */
    @Override
    public <BO extends EntityBO, ID, T extends Gateway<BO, ID>> T getGateway(Class<T> type, String name) {
        return applicationContext.getBean(name, type);
    }

    /**
     * Retrieves a specific implementation of a {@link Gateway} based on the
     * provided name.
     *
     * @param name the qualifier name of the specific {@link Gateway}
     *             implementation
     * @param <BO> the type of the business object that the Gateway operates on
     * @param <ID> the type of the identifier used by the Gateway
     * @param <T>  the type of the {@link Gateway}
     * @return the specific {@link Gateway} implementation corresponding to the
     * provided name
     */
    @Override
    public <BO extends EntityBO, ID, T extends Gateway<BO, ID>> T getGateway(String name) {
        Object obj = applicationContext.getBean(name);

        if (!(obj instanceof Gateway)) {
            throw new BeanNotOfRequiredTypeException(name, Gateway.class, obj.getClass());
        }

        @SuppressWarnings("unchecked")
        T gateway = (T) obj;

        return gateway;
    }

}