package com.kurly.tet.guide.springrestdocs.infrastructure.web.common.advisor;

import com.kurly.tet.guide.springrestdocs.common.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ApiResponseJsonProcessingException extends BusinessException {
    private static final String ERROR_CODE = "S599";

    private static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public ApiResponseJsonProcessingException(Throwable cause) {
        super(cause);
    }

    @Override
    public HttpStatus getHttpStatus() {
        return HTTP_STATUS;
    }

    @Override
    public String getErrorCode() {
        return ERROR_CODE;
    }

    @Override
    public boolean isNecessaryToLog() {
        return true;
    }
}
