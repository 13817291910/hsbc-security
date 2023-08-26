package com.hsbc.security.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 创建角色请求
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {
    // 权限名
    private String roleName;
}
