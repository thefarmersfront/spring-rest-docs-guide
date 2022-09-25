package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import lombok.Getter;

@Getter
public class ProductSearchCondition {
    private String productName;
    private String productNo;

    public boolean hasProductName() {
        return getProductName() != null && !getProductName().isEmpty();
    }

    public boolean hasProductNo() {
        return getProductNo() != null && !getProductNo().isEmpty();
    }

    public boolean filter(ProductDto productDto) {
        if(hasProductName()) {
            return productDto.getProductName().equals(getProductNo());
        }
        if(hasProductNo()) {
            return productDto.getProductNo().equals(getProductNo());
        }

        return true;
    }
}
