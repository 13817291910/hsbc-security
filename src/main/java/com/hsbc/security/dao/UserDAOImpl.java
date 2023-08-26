package com.hsbc.security.dao;

import com.github.benmanes.caffeine.cache.Cache;
import com.hsbc.security.model.UserDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private Cache<String, Object> userCache;

    public void save(UserDO user){
        userCache.put(user.getUserName(), user);
    }


    public boolean removeByUsername(String userName){
        return userCache.asMap().remove(userName) == null;
    }

    public UserDO getByUsername(String userName){
        if (userCache.asMap().containsKey(userName)){
            return (UserDO) userCache.asMap().get(userName);
        }

        return null;
    }

}
