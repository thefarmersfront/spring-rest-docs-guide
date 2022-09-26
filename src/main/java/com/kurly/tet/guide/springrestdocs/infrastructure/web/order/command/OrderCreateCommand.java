package com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command;

import com.kurly.tet.guide.springrestdocs.common.util.LocalDateTimeUtils;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "주문생성명령")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderCreateCommand {
    @Schema(description = "회원번호",required = true)
    @NotBlank
    private String memberNo;
    @Schema(description = "상품식별번호목록", required = true, example = "[1,2,]")
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
