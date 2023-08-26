package com.hsbc.security.api;

import com.hsbc.security.api.dto.CreateUserRequest;
import com.hsbc.security.api.dto.RoleDTO;
import com.hsbc.security.api.dto.UserDTO;

import java.util.List;

public interface UserService {

    /**
     * 创建用户
     *
     * @param request
     */
    void create(CreateUserRequest request);

    /**
     * 根据用户名查询用户
     *
     * @param userName
     * @return
     */
    UserDTO getByUserName(String userName);

    /**
     * 删除用户
     *
     * @param userName 用户名
     */
    void remove(String userName);

    /**
     * 给特定用户绑定角色
     *
     * @param roleName 角色名
     * @param userName 用户名
     */
    void bindRole(String roleName, String userName);

    /**
     * 列出特定用户所属的角色名
     *
     * @param userName 用户名
     * @return
     */
    List<RoleDTO> listRolesByUserName(String userName);

    /**
     * 查询特定用户是否拥有所属角色
     *
     * @param roleName 角色名
     * @param userName 用户名
     * @return
     */
    boolean checkRole(String roleName, String userName);

}
