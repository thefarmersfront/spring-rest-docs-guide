package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PageRequest {
    private int page;
    @Max(value = 1000)
    private int size;

    public PageRequest(int page, int size) {
        if (page < 0) {
            throw new IllegalArgumentException("페이지(page)는 0보다 커야합니다.");
        }

        if (size < 1) {
            throw new IllegalArgumentException("페이지크기(size)는 1보다 커야 합니다.");
        }

        this.page = page;
        this.size = size;
    }

    public static PageRequest of(int page, int size) {
        return new PageRequest(page, size);
    }

    public long getOffset() {
        return (long) page * (long) size;
    }
}