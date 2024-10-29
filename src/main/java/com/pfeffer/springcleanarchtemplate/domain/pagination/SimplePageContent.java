package com.pfeffer.springcleanarchtemplate.domain.pagination;

import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;

/**
 * Implementation of {@link PageContent} that provides detailed pagination
 * information, including the total number of elements and pages. It extends
 * {@link ChunkContent} to support chunked content management with additional
 * pagination capabilities.
 * <p>
 * This class is designed to be used when the total number of elements is known,
 * and it provides methods to map the content to another type while preserving
 * pagination details.
 *
 * @param <T> the type of elements contained in the page
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class SimplePageContent<T> extends ChunkContent<T> implements PageContent<T> {

    @Serial
    private static final long serialVersionUID = -3086735011712927299L;

    private final long totalElements;

    /**
     * Constructs a new {@link SimplePageContent} with the given content, pagination details, and total number of elements.
     *
     * @param content       the list of elements in this page; must not be null
     * @param pagination    the pagination details for this page; must not be null
     * @param totalElements the total number of elements in the entire dataset
     */
    public SimplePageContent(Collection<T> content, Pagination pagination, long totalElements) {
        super(content, pagination);
        this.totalElements = pagination.toOptional().filter(it -> !content.isEmpty())
                .filter(it -> it.getOffset() + it.getPageSize() > totalElements)
                .map(it -> it.getOffset() + content.size())
                .orElse(totalElements);

        InternalValidation.isTrue(totalElements >= 0, "Total elements cannot be negative");
    }

    /**
     * Constructs a new {@link SimplePageContent} with the given content and assumes unpaged pagination.
     *
     * @param content the list of elements in this page; if null, it is treated as an empty list
     */
    public SimplePageContent(Collection<T> content) {
        this(content, Pagination.unpaged(), null == content ? 0 : content.size());
    }

    @Override
    public int getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) getTotalElements() / (double) getSize());
    }

    @Override
    public long getTotalElements() {
        return totalElements;
    }

    /**
     * Maps the content of this page using a given function and returns a new
     * {@link PageContent} instance with the mapped content while preserving
     * pagination details.
     *
     * @param <U>       the type of elements in the resulting page
     * @param converter the function to convert elements; must not be {@code
     *                  null}
     * @return a new {@link PageContent} instance with the mapped content
     */
    @Override
    public <U> PageContent<U> map(Function<? super T, ? extends U> converter) {
        return new SimplePageContent<>(getConvertedContent(converter), getPagination(), getTotalElements());
    }

    @Override
    public boolean hasNext() {
        return getNumber() + 1 < getTotalPages();
    }

    @Override
    public boolean isLast() {
        return !hasNext();
    }

    /**
     * Returns a string representation of this page including the current page
     * number, total pages, and the type of elements contained in the page.
     *
     * @return a string describing the current page
     */
    @Override
    public String toString() {
        String contentType = "UNKNOWN";
        List<T> content = getContent();

        if (!content.isEmpty() && content.getFirst() != null) {
            contentType = content.getFirst().getClass().getName();
        }

        return String.format("Page %s of %d containing %s instances", getNumber() + 1, getTotalPages(), contentType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof SimplePageContent<?> that)) {
            return false;
        }

        return this.totalElements == that.totalElements && super.equals(obj);
    }

    @Override
    public int hashCode() {
        int result = 17;

        result += 31 * Long.hashCode(totalElements);
        result += 31 * super.hashCode();

        return result;
    }

}
