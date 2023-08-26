package com.hsbc.security.api.dto;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResultCode {
    SUCCESS(HttpServletResponse.SC_OK, "OK"),
    UNAUTHORIZED(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized"),
    BAD_REQUEST(HttpServletResponse.SC_BAD_REQUEST, "Invalid Request"),
    FAILURE(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Biz Error");

    final int code;
    final String msg;

}
