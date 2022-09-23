package com.kurly.tet.guide.springrestdocs.domain;

import lombok.Getter;

import java.util.Set;

@Getter
public enum ProductStatus implements DescriptionEnum {
    CREATED("생성"),
    ACTIVATED("활성화"),
    ARCHIVED("보관처리됨"),
    ;

    private String description;

    ProductStatus(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return name();
    }
}
