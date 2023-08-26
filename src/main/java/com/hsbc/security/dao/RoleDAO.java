package com.hsbc.security.dao;

import com.hsbc.security.model.RoleDO;

import java.util.List;

public interface RoleDAO {
    void saveRole(RoleDO role);

    RoleDO getRoleByName(String roleName);

    List<RoleDO> getRolesByNames(List<String> roleNames);

    void removeRoleByName(String roleName);

    List<RoleDO> listRoles();


}
