package com.kurly.tet.guide.springrestdocs.domain.exception;

import com.kurly.tet.guide.springrestdocs.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends BusinessException {
    public OrderNotFoundException(Long id) {
        super(String.format("주문(ID: %d)을 찾을 수 없습니다.", id));
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }

    @Override
    public String getErrorCode() {
        return "C404";
    }

    @Override
    public boolean isNecessaryToLog() {
        return true;
    }
}
