package com.haribo.mypc_service.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {

    // 5개가 꽉 찼다
    SIZE_FULL_ERROR("1인당 허용된 커스텀 PC 생성 개수는 5개입니다.", HttpStatus.BAD_REQUEST),

    // 커스텀 PC 목록에 해당 이름의 컴퓨터가 없다
    CUSTOM_PC_NOT_FOUND("커스텀 PC 목록에 해당 이름을 가진 컴퓨터가 없습니다.", HttpStatus.BAD_REQUEST),
    
    // 존재하지 않는 유저
    USER_NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST),;

    private final String statusMessage;
    private final HttpStatus status;
}
