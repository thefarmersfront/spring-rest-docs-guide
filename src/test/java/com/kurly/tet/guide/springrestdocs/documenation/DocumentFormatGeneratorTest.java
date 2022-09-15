package com.kurly.tet.guide.springrestdocs.documenation;

import com.kurly.tet.guide.springrestdocs.domain.GiftOrderStatus;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kurly.tet.guide.springrestdocs.domain.GiftOrderStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

class DocumentFormatGeneratorTest {
    @Test
    void testGeneratedEnums() {
        var result = DocumentFormatGenerator.generatedEnums(GiftOrderStatus.class);

        assertThat(result).isEqualTo("""
                * `CREATED`
                * `READY_FOR_ACCEPT`
                * `REJECTED`
                * `ACCEPTED`
                * `DELIVERY_PENDING`
                * `DELIVERED`
                * `ACCEPT_EXPIRED`
                * `DELIVERY_PEND_EXPIRED`
                * `CANCELED`
                * `FAILED`""");
    }

    @Test
    void testGeneratedEnumAttr() {
        var attrs = DocumentFormatGenerator.generateEnumAttrs(GiftOrderStatus.class, GiftOrderStatus::getDescription);

        assertThat(attrs.getValue()).isEqualTo("""
                * `CREATED`(선물주문생성)
                * `READY_FOR_ACCEPT`(선물수락대기)
                * `REJECTED`(선물거절)
                * `ACCEPTED`(선물수락)
                * `DELIVERY_PENDING`(배송보류)
                * `DELIVERED`(선물배송완료)
                * `ACCEPT_EXPIRED`(선물수락만료)
                * `DELIVERY_PEND_EXPIRED`(배송보류만료)
                * `CANCELED`(선물취소완료)
                * `FAILED`(선물주문실패)""");
    }

    @Test
    void testGeneratedEnumListFormat() {
        var failed = List.of(CREATED, READY_FOR_ACCEPT, ACCEPTED, CANCELED);

        var formatAttr = DocumentFormatGenerator.generateEnumListFormatAttribute(failed, GiftOrderStatus::getDescription);

        assertThat(formatAttr.getValue()).isEqualTo(
                """
                        * `CREATED`(선물주문생성)
                        * `READY_FOR_ACCEPT`(선물수락대기)
                        * `ACCEPTED`(선물수락)
                        * `CANCELED`(선물취소완료)""");
    }
}