package com.kurly.tet.guide.springrestdocs.infrastructure.web.handler;

import com.kurly.tet.guide.springrestdocs.common.exception.BusinessException;
import com.kurly.tet.guide.springrestdocs.domain.BusinessCode;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.dto.ApiResponse;
import com.kurly.tet.guide.springrestdocs.infrastructure.web.dto.ApiResponseGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.nio.file.AccessDeniedException;

import static com.kurly.tet.guide.springrestdocs.domain.BusinessCode.*;

@Slf4j
@RestControllerAdvice
public class GlobalRestControllerExceptionHandler {
    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ApiResponse<Void>> handle(BusinessException exception) {
        if (exception.isNecessaryToLog()) {
            log.error("[BusinessException] {}", exception.getMessage(), exception);
        }

        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ApiResponseGenerator.fail(exception.getErrorCode(), exception.getMessage()));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    protected ApiResponse<Void> handle(Throwable throwable) {
        log.error("[InternalServerError]{}", throwable.getMessage(), throwable);

        return ApiResponseGenerator.fail(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getDescription());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConversionFailedException.class)
    protected ApiResponse<Void> handle(ConversionFailedException exception) {
        Throwable cause = exception.getCause();
        if (cause instanceof IllegalArgumentException illegalArgumentException) {
            return this.handle(illegalArgumentException);
        }

        log.error("[InternalServerError][ConversionFailed] {}", exception.getMessage(), exception);

        return ApiResponseGenerator.fail(INTERNAL_SERVER_ERROR.getCode(), INTERNAL_SERVER_ERROR.getDescription());
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    protected ApiResponse<Void> handle(AccessDeniedException exception) {
        log.info("[AccessDenied] {}", exception.getMessage());
        return ApiResponseGenerator.fail(FORBIDDEN.getCode(), FORBIDDEN.getDescription());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({
            IllegalArgumentException.class,
            MethodArgumentNotValidException.class,
            MissingServletRequestParameterException.class,
            MethodArgumentTypeMismatchException.class,
            HttpMessageNotReadableException.class,
            HttpMediaTypeNotSupportedException.class,
            HttpMediaTypeNotAcceptableException.class
    })
    protected ApiResponse<Void> handle(Exception exception) {
        log.info("[BadRequest] {}", exception.getMessage(), exception);

        return ApiResponseGenerator.fail(BAD_REQUEST.getCode(), BAD_REQUEST.getDescription());
    }
}
