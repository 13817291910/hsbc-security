package com.hsbc.security.controller;

import com.hsbc.security.aop.auth.Auth;
import com.hsbc.security.aop.auth.AuthConstant;
import com.hsbc.security.aop.auth.AuthContext;
import com.hsbc.security.api.AuthService;
import com.hsbc.security.api.dto.LoginRequest;
import com.hsbc.security.api.dto.ResultCode;
import com.hsbc.security.error.ErrorMsgConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/hsbc/v1")
public class AuthController {

    @Autowired
    private AuthService authService;

    /**
     * 登录账号，如果已经登录，会踢掉老的session,并刷新token
     *
     * @param request 用户名和密码
     * @return 返回是否登录成功
     */
    @PostMapping("/session")
    public ResponseEntity login(@RequestBody LoginRequest request) {
        boolean valid = authService.isValid(request.getUsername(), request.getPassword());
        if (!valid) {
            return ResponseEntity.status(ResultCode.BAD_REQUEST.getCode()).body(ResultCode.BAD_REQUEST.getMsg());
        }

        String token = authService.saveToken(request.getUsername());
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).
                header(AuthConstant.AUTHORIZATION_HEADER, token).
                body(ResultCode.SUCCESS.getMsg());
    }

    /**
     * 退登账号，需携带当前账号的auth token
     *
     * @return 根据账号状态返回是否退登成功
     */
    @DeleteMapping("/session")
    @Auth
    public ResponseEntity logout() {
        boolean isLogin = authService.isLogin(AuthContext.getAuthz());
        if (!isLogin) {
            return ResponseEntity.status(ResultCode.BAD_REQUEST.getCode()).body(ErrorMsgConstant.INVALID_TOKEN);
        }
        authService.logout(AuthContext.getAuthz());
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }

}
