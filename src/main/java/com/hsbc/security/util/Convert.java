package com.hsbc.security.util;

import com.hsbc.security.api.dto.RoleDTO;
import com.hsbc.security.model.RoleDO;

import java.util.ArrayList;
import java.util.List;

public class Convert{
    public static RoleDTO convertToRoleDTO(RoleDO roleDO){
        RoleDTO roleDTO = new RoleDTO();
        if (roleDO == null){
            return roleDTO;
        }
        roleDTO.setRoleName(roleDO.getRoleName());
        return roleDTO;
    }

    public static List<RoleDTO> convertToRoleDTOs(List<RoleDO> roleDOs){
        if (roleDOs == null){
            return null;
        }

        List<RoleDTO> roleDTOS = new ArrayList<>(roleDOs.size());
        for (RoleDO roleDO: roleDOs){
            roleDTOS.add(convertToRoleDTO(roleDO));
        }

        return roleDTOS;
    }

}