package com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command;

import com.kurly.tet.guide.springrestdocs.common.util.LocalDateTimeUtils;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateCommand {
    @NotBlank
    private String memberNo;
    @NotEmpty
    private List<Long> productIds;

    public OrderCreateCommand(String memberNo, List<Long> productIds) {
        this.memberNo = memberNo;
        this.productIds = productIds;
    }

    public OrderDto createOrder(long id, List<ProductDto> findProducts) {
        return new OrderDto(
                id,
                this.memberNo,
                LocalDateTimeUtils.toString(LocalDateTime.now(), "yyyyMMddHHmmss" + id),
                findProducts);
    }
}
