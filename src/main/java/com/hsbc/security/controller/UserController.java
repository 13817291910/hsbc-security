package com.hsbc.security.controller;

import com.hsbc.security.aop.auth.Auth;
import com.hsbc.security.aop.auth.AuthContext;
import com.hsbc.security.api.AuthService;
import com.hsbc.security.api.UserService;
import com.hsbc.security.api.dto.CreateUserRequest;
import com.hsbc.security.api.dto.ResultCode;
import com.hsbc.security.api.dto.RoleDTO;
import com.hsbc.security.api.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Controller
@RequestMapping("/hsbc/v1")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthService authService;

    /**
     * 创建用户
     *
     * @param request
     */
    @PostMapping("/user")
    public ResponseEntity createUser(@RequestBody CreateUserRequest request) {
        userService.create(request);
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }

    /**
     * 删除用户
     *
     * @param name 用户名
     * @return
     */
    @DeleteMapping("/user/{name}")
    @Auth
    public ResponseEntity deleteUser(@PathVariable("name") String name) {
        userService.remove(name);
        authService.logout(AuthContext.getAuthz());
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }

    /**
     * 校验该用户是否拥有特定角色
     *
     * @param userName 用户名
     * @param roleName 角色名
     * @return
     */
    @GetMapping("/user/{user_name}/role/{role_name}")
    @Auth
    public ResponseEntity checkRole(@PathVariable("user_name") @NotBlank String userName, @PathVariable("role_name") @NotBlank String roleName) {
        boolean isExist = userService.checkRole(userName, roleName);
        if (!isExist) {
            return ResponseEntity.status(ResultCode.UNAUTHORIZED.getCode()).body(ResultCode.UNAUTHORIZED.getMsg());
        }
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }

    /**
     * 列出该用户的所有的角色名
     *
     * @param userName 用户名
     * @return 所有的角色名
     */
    @GetMapping("/user/{name}/roles")
    public ResponseEntity listRoles(@PathVariable("name") @NotBlank String userName) {
        List<RoleDTO> rolesDTO = userService.listRolesByUserName(userName);
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(rolesDTO);
    }

    /**
     * 给当前用户绑定特定角色
     *
     * @param userName 用户名
     * @param roleName 角色名
     * @return 是否绑定成功
     */
    @PostMapping("/user/{user_name}/role/{role_name}")
    @Auth
    public ResponseEntity bindRole(@PathVariable("user_name") @NotBlank String userName, @PathVariable("role_name") @NotBlank String roleName) {
        UserDTO userDTO = userService.getByUserName(userName);
        if (userDTO == null) {
            ResponseEntity.status(ResultCode.FAILURE.getCode()).body(ResultCode.FAILURE.getMsg());
        }

        userService.bindRole(roleName, userName);
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }
}
