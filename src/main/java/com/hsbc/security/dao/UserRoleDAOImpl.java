package com.hsbc.security.dao;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO {

    @Autowired
    private Cache<String, Set<String>> userRolesCache;

    @Override
    public Set<String> getRoleNamesByUser(String userName) {
        return userRolesCache.asMap().get(userName);
    }

    @Override
    public void removeByRoleName(String roleName) {
        for (Map.Entry<String, Set<String>> entry: userRolesCache.asMap().entrySet()){
            Set<String> roleNames = entry.getValue();
            roleNames.remove(roleName);
        }
    }

    @Override
    public void saveRoleByUser(String roleName, String userName) {
        Set<String> roles = userRolesCache.asMap().getOrDefault(userName, new HashSet<>());
        roles.add(roleName);
        userRolesCache.put(userName, roles);
    }

    @Override
    public void removeByUserName(String userName) {
        userRolesCache.asMap().remove(userName);
    }
}
