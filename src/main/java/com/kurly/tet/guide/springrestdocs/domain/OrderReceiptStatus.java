package com.kurly.tet.guide.springrestdocs.domain;

import lombok.Getter;

@Getter
public enum OrderReceiptStatus implements DescriptionEnum {
    COMPLETED("주문완료"),
    PRODUCING("배송 준비중"),
    PENDING("주문전송대기"),
    DELIVERING("배송중"),
    DELIVERED("배송완료"),
    REFUND_REQUESTED("취소접수"),
    REFUNDED("취소완료"),
    ;

    private final String description;

    OrderReceiptStatus(String description) {
        this.description = description;
    }

    public String getCode() {
        return name();
    }
}
