package com.hsbc.security.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class RolesResponse extends BaseResponse {
    private List<RoleDTO> roles;
}
