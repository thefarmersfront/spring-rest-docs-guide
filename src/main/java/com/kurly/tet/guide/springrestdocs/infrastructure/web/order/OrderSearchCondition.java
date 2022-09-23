package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import lombok.Getter;

@Getter
public class OrderSearchCondition {
    private String memberNo;

    public boolean hasMemberNo() {
        return memberNo != null && !memberNo.isBlank();
    }

    public boolean filter(OrderDto orderDto) {
        if (hasMemberNo()) {
            return orderDto.getMemberNo().equals(getMemberNo());
        }

        return false;
    }
}
