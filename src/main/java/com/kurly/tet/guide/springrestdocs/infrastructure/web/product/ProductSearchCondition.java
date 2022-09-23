package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import lombok.Getter;

@Getter
public class ProductSearchCondition {
    private String productName;
    private String productNo;

    public boolean hasName() {
        return getProductName() != null && !getProductName().isEmpty();
    }

    public boolean hasProductNumber() {
        return getProductNo() != null && !getProductNo().isEmpty();
    }
}
