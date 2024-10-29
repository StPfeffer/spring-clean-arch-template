package com.pfeffer.springcleanarchtemplate.domain.pagination;

import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;

import java.util.Collections;
import java.util.function.Function;

/**
 * Represents the content of a page, including the total elements and pages
 * available. It extends {@link SliceContent} to provide additional
 * pagination information and mapping capabilities.
 *
 * @param <T> the type of elements contained in the page.
 * @author Mateus Pfeffer
 * @since 1.0
 */
public interface PageContent<T> extends SliceContent<T> {

    /**
     * Returns an empty {@link PageContent} instance with unpaged pagination.
     *
     * @param <T> the type of the content to be held in the page.
     * @return an empty {@link PageContent} instance.
     */
    static <T> PageContent<T> empty() {
        return empty(Pagination.unpaged());
    }

    /**
     * Returns an empty {@link PageContent} instance with the given pagination.
     *
     * @param pagination the {@link Pagination} to associate with the empty page.
     * @param <T>        the type of the content to be held in the page.
     * @return an empty {@link PageContent} instance with the given pagination.
     */
    static <T> PageContent<T> empty(Pagination pagination) {
        return new SimplePageContent<>(Collections.emptyList(), pagination, 0);
    }

    /**
     * Gets the total number of pages available based on the current pagination
     * and total elements.
     *
     * @return the total number of pages available.
     */
    int getTotalPages();

    /**
     * Gets the total number of elements across all pages.
     *
     * @return the total number of elements.
     */
    long getTotalElements();

    /**
     * Maps the content of the current {@link PageContent} using the provided
     * {@link Function}. The result is a new {@link PageContent} instance with
     * the transformed content.
     *
     * @param converter a {@link Function} to convert each element; must not be
     *                  {@code null}.
     * @param <U>       the type of elements in the new {@link PageContent}.
     * @return a new {@link PageContent} instance with the mapped content.
     * @throws InternalValidationException if the converter is {@code null}.
     */
    <U> PageContent<U> map(Function<? super T, ? extends U> converter);

}
