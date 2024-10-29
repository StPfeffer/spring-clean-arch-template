package com.pfeffer.springcleanarchtemplate.infra.adapter;

import com.pfeffer.springcleanarchtemplate.domain.pagination.PageContent;
import com.pfeffer.springcleanarchtemplate.domain.pagination.Pagination;
import com.pfeffer.springcleanarchtemplate.domain.pagination.PaginationRequest;
import com.pfeffer.springcleanarchtemplate.domain.pagination.SimplePageContent;
import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Adapter for converting Spring's {@link Page} interface to the domain-specific
 * {@link PageContent}.
 *
 * @param <T> the type of elements contained in the page.
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class SpringPageAdapter<T> implements PageContent<T> {

    private final Page<T> page;

    public SpringPageAdapter(Page<T> page) {
        InternalValidation.notNull(page, "Page must not be null");

        this.page = page;
    }

    @Override
    public int getTotalPages() {
        return page.getTotalPages();
    }

    @Override
    public long getTotalElements() {
        return page.getNumberOfElements();
    }

    @Override
    public int getNumber() {
        return page.getNumber();
    }

    @Override
    public int getSize() {
        return page.getSize();
    }

    @Override
    public int getNumberOfElements() {
        return page.getNumberOfElements();
    }

    @Override
    public List<T> getContent() {
        return page.getContent();
    }

    @Override
    public boolean hasContent() {
        return page.hasContent();
    }

    @Override
    public String[] getSort() {
        return page.getSort().stream()
                .map(order -> order.getProperty() + " " + order.getDirection().name())
                .toArray(String[]::new);
    }

    @Override
    public boolean isFirst() {
        return page.isFirst();
    }

    @Override
    public boolean isLast() {
        return page.isLast();
    }

    @Override
    public boolean hasNext() {
        return page.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return page.hasPrevious();
    }

    @Override
    public Pagination nextPagination() {
        return hasNext() ? PaginationRequest.of(page.getNumber() + 1, page.getSize(), getSort())
                : Pagination.unpaged();
    }

    @Override
    public Pagination previousPagination() {
        return hasPrevious() ? PaginationRequest.of(page.getNumber() - 1, page.getSize(), getSort())
                : Pagination.unpaged();
    }

    @Override
    public <U> PageContent<U> map(Function<? super T, ? extends U> converter) {
        InternalValidation.notNull(converter, "Function must not be null");

        List<U> content = getContent().stream()
                .map(converter)
                .collect(Collectors.toList());

        return new SimplePageContent<>(content, getPagination(), getTotalElements());
    }

}
