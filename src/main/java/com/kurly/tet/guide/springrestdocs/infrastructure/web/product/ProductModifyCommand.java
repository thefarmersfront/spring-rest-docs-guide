package com.kurly.tet.guide.springrestdocs.infrastructure.web.product;

import com.kurly.tet.guide.springrestdocs.domain.ProductDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ProductModifyCommand {
    @NotBlank
    private String productName;
    @NotBlank
    private String productNo;

    public ProductModifyCommand(String productName, String productNo) {
        this.productName = productName;
        this.productNo = productNo;
    }

    public ProductDto modify(ProductDto source) {
        source.modify(getProductName(), getProductNo());
        return source;
    }
}
