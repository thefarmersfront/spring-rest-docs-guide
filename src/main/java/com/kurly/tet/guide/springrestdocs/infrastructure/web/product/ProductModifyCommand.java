package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductModifyCommand {
    @NotBlank
    private String productName;
    @NotBlank
    private String productNo;
    @NotNull
    private ProductStatus productStatus;

    public ProductModifyCommand(String productName, String productNo, ProductStatus productStatus) {
        this.productName = productName;
        this.productNo = productNo;
        this.productStatus = productStatus;
    }

    public ProductDto modify(ProductDto source) {
        source.modify(getProductName(), getProductNo(), getProductStatus());
        return source;
    }
}
