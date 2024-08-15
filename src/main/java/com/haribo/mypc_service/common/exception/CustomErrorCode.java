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
    CUSTOM_PC_NOT_FOUND("커스텀 PC 목록에 해당 아이디를 가진 컴퓨터가 없습니다.", HttpStatus.BAD_REQUEST),

    // 존재하지 않는 유저
    USER_NOT_FOUND("존재하지 않는 유저입니다.", HttpStatus.BAD_REQUEST),

    // 올바르지 않은 부품 요청
    PART_NOT_FOUND("유효하지 않은 부품 이름입니다.", HttpStatus.BAD_REQUEST),

    // 작성자와 수정자 불일치
    CAN_NOT_UPDATED("작성자와 수정자가 일치하지 않아 수정이 불가합니다.", HttpStatus.BAD_REQUEST),


    CAN_NOT_DELETED("작성자와 수정자가 일치하지 않아 삭제가 불가합니다.", HttpStatus.BAD_REQUEST),

    ;

    private final String statusMessage;
    private final HttpStatus status;
}
