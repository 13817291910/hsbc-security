package com.hsbc.security.service;

import com.hsbc.security.api.AuthService;
import com.hsbc.security.dao.AuthDAO;
import com.hsbc.security.dao.UserDAO;
import com.hsbc.security.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthDAO authDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String saveToken(String userName) {
        // 这里用于demo，因此简化token生成流程，uuid有极低概率发生冲突
        UUID uuid = UUID.randomUUID();
        authDAO.login(userName, uuid.toString());
        return uuid.toString();
    }

    @Override
    public boolean isValid(String username, String password) {
        UserDO userDO = userDAO.getByUsername(username);
        return passwordEncoder.matches(password, userDO.getPasswordHash());
    }

    @Override
    public boolean isLogin(String token) {
        return authDAO.isLogin(token);
    }

    @Override
    public void logout(String token) {
        authDAO.logOut(token);
    }


}
