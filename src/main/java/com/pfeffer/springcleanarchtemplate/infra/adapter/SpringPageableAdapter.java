package com.pfeffer.springcleanarchtemplate.infra.adapter;

import com.pfeffer.springcleanarchtemplate.domain.pagination.Pagination;
import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

/**
 * Adapter for converting Spring's {@link Pageable} interface to the
 * domain-specific {@link Pagination} and vice versa.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class SpringPageableAdapter implements Pagination {

    private final Pageable pageable;

    public SpringPageableAdapter(Pageable pageable) {
        InternalValidation.notNull(pageable, "Pageable must not be null");

        this.pageable = pageable;
    }

    /**
     * Converts a domain-specific {@link Pagination} to a Spring {@link Pageable}.
     *
     * @param pagination the domain-specific pagination instance
     * @return a Spring {@link Pageable} corresponding to the provided pagination
     */
    public static Pageable toPageable(Pagination pagination) {
        InternalValidation.notNull(pagination, "Pagination must not be null");

        return PageRequest.of(
                pagination.getPageNumber(),
                pagination.getPageSize(),
                Sort.by(pagination.getSort())
        );
    }

    @Override
    public int getPageNumber() {
        return pageable.getPageNumber();
    }

    @Override
    public int getPageSize() {
        return pageable.getPageSize();
    }

    @Override
    public long getOffset() {
        return pageable.getOffset();
    }

    @Override
    public String[] getSort() {
        return pageable.getSort().stream()
                .map(order -> order.getProperty() + " " + order.getDirection().name())
                .toArray(String[]::new);
    }

    @Override
    public Pagination next() {
        return new SpringPageableAdapter(pageable.next());
    }

    @Override
    public Pagination previousOrFirst() {
        return new SpringPageableAdapter(pageable.previousOrFirst());
    }

    @Override
    public Pagination first() {
        return new SpringPageableAdapter(pageable.first());
    }

    @Override
    public Pagination withPage(int pageNumber) {
        return new SpringPageableAdapter(pageable.withPage(pageNumber));
    }

    @Override
    public boolean hasPrevious() {
        return pageable.hasPrevious();
    }

}
