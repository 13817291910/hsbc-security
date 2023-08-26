package com.hsbc.security.dao;

import com.hsbc.security.model.UserDO;

public interface UserDAO {
    void save(UserDO user);

    boolean removeByUsername(String userName);

    UserDO getByUsername(String userName);


}
