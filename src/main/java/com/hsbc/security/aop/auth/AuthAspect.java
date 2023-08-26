package com.hsbc.security.aop.auth;

import com.hsbc.security.api.AuthService;
import com.hsbc.security.api.dto.ResultCode;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

/**
 * 面向切面验证身份
 */
@Component
@Aspect
public class AuthAspect {
    @Autowired
    private AuthService authService;

    @Pointcut("@annotation(com.hsbc.security.aop.auth.Auth)")
    public void authPointcut() {

    }

    @Before("authPointcut()")
    public ResponseEntity authBefore(ProceedingJoinPoint joinPoint) {
        String token = AuthContext.getAuthz();
        if (!authService.isLogin(token)) {
            return ResponseEntity.status(ResultCode.UNAUTHORIZED.getCode()).body(ResultCode.UNAUTHORIZED.getMsg());
        }
        Object res;
        try {
            res = joinPoint.proceed();
        } catch (Throwable e) {
            return ResponseEntity.status(ResultCode.FAILURE.getCode()).body(ResultCode.FAILURE.getMsg());
        }
        return (ResponseEntity) res;
    }

}
