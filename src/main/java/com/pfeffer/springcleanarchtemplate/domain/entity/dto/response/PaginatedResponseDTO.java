package com.pfeffer.springcleanarchtemplate.domain.entity.dto.response;

import com.pfeffer.springcleanarchtemplate.domain.pagination.PageContent;
import com.pfeffer.springcleanarchtemplate.domain.utils.InternalValidation;

import java.util.List;

/**
 * A Data Transfer Object (DTO) representing a paginated response.
 * <p>
 * This DTO encapsulates the paginated content, pagination details such as the
 * current page, total pages, page size, total elements, and sorting
 * information.
 *
 * @param <T> the type of content in the paginated response
 * @author Mateus Pfeffer
 * @since 1.0
 */
public class PaginatedResponseDTO<T> {

    private List<T> content;

    private int pageNumber;

    private int pageSize;

    private int totalPages;

    private long totalElements;

    private boolean first;

    private boolean last;

    private String[] sort;

    /**
     * Constructs a new {@link PaginatedResponseDTO} from a given {@link
     * PageContent}.
     *
     * @param pageContent the {@link PageContent} containing pagination details
     *                    and content
     */
    public PaginatedResponseDTO(PageContent<T> pageContent) {
        InternalValidation.notNull(pageContent, "PageContent cannot be null");

        this.content = pageContent.getContent();
        this.pageNumber = pageContent.getNumber();
        this.pageSize = pageContent.getSize();
        this.totalPages = pageContent.getTotalPages();
        this.totalElements = pageContent.getTotalElements();
        this.first = pageContent.isFirst();
        this.last = pageContent.isLast();
        this.sort = pageContent.getSort();
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public String[] getSort() {
        return sort;
    }

    public void setSort(String[] sort) {
        this.sort = sort;
    }

}
