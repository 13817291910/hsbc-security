package com.hsbc.security.dao;

import com.github.benmanes.caffeine.cache.Cache;
import com.hsbc.security.model.RoleDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private Cache<String, RoleDO> roleCache;

    public void saveRole(RoleDO role) {
        roleCache.asMap().put(role.getRoleName(), role);
    }

    public void removeRoleByName(String roleName) {
        roleCache.asMap().remove(roleName);
    }

    public RoleDO getRoleByName(String roleName) {
        return roleCache.asMap().get(roleName);
    }

    public List<RoleDO> getRolesByNames(List<String> roleNames) {
        Map<String, RoleDO> roles = roleCache.getAllPresent(roleNames);
        return new ArrayList<>(roles.values());
    }

    public List<RoleDO> listRoles() {
        return (List<RoleDO>) roleCache.asMap().values();
    }
}
