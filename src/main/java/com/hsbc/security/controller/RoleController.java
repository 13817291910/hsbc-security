package com.hsbc.security.controller;

import com.hsbc.security.api.RoleService;
import com.hsbc.security.api.dto.CreateRoleRequest;
import com.hsbc.security.api.dto.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;

@RequestMapping("/hsbc/v1")
@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;

    /**
     * 创建角色
     *
     * @param request {@link CreateRoleRequest}
     * @return {@link ResponseEntity}
     */
    @PostMapping("/role")
    public ResponseEntity createRole(@RequestBody @NotBlank CreateRoleRequest request) {
        roleService.saveRole(request.getRoleName());
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }

    /**
     * 删除角色
     *
     * @param roleName
     * @return {@link ResponseEntity}
     */
    @DeleteMapping("/role/{name}")
    public ResponseEntity removeRole(@PathVariable("name") @NotBlank String roleName) {
        roleService.removeRoleByName(roleName);
        return ResponseEntity.status(ResultCode.SUCCESS.getCode()).body(ResultCode.SUCCESS.getMsg());
    }
}
