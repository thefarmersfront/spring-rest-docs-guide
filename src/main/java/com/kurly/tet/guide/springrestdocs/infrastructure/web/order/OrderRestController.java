package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.application.order.OrderFacade;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderCreateCommand;
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
    public PageResponse<OrderDto> search(OrderSearchCondition searchCondition, @Valid PageRequest pageRequest) {
        return orderFacade.search(searchCondition, pageRequest);
    }

    @PostMapping("/orders")
    public OrderDto create(@Valid @RequestBody OrderCreateCommand createCommand) {
        return orderFacade.create(createCommand);
    }

    @GetMapping("/orders/{orderId}")
    public OrderDto get(@PathVariable Long orderId) {
        return orderFacade.findById(orderId);
    }

    @PutMapping("/orders/{orderId}/payment")
    public OrderDto payment(@PathVariable Long orderId) {
    }

    @PutMapping("/orders/{orderId}/shipping")
    public OrderDto shipping(@PathVariable Long orderId) {

    }

    @PutMapping("/orders/{orderId}/complete")
    public OrderDto complete(@PathVariable Long orderId) {

    }
}
