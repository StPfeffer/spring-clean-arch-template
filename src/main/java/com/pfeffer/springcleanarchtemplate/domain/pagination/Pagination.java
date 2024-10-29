package com.pfeffer.springcleanarchtemplate.domain.pagination;

import java.util.Optional;

/**
 * Represents pagination information, including page number and size,
 * allowing navigation between pages. This interface provides methods
 * to access and manipulate pagination details for retrieving paginated data.
 *
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface Pagination {

    /**
     * Returns an unpaged {@link Pagination} instance with no sorting applied.
     * This instance indicates that no pagination constraints are enforced.
     *
     * @return an unpaged {@link Pagination} instance.
     */
    static Pagination unpaged() {
        return unpaged(new String[0]);
    }

    /**
     * Returns an unpaged {@link Pagination} instance with the specified sorting
     * criteria. This instance indicates no pagination but may apply the given
     * sort order to the content.
     *
     * @param sort an array of sorting criteria as {@link String}; must not be
     *             {@code null}.
     * @return an unpaged {@link Pagination} instance with the specified sorting.
     */
    static Pagination unpaged(String[] sort) {
        return UnpagedContent.sorted(sort);
    }

    /**
     * Creates a new {@link Pagination} instance for the first page
     * (page number {@code 0}) with the given {@code pageSize}.
     *
     * @param pageSize the size of the page to be returned, must be greater
     *                 than 0.
     * @return a new {@link Pagination} instance for the first page.
     */
    static Pagination ofSize(int pageSize) {
        return PaginationRequest.of(0, pageSize);
    }

    /**
     * Checks if the current {@link Pagination} contains pagination
     * information.
     *
     * @return {@code true} if pagination is enabled; otherwise, {@code false}.
     */
    default boolean isPaged() {
        return true;
    }

    /**
     * Checks if the current {@link Pagination} does not contain pagination
     * information.
     *
     * @return {@code true} if pagination is not enabled; otherwise, {@code
     * false}.
     */
    default boolean isUnpaged() {
        return !isPaged();
    }

    /**
     * Gets the current page number.
     *
     * @return the number of the page to be returned.
     * @throws UnsupportedOperationException if pagination is not enabled
     *                                       ({@link #isUnpaged()}).
     */
    int getPageNumber();

    /**
     * Gets the number of items per page.
     *
     * @return the number of items to be returned for the current page.
     * @throws UnsupportedOperationException if pagination is not enabled
     *                                       ({@link #isUnpaged()}).
     */
    int getPageSize();

    /**
     * Calculates and returns the offset based on the current page and page
     * size.
     *
     * @return the offset value calculated based on the page number and size.
     * @throws UnsupportedOperationException if pagination is not enabled
     *                                       ({@link #isUnpaged()}).
     */
    long getOffset();

    /**
     * Returns the sorting parameters of the page.
     *
     * @return an array of the sorting parameters of the page (e.g., "name,asc")
     */
    String[] getSort();

    /**
     * Returns a {@link Pagination} instance representing the next page.
     *
     * @return a new {@link Pagination} instance for the next page.
     */
    Pagination next();

    /**
     * Returns the previous {@link Pagination} instance or the first page
     * if the current instance already represents the first page.
     *
     * @return a new {@link Pagination} instance for the previous or first page.
     */
    Pagination previousOrFirst();

    /**
     * Returns a {@link Pagination} instance representing the first page.
     *
     * @return a new {@link Pagination} instance for the first page.
     */
    Pagination first();

    /**
     * Creates a new {@link Pagination} instance with the specified {@code
     * pageNumber}.
     *
     * @param pageNumber the page number for the new pagination instance.
     * @return a new {@link PaginationRequest} instance with the specified page.
     * @throws UnsupportedOperationException if pagination is not enabled
     *                                       ({@link #isUnpaged()}) and the
     *                                       {@code pageNumber} is not zero.
     */
    Pagination withPage(int pageNumber);

    /**
     * Checks if there is a previous page available before the current one.
     * Returns {@code false} if the current {@link Pagination} is already the
     * first page.
     *
     * @return {@code true} if a previous page is available; otherwise, {@code
     * false}.
     */
    boolean hasPrevious();

    /**
     * Converts the current {@link Pagination} to an {@link Optional}.
     * If the pagination is unpaged, it returns an empty {@link Optional}.
     *
     * @return an {@link Optional} containing the current {@link Pagination}
     * instance, or an empty {@link Optional} if pagination is not enabled.
     */
    default Optional<Pagination> toOptional() {
        return isUnpaged() ? Optional.empty() : Optional.of(this);
    }

}
