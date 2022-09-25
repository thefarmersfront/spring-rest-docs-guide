package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import lombok.Getter;

@Getter
public class OrderSearchCondition {
    private String memberNo;
    private String orderNo;

    private boolean hasMemberNo() {
        return memberNo != null && !memberNo.isBlank();
    }

    private boolean hasOrderNo() {
        return orderNo != null && !orderNo.isBlank();
    }

    public boolean filter(OrderDto orderDto) {
        if (hasMemberNo()) {
            return orderDto.getMemberNo().equals(getMemberNo());
        }
        if (hasOrderNo()) {
            return orderDto.getOrderNo().equals(getOrderNo());
        }

        return true;
    }
}
