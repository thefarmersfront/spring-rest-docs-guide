package com.kurly.tet.guide.springrestdocs.domain.order;

import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import lombok.Getter;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class OrderDto {
    private final Long id;
    private final String memberNo;
    private final String orderNo;
    private OrderStatus orderStatus;
    private final List<ProductDto> products;
    private final LocalDateTime orderDateTime; // 주문일시
    private LocalDateTime paymentDateTime; // 결제일시
    private LocalDateTime shipmentDateTime; // 출하일시
    private LocalDateTime completedDateTime;    // 완료일시

    /**
     * 주문정보
     *
     * @param id       식별번호
     * @param memberNo 회원번호
     * @param orderNo  주문번호
     * @param products 주문상품목록
     */
    public OrderDto(Long id,
                    String memberNo,
                    String orderNo,
                    List<ProductDto> products) {
        this.id = id;
        this.memberNo = memberNo;
        this.orderNo = orderNo;
        this.products = products;

        this.orderStatus = OrderStatus.ORDERED;
        this.orderDateTime = LocalDateTime.now();
    }

    public void pay() {
        Assert.isTrue(this.orderStatus == OrderStatus.ORDERED, "주문상태가 '주문완료' 상태여야 합니다.");

        this.orderStatus = OrderStatus.PAID;
        this.paymentDateTime = LocalDateTime.now();
    }

    public void shipping() {
        Assert.isTrue(this.orderStatus == OrderStatus.PAID, "주문상태가 '결제완료' 상태여야 합니다.");

        this.orderStatus = OrderStatus.SHIPPED;
        this.shipmentDateTime = LocalDateTime.now();
    }

    public void complete() {
        Assert.isTrue(this.orderStatus == OrderStatus.SHIPPED, "주문상태가 '출하완료' 상태여야 합니다.");

        this.orderStatus = OrderStatus.COMPLETED;
        this.completedDateTime = LocalDateTime.now();
    }
}
