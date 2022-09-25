package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.application.order.OrderFacade;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderCreateCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderPaymentCommand;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class OrderRestController {
    private final OrderFacade orderFacade;

    public OrderRestController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    /**
     * 주문검색
     *
     * @param searchCondition 검색조건[회원번호]
     * @param pageRequest 페이징조건(page=페이지번호,size=페이지 내 건수)
     * @return 페이징된 검색주문내역
     */
    @GetMapping("/orders")
    public PageResponse<OrderDto> search(OrderSearchCondition searchCondition,
                                         @Valid PageRequest pageRequest) {
        return orderFacade.search(searchCondition, pageRequest);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders")
    public OrderDto create(@Valid @RequestBody OrderCreateCommand createCommand) {
        return orderFacade.create(createCommand);
    }

    @GetMapping("/orders/{orderNo}")
    public OrderDto findByOrderNo(@PathVariable String orderNo) {
        return orderFacade.findByOrderNo(orderNo);
    }

    @PutMapping("/orders/{orderNo}/payment")
    public OrderDto payment(@PathVariable String orderNo,
                            @Valid @RequestBody OrderPaymentCommand paymentCommand) {
        return orderFacade.payment(orderNo, paymentCommand);
    }

    @PutMapping("/orders/{orderNo}/shipping")
    public OrderDto shipping(@PathVariable String orderNo) {
        return orderFacade.shipping(orderNo);
    }

    @PutMapping("/orders/{orderNo}/complete")
    public OrderDto complete(@PathVariable String orderNo) {
        return orderFacade.complete(orderNo);
    }
}
