package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductCreateCommand {
    @NotBlank
    private String productName;
    @NotBlank
    private String productNo;

    public ProductCreateCommand(String productName, String productNo) {
        this.productName = productName;
        this.productNo = productNo;
    }

    public ProductDto createDto(Long id) {
        return new ProductDto(id, getProductName(), getProductNo());
    }
}
