package com.kurly.tet.guide.springrestdocs.application.order;

import com.kurly.tet.guide.springrestdocs.annotations.MockTest;
import com.kurly.tet.guide.springrestdocs.application.product.ProductFacade;
import com.kurly.tet.guide.springrestdocs.domain.order.OrderStatus;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderCreateCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.order.command.OrderPaymentCommand;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.product.ProductCreateCommand;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

@MockTest
class OrderFacadeTest {
    @Spy
    ProductFacade productFacade;
    OrderFacade orderFacade;

    @BeforeEach
    void setUp() {
        productFacade = new ProductFacade();
        var createCommand = new ProductCreateCommand("TEST", "TEST01");
        productFacade.create(createCommand);

        orderFacade = new OrderFacade(productFacade);
    }

    @DisplayName("주문: 주문완료(생성)")
    @Test
    void testCreate() {
        //tiven
        long productId = 1L;
        var createCommand = new OrderCreateCommand("202209240001", List.of(productId));

        //when
        var createdOrder = orderFacade.create(createCommand);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(createdOrder.getMemberNo()).isEqualTo(createCommand.getMemberNo());
            softly.assertThat(createdOrder.getProducts().get(0).getId()).isEqualTo(productId);
        });

    }

    @DisplayName("주문: 결제완료")
    @Test
    void testPayment() {
        //given
        long productId = 1L;
        var createCommand = new OrderCreateCommand("202209240001", List.of(productId));
        var createdOrder = orderFacade.create(createCommand);

        //when
        OrderPaymentCommand paymentCommand = new OrderPaymentCommand(1000L);
        var paidOrder = orderFacade.payment(createdOrder.getOrderNo(), paymentCommand);

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(paidOrder.getOrderStatus()).isEqualTo(OrderStatus.PAID);
            softly.assertThat(paidOrder.getPaymentMoney()).isEqualTo(paymentCommand.getPaymentMoney());
            softly.assertThat(paidOrder.getPaymentDateTime()).isNotNull();
        });
    }

    @DisplayName("주문: 출하완료 처리")
    @Test
    void testShipping() {
        //given
        long productId = 1L;
        var createCommand = new OrderCreateCommand("202209240001", List.of(productId));
        var createdOrder = orderFacade.create(createCommand);

        OrderPaymentCommand paymentCommand = new OrderPaymentCommand(1000L);
        var paidOrder = orderFacade.payment(createdOrder.getOrderNo(), paymentCommand);

        var shippingOrder = orderFacade.shipping(paidOrder.getOrderNo());

        //expect
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(paidOrder.getOrderStatus()).isEqualTo(OrderStatus.SHIPPED);
            softly.assertThat(paidOrder.getShipmentDateTime()).isNotNull();
        });
    }

    @Test
    @DisplayName("생성상태에서 출하로 변경시 오류 발생")
    void testShippingWhenCreatedOccurException() {
        //given
        long productId = 1L;
        var createCommand = new OrderCreateCommand("202209240001", List.of(productId));
        var createdOrder = orderFacade.create(createCommand);

        //expect
        Throwable thrown = catchThrowable(() -> orderFacade.shipping(createdOrder.getOrderNo()));
        assertThat(thrown)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("주문상태가 '결제완료' 상태여야 합니다.");
    }

    @DisplayName("주문: 배송완료 처리")
    @Test
    void testComplete() {
        //given
        long productId = 1L;
        var createCommand = new OrderCreateCommand("202209240001", List.of(productId));
        var createdOrder = orderFacade.create(createCommand);

        OrderPaymentCommand paymentCommand = new OrderPaymentCommand(1000L);
        var paidOrder = orderFacade.payment(createdOrder.getOrderNo(), paymentCommand);

        var shippingOrder = orderFacade.shipping(paidOrder.getOrderNo());

        //when
        var completedOrder = orderFacade.complete(shippingOrder.getOrderNo());

        //then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(completedOrder.getOrderStatus()).isEqualTo(OrderStatus.COMPLETED);
            softly.assertThat(completedOrder.getCompletedDateTime()).isNotNull();
        });
    }
}