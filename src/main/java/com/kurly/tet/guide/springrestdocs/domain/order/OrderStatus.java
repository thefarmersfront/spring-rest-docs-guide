package com.kurly.tet.guide.springrestdocs.domain.order;

import com.kurly.tet.guide.springrestdocs.domain.DescriptionEnum;
import lombok.Getter;

@Getter
public enum OrderStatus implements DescriptionEnum {
    ORDERED("주문완료"),
    PAID("결제완료"),
    SHIPPED("출하완료"),
    COMPLETED("배송완료"),
    ;

    private String description;

    OrderStatus(String description) {
        this.description = description;
    }

    @Override
    public String getCode() {
        return name();
    }
}
