package com.hsbc.security.dao;

import java.util.Set;

public interface UserRoleDAO {
    Set<String> getRoleNamesByUser(String userName);

    void removeByRoleName(String roleName);

    void saveRoleByUser(String roleName, String userName);

    void removeByUserName(String userName);

}
