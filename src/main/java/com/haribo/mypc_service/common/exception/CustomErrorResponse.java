package com.haribo.mypc_service.common.exception;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomErrorResponse {
    private CustomErrorCode status;
    private String statusMessage;
}
