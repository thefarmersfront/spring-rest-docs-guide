package com.kurly.tet.guide.springrestdocs.domain;

import lombok.Getter;

import java.util.Set;

@Getter
public enum GiftOrderStatus implements DescriptionEnum {
    CREATED("선물주문생성", false, false, false),
    READY_FOR_ACCEPT("선물수락대기", true, true, true),
    REJECTED("선물거절", false, false, true),
    ACCEPTED("선물수락", false, false, true),
    DELIVERY_PENDING("배송보류", false, true, true),
    DELIVERED("선물배송완료", false, false, true),

    ACCEPT_EXPIRED("선물수락만료", false, false, false),
    DELIVERY_PEND_EXPIRED("배송보류만료", false, false, false),

    CANCELED("선물취소완료", false, false, false),
    FAILED("선물주문실패", false, false, false),
    ;

    public static final Set<GiftOrderStatus> DONE = Set.of(DELIVERED, CANCELED, REJECTED);
    public static final Set<GiftOrderStatus> ALREADY_CANCELED = Set.of(CANCELED, FAILED, REJECTED, ACCEPT_EXPIRED, DELIVERY_PEND_EXPIRED);

    private final String description;
    private final boolean userCancelable;
    private final boolean adminCancelable;
    private final boolean systemCancelable;

    /**
     * 선물주문상태
     *
     * @param description      설명
     * @param userCancelable   회원 취소가능여부
     * @param adminCancelable  관리자 취소가능여부
     * @param systemCancelable 시스템 취소가능여부
     */
    GiftOrderStatus(String description, boolean userCancelable, boolean adminCancelable, boolean systemCancelable) {
        this.description = description;
        this.userCancelable = userCancelable;
        this.adminCancelable = adminCancelable;
        this.systemCancelable = systemCancelable;
    }

    @Override
    public String getCode() {
        return name();
    }
}
