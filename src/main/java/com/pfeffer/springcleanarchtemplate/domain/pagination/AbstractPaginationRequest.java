package com.pfeffer.springcleanarchtemplate.domain.pagination;

import com.pfeffer.springcleanarchtemplate.domain.utils.Validation;

import java.io.Serial;
import java.io.Serializable;

/**
 * An abstract implementation of the {@link Pagination} interface, providing a
 * base for pagination requests with essential functionalities such as
 * calculating offsets and checking pagination constraints. It is designed for
 * paginated content where pages are zero-indexed.
 *
 * @author Mateus Pfeffer
 * @implNote The pagination is zero-based; thus, providing {@code 0} as
 * the {@code pageNumber} corresponds to the first page.
 * @see Pagination
 * @since 1.0
 */
public abstract class AbstractPaginationRequest
        implements Pagination, Serializable {

    @Serial
    private static final long serialVersionUID = -5270894435699290173L;

    private final int pageNumber;

    private final int pageSize;

    /**
     * Creates a new {@link AbstractPaginationRequest}. Pages are zero indexed,
     * thus providing 0 for {@code pageNumber} will return the first page.
     *
     * @param pageNumber zero-based page number, must not be negative
     * @param pageSize   the size of the page to be returned, must be greater
     *                   than 0.
     * @throws IllegalArgumentException if {@code pageNumber} is negative or
     *                                  {@code pageSize} is less than or equal
     *                                  to zero.
     */
    protected AbstractPaginationRequest(int pageNumber, int pageSize) {
        Validation.isTrue(pageNumber >= 0, "Page number must be greater than or equal to zero");
        Validation.isTrue(pageSize > 0, "Page size must be greater than zero");

        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public long getOffset() {
        return (long) pageNumber * (long) pageSize;
    }

    @Override
    public boolean hasPrevious() {
        return pageNumber > 0;
    }

    @Override
    public Pagination previousOrFirst() {
        return hasPrevious() ? previous() : first();
    }

    /**
     * Returns a {@link Pagination} instance representing the previous page.
     * If the current page is the first one, an exception may be thrown
     * depending on the implementation.
     *
     * @return a new {@link Pagination} instance for the previous page.
     */
    public abstract Pagination previous();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;

        result = prime * result + pageNumber;
        result = prime * result + pageSize;

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        AbstractPaginationRequest other = (AbstractPaginationRequest) obj;

        return pageNumber == other.pageNumber && pageSize == other.pageSize;
    }

}
