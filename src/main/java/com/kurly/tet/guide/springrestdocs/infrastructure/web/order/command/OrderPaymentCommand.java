package com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderPaymentCommand {
    @NotNull
    private Long paymentMoney;
}
