package com.kurly.tet.guide.springrestdocs.domain.order;

import com.kurly.tet.guide.springrestdocs.domain.product.ProductDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderDto {
    private Long id;
    private String memberNo;
    private String orderNo;
    private OrderStatus orderStatus;
    private List<ProductDto> products;
    private LocalDateTime orderDateTime; // 주문일시

    private Long paymentMoney; // 결제금액
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

    public String getOrderStatusDescription() {
        return getOrderStatus().getDescription();
    }

    /**
     * 결제
     *
     * @param paymentMoney 결제금액
     */
    public void payment(Long paymentMoney) {
        Assert.isTrue(this.orderStatus == OrderStatus.ORDERED, "주문상태가 '주문완료' 상태여야 합니다.");
        Assert.notNull(paymentMoney, "결제금액(paymentMoney)은 필수입력값입니다.");
        Assert.isTrue(paymentMoney > 0, "결제금액(paymentMoney)은 0보다 커야합니다.");

        this.orderStatus = OrderStatus.PAID;
        this.paymentMoney = paymentMoney;
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
