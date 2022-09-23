package com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command;

import com.kurly.tet.guide.springrestdocs.common.util.LocalDateTimeUtils;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderCreateCommand {
    private String memberNo;
    private List<Long> productIds;

    public OrderDto createOrder(long id, List<ProductDto> findProducts) {
        return new OrderDto(
                id,
                LocalDateTimeUtils.toString(LocalDateTime.now(), "yyyyMMddHHmmss" + id),
                this.memberNo,
                findProducts);
    }
}
