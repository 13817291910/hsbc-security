package com.hsbc.security.service;

import com.hsbc.security.api.RoleService;
import com.hsbc.security.api.dto.RoleDTO;
import com.hsbc.security.dao.RoleDAO;
import com.hsbc.security.dao.UserRoleDAO;
import com.hsbc.security.error.ServiceException;
import com.hsbc.security.model.RoleDO;
import com.hsbc.security.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;

    @Override
    public void saveRole(String roleName) {
        RoleDO role = roleDAO.getRoleByName(roleName);
        if (role != null){
            return;
        }

        role = new RoleDO();
        role.setRoleName(roleName);
        roleDAO.saveRole(role);
    }

    @Override
    public RoleDTO getRole(String roleName) {
        RoleDO roleDO = roleDAO.getRoleByName(roleName);
        if (roleDO == null){
            return null;
        }

        return Convert.convertToRoleDTO(roleDO);
    }

    @Override
    public void removeRoleByName(String roleName) {
        roleDAO.removeRoleByName(roleName);
        userRoleDAO.removeByRoleName(roleName);
    }

    public List<RoleDO> listRoles() {
        return roleDAO.listRoles();
    }
}
