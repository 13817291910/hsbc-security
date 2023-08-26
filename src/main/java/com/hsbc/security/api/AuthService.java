package com.hsbc.security.api;

public interface AuthService {

    String saveToken(String userName);

    /**
     * 校验用户名密码组合的正确性
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回正确或错误
     */
    boolean isValid(String username, String password);

    /**
     * 判断用户是否登录
     *
     * @param token 身份认证token
     * @return 返回已登录或未登录
     */
    boolean isLogin(String token);

    /**
     * 登出用户
     *
     * @param token 身份认证token
     */
    void logout(String token);
}
