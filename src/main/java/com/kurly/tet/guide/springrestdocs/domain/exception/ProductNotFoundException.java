package com.kurly.tet.guide.springrestdocs.domain.exception;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super(String.format("상품(ID: %d)를 찾을 수 없습니다.", id));
    }
}
