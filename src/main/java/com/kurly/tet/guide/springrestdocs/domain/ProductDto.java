package com.kurly.tet.guide.springrestdocs.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ProductDto {
    private Long productId;
    private String productName;
    private String productNo;
    private LocalDateTime created;
    private LocalDateTime modified;

    public ProductDto(Long productId, String productName, String productNo) {
        this.productId = productId;
        this.productName = productName;
        this.productNo = productNo;
        this.created = LocalDateTime.now();
        this.modified = this.created;
    }

    public void modify(String productName, String productNo) {
        this.productName = productName;
        this.productNo = productNo;
        this.modified = LocalDateTime.now();
    }
}
