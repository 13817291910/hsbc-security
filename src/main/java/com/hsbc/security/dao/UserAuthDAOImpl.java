package com.hsbc.security.dao;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserAuthDAOImpl implements AuthDAO {

    @Autowired
    private Cache<String, String> userAuthCache;

    @Override
    public void login(String userName, String token) {
        userAuthCache.asMap().put(token, userName);
    }

    @Override
    public boolean isLogin(String token) {
        return userAuthCache.getIfPresent(token) == null;
    }

    @Override
    public void logOut(String token) {
        userAuthCache.invalidate(token);
    }

    @Override
    public String getUserName(String token) {
        return null;
    }
}
