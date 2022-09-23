package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@NoArgsConstructor
public class PageResponse<T> {
    private List<T> content = new ArrayList<>();
    private PageRequest pageRequest;
    private long total;

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
        return this.pageRequest.getSize() == 0 ? 1 : (int) Math.ceil(getTotal() / (double) this.pageRequest.getSize());
    }

    @JsonProperty("hasPrevious")
    public boolean hasPrevious(){
        return this.pageRequest.getPage() > 0;
    }

    @JsonProperty("isFirst")
    public boolean isFirst() {
        return !hasPrevious();
    }

    @JsonProperty("hasNext")
    public boolean hasNext() {
        return this.pageRequest.getPage() + 1 < getTotalPages();
    }

    @JsonProperty("isLast")
    public boolean isLast() {
        return !hasNext();
    }

    @JsonProperty("hasContent")
    public boolean hasContent() {
        return !getContent().isEmpty();
    }
}
