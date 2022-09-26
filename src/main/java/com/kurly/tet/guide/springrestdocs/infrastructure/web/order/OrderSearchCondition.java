package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Schema(description = "주문검색조건")
@Getter
public class OrderSearchCondition {
    @Schema(description = "회원번호")
    private String memberNo;
    @Schema(description = "주문번호")
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
