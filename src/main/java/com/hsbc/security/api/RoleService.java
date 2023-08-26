package com.hsbc.security.api;

import com.hsbc.security.api.dto.RoleDTO;

public interface RoleService {
    /**
     * 创建角色
     *
     * @param roleName 角色名称，唯一
     */
    void saveRole(String roleName);

    /**
     * 根据角色名删除角色，此时会把所有用户绑定的该角色也一并删除
     *
     * @param roleName
     */
    void removeRoleByName(String roleName);

    /**
     * 获取角色
     *
     * @param roleName 角色名
     * @return 角色
     */
    RoleDTO getRole(String roleName);
}
