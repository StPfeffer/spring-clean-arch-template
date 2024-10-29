package com.pfeffer.springcleanarchtemplate.domain.pagination;

import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;

import java.util.List;
import java.util.function.Function;

/**
 * Represents a slice of paginated data, providing information about the slice
 * and allowing navigation to adjacent slices. A {@link SliceContent} indicates
 * if there are previous or next slices available and provides methods to
 * obtain the necessary {@link Pagination} details for fetching them.
 *
 * @param <T> the type of elements in this slice.
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface SliceContent<T> {

    /**
     * Returns the number of the current slice. The number is always
     * non-negative and represents the zero-based index of this slice.
     *
     * @return the number of the current {@link SliceContent}.
     */
    int getNumber();

    /**
     * Returns the size of this {@link SliceContent}, which indicates the
     * maximum number of elements it can contain.
     *
     * @return the size of the {@link SliceContent}.
     */
    int getSize();

    /**
     * Returns the number of elements currently in this {@link SliceContent}.
     *
     * @return the number of elements currently in this {@link SliceContent}.
     */
    int getNumberOfElements();

    /**
     * Returns the page content as a {@link List} of elements of type {@code T}.
     *
     * @return a {@link List} containing the elements in this slice.
     */
    List<T> getContent();

    /**
     * Indicates whether this {@link SliceContent} contains any elements.
     *
     * @return {@code true} if the slice has content; {@code false} otherwise.
     */
    boolean hasContent();

    /**
     * Returns the sorting parameters applied to this {@link SliceContent}.
     *
     * @return an array of strings representing the sort criteria.
     */
    String[] getSort();

    /**
     * Indicates whether the current {@link SliceContent} is the first slice.
     *
     * @return {@code true} if this is the first slice; {@code false} otherwise.
     */
    boolean isFirst();

    /**
     * Indicates whether the current {@link SliceContent} is the last slice.
     *
     * @return {@code true} if this is the last slice; {@code false} otherwise.
     */
    boolean isLast();

    /**
     * Indicates if there is a next {@link SliceContent} available.
     *
     * @return {@code true} if a next slice exists; {@code false} otherwise.
     */
    boolean hasNext();

    /**
     * Indicates if there is a previous {@link SliceContent} available.
     *
     * @return {@code true} if a previous slice exists; {@code false} otherwise.
     */
    boolean hasPrevious();

    /**
     * Returns the {@link Pagination} object that was used to request the
     * current {@link SliceContent}.
     *
     * @return the {@link Pagination} for the current slice.
     */
    default Pagination getPagination() {
        return PaginationRequest.of(getNumber(), getSize(), getSort());
    }

    /**
     * Returns the {@link Pagination} object needed to request the next
     * {@link SliceContent}. Returns {@link Pagination#unpaged()} if the
     * current slice is the last one. Clients should check {@link #hasNext()}
     * before calling this method to ensure a next slice exists.
     *
     * @return the {@link Pagination} for the next slice.
     * @see #nextOrLastPagination()
     */
    Pagination nextPagination();

    /**
     * Returns the {@link Pagination} object needed to request the previous
     * {@link SliceContent}. Returns {@link Pagination#unpaged()} if the current
     * slice is the first one. Clients should check {@link #hasPrevious()}
     * before calling this method to ensure a previous slice exists.
     *
     * @return the {@link Pagination} for the previous slice.
     * @see #previousPagination()
     */
    Pagination previousPagination();

    /**
     * Maps the content of the current {@link SliceContent} using the provided
     * {@link Function}. The result is a new {@link SliceContent} instance with
     * the transformed content.
     *
     * @param converter a {@link Function} to convert each element; must not be
     *                  {@code null}.
     * @param <U>       the type of elements in the new {@link SliceContent}.
     * @return a new {@link SliceContent} instance with the mapped content.
     * @throws InternalValidationException if the converter is {@code null}.
     */
    <U> SliceContent<U> map(Function<? super T, ? extends U> converter);

    /**
     * Returns the {@link Pagination} for the next slice if available, or the
     * current slice's pagination if this is the last slice.
     *
     * @return the {@link Pagination} for the next or current slice.
     */
    default Pagination nextOrLastPagination() {
        return hasNext() ? nextPagination() : getPagination();
    }

    /**
     * Returns the {@link Pagination} for the previous slice if available, or
     * the current slice's pagination if this is the first slice.
     *
     * @return the {@link Pagination} for the previous or current slice.
     */
    default Pagination previousOrFirstPagination() {
        return hasPrevious() ? previousPagination() : getPagination();
    }

}
