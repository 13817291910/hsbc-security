package com.hsbc.security.dao;

public interface AuthDAO {

    void login(String userName, String authToken);

    boolean isLogin(String token);

    void logOut(String userName);

    String getUserName(String token);

}
