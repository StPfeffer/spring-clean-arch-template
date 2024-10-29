package com.pfeffer.springcleanarchtemplate.domain.pagination;

import com.pfeffer.springcleanarchtemplate.domain.exception.InternalValidationException;
import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;

import java.io.Serial;
import java.io.Serializable;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * An abstract implementation of {@link SliceContent} that provides a chunk of
 * data with pagination details. This class manages the content as a list and
 * supports methods to convert the content using a provided function.
 * <p>
 * The class is immutable, and the content list is unmodifiable to ensure safe
 * access. It also implements {@link Serializable} for serialization support.
 *
 * @param <T> the type of elements contained in the chunk
 * @author Mateus Pfeffer
 * @since 1.0
 */
public abstract class ChunkContent<T>
        implements SliceContent<T>, Serializable {

    @Serial
    private static final long serialVersionUID = 4253418781635462751L;

    private final List<T> content = new ArrayList<>();

    private final Pagination pagination;

    /**
     * Constructs a new {@link ChunkContent} with the given content and
     * pagination details.
     *
     * @param content    the list of elements in this chunk; must not be
     *                   {@code null}
     * @param pagination the pagination details for this chunk; must not be
     *                   {@code null}
     * @throws InternalValidationException if content or pagination is {@code null}
     */
    protected ChunkContent(Collection<T> content, Pagination pagination) {
        InternalValidation.notNull(content, "Content must not be null");
        InternalValidation.notNull(pagination, "Pagination must not be null");

        this.content.addAll(content);
        this.pagination = pagination;
    }

    @Override
    public int getNumber() {
        return pagination.isPaged() ? pagination.getPageNumber() : 0;
    }

    @Override
    public int getSize() {
        return pagination.isPaged() ? pagination.getPageSize() : content.size();
    }

    @Override
    public int getNumberOfElements() {
        return content.size();
    }

    @Override
    public boolean hasPrevious() {
        return getNumber() > 0;
    }

    @Override
    public boolean isFirst() {
        return !hasPrevious();
    }

    @Override
    public boolean isLast() {
        return !hasNext();
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public Pagination nextPagination() {
        return hasNext() ? pagination.next() : Pagination.unpaged();
    }

    @Override
    public Pagination previousPagination() {
        return hasPrevious() ? pagination.previousOrFirst() : Pagination.unpaged();
    }

    /**
     * Converts the content of this chunk using a given function.
     *
     * @param <U>       the type of elements in the resulting list
     * @param converter the function to convert elements; must not be {@code
     *                  null}
     * @return a list of converted elements
     * @throws IllegalArgumentException if the converter function is {@code
     *                                  null}
     */
    protected <U> List<U> getConvertedContent(Function<? super T, ? extends U> converter) {
        InternalValidation.notNull(converter, "Function must not be null");
        return getContent().stream().map(converter).collect(Collectors.toList());
    }

    @Override
    public boolean hasContent() {
        return !content.isEmpty();
    }

    @Override
    public String[] getSort() {
        return pagination.getSort();
    }

    @Override
    public List<T> getContent() {
        return Collections.unmodifiableList(content);
    }

    @Override
    public Pagination getPagination() {
        return pagination;
    }

    /**
     * Returns an iterator over the elements in this chunk.
     *
     * @return an {@link Iterator} over the content of this chunk
     */
    public Iterator<T> iterator() {
        return content.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof ChunkContent<?> that)) {
            return false;
        }

        boolean contentEqual = this.content.equals(that.content);
        boolean paginationEqual = this.pagination.equals(that.pagination);

        return contentEqual && paginationEqual;
    }

    @Override
    public int hashCode() {
        int result = 17;

        result += 31 * pagination.hashCode();
        result += 31 * content.hashCode();

        return result;
    }

}
