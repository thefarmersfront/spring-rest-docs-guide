package com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command;

import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderPaymentCommand {
    @NotNull
    private Long paymentMoney;

    public OrderPaymentCommand(Long paymentMoney) {
        this.paymentMoney = paymentMoney;
    }

    public void payment(OrderDto order) {
        order.payment(paymentMoney);
    }
}
