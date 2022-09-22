package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
public class PageResponse<T> {
    private final List<T> content = new ArrayList<>();
    private final PageRequest pageRequest;
    private final long total;

    public PageResponse(List<T> content, PageRequest pageRequest, long total) {
        this.content.addAll(content);
        this.pageRequest = pageRequest;
        this.total = Optional.of(pageRequest)
                .filter(it -> !content.isEmpty())
                .filter(it -> pageRequest.getOffset() + it.getSize() > total)
                .map(it -> pageRequest.getOffset() + content.size())
                .orElse(total);
    }

    public int getTotalPages() {
        return getPageRequest().getSize() == 0 ? 1 : (int) Math.ceil(getTotal() / (double) getPageRequest().getSize());
    }

    public boolean hasPrevious(){
        return getPageRequest().getPage() > 0;
    }

    public boolean isFirst() {
        return !hasPrevious();
    }

    public boolean hasNext() {
        return getPageRequest().getPage() + 1 < getTotalPages();
    }

    public boolean isLast() {
        return !hasNext();
    }

    public boolean hasContent() {
        return !getContent().isEmpty();
    }
}
