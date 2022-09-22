package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.dto;

import com.kurly.tet.guide.springrestdocs.domain.BusinessCode;

public class ApiResponseGenerator {
    private static final ApiResponse<Void> SUCCESS = new ApiResponse<>(BusinessCode.SUCCESS);
    private static final ApiResponse<Void> FAILURE = new ApiResponse<>(BusinessCode.INTERNAL_SERVER_ERROR);

    private ApiResponseGenerator() {
        throw new UnsupportedOperationException("Utility class.");
    }

    /**
     * 기본적인 성공응답
     *
     * @return <code>new ApiResponse<>("0000", "OK");</code>
     */
    public static ApiResponse<Void> success() {
        return SUCCESS;
    }

    /**
     * 성공 응답데이터 제공
     *
     * @param data 응답데이터
     * @return <code>new ApiResponse<>("0000", "OK", data);</code>
     */
    public static <D> ApiResponse<D> success(D data) {
        return new ApiResponse<>(SUCCESS.getCode(), SUCCESS.getMessage(), data);
    }

    /**
     * 기본적인 실패응답
     *
     * @return <code>new ApiResponse<>("5000", "Internal Server Error");</code>
     */
    public static ApiResponse<Void> fail() {
        return FAILURE;
    }

    /**
     * 실패 응답데이터 제공
     *
     * @param data 응답데이터
     * @return <code>new ApiResponse<>("5000", "Internal Server Error", data);</code>
     */
    public static <D> ApiResponse<D> fail(D data) {
        return new ApiResponse<>(FAILURE.getCode(), FAILURE.getMessage(), data);
    }

    /**
     * 실패응답
     *
     * @param code    실패코드
     * @param message 실패메시지
     * @return <code>new ApiResponse<>(code, message, null);</code>
     */
    public static ApiResponse<Void> fail(String code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    /**
     * 응답값 처리(서버에서 응답한 경우는 요청성공(success == true)으로 판단한다)
     *
     * @param code    응답코드
     * @param message 응답메시지
     * @param data    응답데이터
     * @param <D>     응답데이터 클래스
     * @return <code>new ApiResponse<>(code, message, data);</code>
     */
    public static <D> ApiResponse<D> of(String code, String message, D data) {
        return new ApiResponse<>(code, message, data);
    }
}
