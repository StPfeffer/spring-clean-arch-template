package com.pfeffer.springcleanarchtemplate.domain.pagination;

import java.util.Arrays;

/**
 * Represents an unpaged pagination model with optional sorting capabilities.
 * This class implements the {@link Pagination} interface but is designed to be
 * unpaged, meaning it does not support paging operations like navigating
 * through pages. Instead, it optionally supports sorting.
 *
 * @param getSort The sorting criteria represented as an array of strings. If no
 *                sorting is specified, an empty array is used.
 * @author Mateus Pfeffer
 * @since 1.0
 */
record UnpagedContent(String[] getSort) implements Pagination {

    /**
     * A static instance representing an unpaged and unsorted state.
     */
    private static final Pagination UNSORTED = new UnpagedContent(new String[0]);

    /**
     * Creates a new {@link Pagination} instance with sorting if the provided
     * array is not {@code null} and not empty. If the sorting array is
     * {@code null} or empty, returns the unsorted instance.
     *
     * @param sort An array of sorting criteria.
     * @return A {@link Pagination} instance with sorting if the array is not
     * empty, otherwise returns {@link #UNSORTED}.
     */
    static Pagination sorted(String[] sort) {
        return sort != null && sort.length > 0 ? new UnpagedContent(sort) : UNSORTED;
    }

    /**
     * Indicates whether the content is paged. This implementation always
     * returns {@code false} since this class represents unpaged content.
     *
     * @return {@code false}, as this content is not paged.
     */
    @Override
    public boolean isPaged() {
        return false;
    }

    /**
     * Returns the previous page or the first page. Since this implementation is
     * unpaged, it always returns itself.
     *
     * @return {@code this}, as there are no pages in this implementation.
     */
    @Override
    public Pagination previousOrFirst() {
        return this;
    }

    /**
     * Returns the next page. Since this implementation is unpaged, it always
     * returns itself.
     *
     * @return {@code this}, as there are no pages in this implementation.
     */
    @Override
    public Pagination next() {
        return this;
    }

    /**
     * Checks if there is a previous page. Since this implementation is unpaged,
     * it always returns {@code false}.
     *
     * @return {@code false}, as there are no pages in this implementation.
     */
    @Override
    public boolean hasPrevious() {
        return false;
    }

    /**
     * Gets the page size. Since this implementation is unpaged, calling this
     * method throws an {@link UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException as this content is not paged.
     */
    @Override
    public int getPageSize() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the page number. Since this implementation is unpaged, calling this
     * method throws an {@link UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException as this content is not paged.
     */
    @Override
    public int getPageNumber() {
        throw new UnsupportedOperationException();
    }

    /**
     * Gets the offset for pagination. Since this implementation is unpaged,
     * calling this method throws an {@link UnsupportedOperationException}.
     *
     * @throws UnsupportedOperationException as this content is not paged.
     */
    @Override
    public long getOffset() {
        throw new UnsupportedOperationException();
    }

    /**
     * Returns the first page. Since this implementation is unpaged,
     * it always returns itself.
     *
     * @return {@code this}, as there are no pages in this implementation.
     */
    @Override
    public Pagination first() {
        return this;
    }

    /**
     * Attempts to set the pagination to a specific page number. If the page
     * number is 0, it returns itself. Otherwise, it throws an {@link
     * UnsupportedOperationException} since this implementation is unpaged.
     *
     * @param pageNumber The page number to set.
     * @return {@code this}, if the page number is 0.
     * @throws UnsupportedOperationException if the page number is not 0.
     */
    @Override
    public Pagination withPage(int pageNumber) {

        if (pageNumber == 0) {
            return this;
        }

        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof UnpagedContent unpaged)) {
            return false;
        }

        return Arrays.equals(getSort, unpaged.getSort);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(getSort);
    }

}
