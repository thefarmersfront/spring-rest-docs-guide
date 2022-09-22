package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import lombok.Getter;

@Getter
public class ProductSearchCondition {
    private String name;
    private String productNumber;

    public boolean hasName() {
        return getName() != null && !getName().isEmpty();
    }

    public boolean hasProductNumber() {
        return getProductNumber() != null && !getProductNumber().isEmpty();
    }
}
