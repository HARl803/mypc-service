package com.haribo.mypc_service.common.exception;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public CustomErrorResponse handleCustomException(
            CustomException e,
            HttpServletRequest request) {
        log.error("에러 발생! 요청 URL: {}, 에러 메시지: {}", request.getRequestURL(), e.getStatusMessage());
        return CustomErrorResponse.builder()
                .status(e.getCustomErrorCode())
                .statusMessage(e.getStatusMessage())
                .build();
    }
}
