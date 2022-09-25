package com.kurly.tet.guide.springrestdocs.application.order;

import com.kurly.tet.guide.springrestdocs.application.product.ProductFacade;
import com.kurly.tet.guide.springrestdocs.domain.exception.OrderNotFoundException;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.OrderSearchCondition;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderCreateCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderPaymentCommand;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Component
public class OrderFacade {
    private final AtomicLong orderIdCreator = new AtomicLong(0);
    private final List<OrderDto> orders = new ArrayList<>();
    private final ProductFacade productFacade;

    public OrderFacade(ProductFacade productFacade) {
        this.productFacade = productFacade;
    }

    public PageResponse<OrderDto> search(OrderSearchCondition searchCondition, PageRequest pageRequest) {
        var filteredOrders = this.orders.stream()
                .filter(searchCondition::filter)
                .collect(Collectors.toList());

        return new PageResponse<>(filteredOrders, pageRequest, this.orders.size());
    }

    public OrderDto create(OrderCreateCommand createCommand) {
        var findProducts = createCommand.getProductIds().stream().map(productFacade::getProduct).collect(Collectors.toList());
        var createOrder = createCommand.createOrder(orderIdCreator.incrementAndGet(), findProducts);
        orders.add(createOrder);
        return createOrder;
    }

    public OrderDto findByOrderNo(String orderNo) {
        return orders.stream()
                .filter(it -> it.getOrderNo().equals(orderNo))
                .findAny()
                .orElseThrow(() -> new OrderNotFoundException(orderNo));
    }

    public OrderDto payment(String orderNo, OrderPaymentCommand paymentCommand) {
        var order = findByOrderNo(orderNo);
        paymentCommand.payment(order);
        return order;
    }

    public OrderDto shipping(String orderNo) {
        var order = findByOrderNo(orderNo);
        order.shipping();
        return order;
    }

    public OrderDto complete(String orderNo) {
        var order = findByOrderNo(orderNo);
        order.complete();
        return order;
    }
}
