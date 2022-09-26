package com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command;

import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Schema(description = "주문결제명령")
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderPaymentCommand {
    @Schema(description = "결제금액", required = true, example = "1000")
    @NotNull
    private Long paymentMoney;

    public OrderPaymentCommand(Long paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public void payment(OrderDto order) {
        order.payment(paymentMoney);
    }
}
