package com.hsbc.security.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用业务返回体父类
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {
    private String message;
    private ResultCode code = ResultCode.SUCCESS;
}
