package com.kurly.tet.guide.springrestdocs.domain;

import lombok.Getter;

@Getter
public enum BusinessCode {
    //COMMON
    SUCCESS("0000","정상"),

    //Client
    BAD_REQUEST("C400", "잘못된 요청입니다. 요청내용을 확인하세요."),
    NOT_FOUND("C404", "요청내용을 찾을 수 없습니다. 요청내용을 확인하세요."),
    UNAUTHORIZED("C401", "인증되지 않았습니다. 인증을 확인하세요."),
    FORBIDDEN("C403", "권한이 없습니다. 권한을 확인하세요."),

    //Application
    INTERNAL_SERVER_ERROR("S500", "시스템 내부오류가 발생했습니다. 담당자에게 문의바랍니다."),


    ;

    private final String code;
    private final String description;

    BusinessCode(String code, String description) {
        this.code = code;
        this.description = description;
    }
}
