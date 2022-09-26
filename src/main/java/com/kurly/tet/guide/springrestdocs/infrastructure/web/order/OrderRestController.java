package com.kurly.tet.guide.springrestdocs.infrastructure.web.order;

import com.kurly.tet.guide.springrestdocs.application.order.OrderFacade;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderDto;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageRequest;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto.PageResponse;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderCreateCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderPaymentCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name = "orders", description = "주문(Order)API")
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
     * @param pageRequest     페이징조건(page=페이지번호,size=페이지 내 건수)
     * @return 페이징된 검색주문내역
     */
    @Operation(summary = "주문내역을 검색한다.")
    @GetMapping("/orders")
    public PageResponse<OrderDto> search(@Parameter(description = "주문검색조건") OrderSearchCondition searchCondition,
                                         @Valid PageRequest pageRequest) {
        return orderFacade.search(searchCondition, pageRequest);
    }

    @Operation(summary = "주문을 생성한다.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "주문을 생성합니다.", required = true , content = @Content(schema = @Schema(implementation = OrderCreateCommand.class))),
            responses = {
                    @ApiResponse(responseCode = "201", description = "정상적으로 생성완료"),
                    @ApiResponse(responseCode = "400", description = "회원번호 혹은 상품번호를 누락한 경우 발생",
                            content = {@Content(schema = @Schema(example = "{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"))}),
                    @ApiResponse(responseCode = "403", description = "미구현"),
            }
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/orders")
    public OrderDto create(@Valid @RequestBody OrderCreateCommand createCommand) {
        return orderFacade.create(createCommand);
    }

    @Operation(summary = "주문내역을 조회한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상처리"),
                    @ApiResponse(responseCode = "400", description = "미구현"),
                    @ApiResponse(responseCode = "403", description = "미구현"),
                    @ApiResponse(responseCode = "404", description = "주문번호와 대응되는 주문내역 검색실패")
            })
    @GetMapping("/orders/{orderNo}")
    public OrderDto findByOrderNo(@Parameter(description = "주문번호") @PathVariable String orderNo) {
        return orderFacade.findByOrderNo(orderNo);
    }

    @Operation(summary = "주문내역을 결제처리한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상처리"),
                    @ApiResponse(responseCode = "400", description = "회원번호 혹은 상품번호를 누락한 경우 발생",
                            content = {@Content(schema = @Schema(example = "{\"code\":\"C400\",\"message\":\"잘못된 요청입니다. 요청내용을 확인하세요.\",\"data\":null}"))}),
                    @ApiResponse(responseCode = "403", description = "미구현"),
                    @ApiResponse(responseCode = "404", description = "주문번호와 대응되는 주문내역 검색실패",
                            content = {@Content(schema = @Schema(example = "{\"code\":\"C404\",\"message\":\"주문(주문번호: ${주문번호})을 찾을 수 없습니다.\",\"data\":null}"))})
            }
    )
    @PutMapping("/orders/{orderNo}/payment")
    public OrderDto payment(@Parameter(description = "주문번호") @PathVariable String orderNo,
                            @Valid @RequestBody OrderPaymentCommand paymentCommand) {
        return orderFacade.payment(orderNo, paymentCommand);
    }

    @Operation(summary = "주문내역을 출하한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상처리"),
                    @ApiResponse(responseCode = "404", description = "주문번호와 대응되는 주문내역 검색실패",
                            content = {@Content(schema = @Schema(example = "{\"code\":\"C404\",\"message\":\"주문(주문번호: ${주문번호})을 찾을 수 없습니다.\",\"data\":null}"))})
            })
    @PutMapping("/orders/{orderNo}/shipping")
    public OrderDto shipping(@Parameter(description = "주문번호") @PathVariable String orderNo) {
        return orderFacade.shipping(orderNo);
    }

    @Operation(summary = "주문내역을 배송완료처리한다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "정상처리"),
                    @ApiResponse(responseCode = "404", description = "주문번호와 대응되는 주문내역 검색실패",
                            content = {@Content(schema = @Schema(example = "{\"code\":\"C404\",\"message\":\"주문(주문번호: ${주문번호})을 찾을 수 없습니다.\",\"data\":null}"))})
            })
    @PutMapping("/orders/{orderNo}/complete")
    public OrderDto complete(@Parameter(description = "주문번호") @PathVariable String orderNo) {
        return orderFacade.complete(orderNo);
    }
}
