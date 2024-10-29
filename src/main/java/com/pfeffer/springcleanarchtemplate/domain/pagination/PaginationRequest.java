package com.pfeffer.springcleanarchtemplate.domain.pagination;

import com.pfeffer.springcleanarchtemplate.domain.utils.Validation;

import java.io.Serial;
import java.util.Arrays;

/**
 * A concrete implementation of {@link AbstractPaginationRequest} that supports
 * pagination with optional sorting parameters. This class provides mechanisms
 * to create paginated requests, move between pages, and apply sorting options.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class PaginationRequest extends AbstractPaginationRequest {

    @Serial
    private static final long serialVersionUID = 4916413161028603554L;

    private final String[] sort;

    /**
     * Constructs a new {@link PaginationRequest} with the specified page
     * number, page size, and sorting criteria.
     *
     * @param pageNumber zero-based page number, must not be negative.
     * @param pageSize   the size of the page to be returned, must be greater
     *                   than 0.
     * @param sort       the sorting criteria, must not be {@code null}.
     * @throws IllegalArgumentException if sort criteria is {@code null}.
     */
    protected PaginationRequest(int pageNumber, int pageSize, String[] sort) {
        super(pageNumber, pageSize);

        Validation.notNull(sort, "Sort must not be null");

        this.sort = sort;
    }

    /**
     * Creates a new unsorted {@link PaginationRequest} instance.
     *
     * @param pageNumber zero-based page number, must not be negative.
     * @param pageSize   the size of the page to be returned, must be greater
     *                   than 0.
     * @return a new {@link PaginationRequest} instance with no sorting applied.
     */
    public static PaginationRequest of(int pageNumber, int pageSize) {
        return of(pageNumber, pageSize, new String[0]);
    }

    /**
     * Creates a new {@link PaginationRequest} with the specified page number,
     * page size, and sort parameters applied.
     *
     * @param pageNumber zero-based page number, must not be negative.
     * @param pageSize   the size of the page to be returned, must be greater
     *                   than 0.
     * @param sort       the sorting criteria, must not be {@code null}. If no
     *                   sort is needed, use {@code new String[0]}.
     * @return a new {@link PaginationRequest} instance with sorting applied.
     * @throws IllegalArgumentException if the sort criteria is {@code null}.
     */
    public static PaginationRequest of(int pageNumber, int pageSize, String[] sort) {
        return new PaginationRequest(pageNumber, pageSize, sort);
    }

    /**
     * Returns the sorting criteria for this pagination request.
     *
     * @return an array of strings representing the sort criteria.
     */
    public String[] getSort() {
        return sort;
    }

    @Override
    public Pagination next() {
        return new PaginationRequest(getPageNumber() + 1, getPageSize(), getSort());
    }

    @Override
    public Pagination previous() {
        return getPageNumber() == 0 ? this : new PaginationRequest(getPageNumber() - 1, getPageSize(), getSort());
    }

    @Override
    public Pagination first() {
        return new PaginationRequest(0, getPageSize(), getSort());
    }

    @Override
    public Pagination withPage(int pageNumber) {
        return new PaginationRequest(pageNumber, getPageSize(), getSort());
    }

    /**
     * Creates a new {@link PaginationRequest} instance with the specified sort
     * criteria applied, maintaining the current page number and page size.
     *
     * @param sort the sorting criteria, must not be {@code null}.
     * @return a new {@link PaginationRequest} instance with updated sort criteria.
     * @throws IllegalArgumentException if the sort criteria is {@code null}.
     */
    public PaginationRequest withSort(String... sort) {
        return new PaginationRequest(getPageNumber(), getPageSize(), sort);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof PaginationRequest that)) {
            return false;
        }

        return super.equals(that) && Arrays.equals(sort, that.sort);
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + Arrays.hashCode(sort);
    }

    @Override
    public String toString() {
        return String.format("Page request [number: %d, size %d, sort: %s]", getPageNumber(), getPageSize(), Arrays.toString(sort));
    }

}
