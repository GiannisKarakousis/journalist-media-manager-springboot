package com.example.journalist_media_manager_springboot.dto.common;

import java.util.Collection;

public class GenericPageDto {

    private Collection<?> elements;
    private long currentPage;
    private long totalElements;
    private long totalPages;

    public Collection<?> getElements() {
        return elements;
    }

    public void setElements(Collection<?> elements) {
        this.elements = elements;
    }

    public long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(long currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public long getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(long totalPages) {
        this.totalPages = totalPages;
    }
}
