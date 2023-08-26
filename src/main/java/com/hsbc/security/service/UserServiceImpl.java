package com.hsbc.security.service;

import com.hsbc.security.api.UserService;
import com.hsbc.security.api.dto.CreateUserRequest;
import com.hsbc.security.api.dto.ResultCode;
import com.hsbc.security.api.dto.RoleDTO;
import com.hsbc.security.api.dto.UserDTO;
import com.hsbc.security.dao.RoleDAO;
import com.hsbc.security.dao.UserDAO;
import com.hsbc.security.dao.UserRoleDAO;
import com.hsbc.security.error.ErrorMsgConstant;
import com.hsbc.security.error.ServiceException;
import com.hsbc.security.model.RoleDO;
import com.hsbc.security.model.UserDO;
import com.hsbc.security.util.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private RoleDAO roleDAO;
    @Autowired
    private UserRoleDAO userRoleDAO;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void create(CreateUserRequest request) {
        UserDO userDO= userDAO.getByUsername(request.getUsername());
        if (userDO != null){
            throw new ServiceException(ResultCode.FAILURE, ErrorMsgConstant.DUPLICATED_USER);
        }

        userDO = new UserDO();
        userDO.setUserName(request.getUsername());
        userDO.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userDAO.save(userDO);
    }

    @Override
    public UserDTO getByUserName(String userName) {
        UserDO userDO = userDAO.getByUsername(userName);
        if (userDO == null) {
            return null;
        }

        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(userDO.getUserName());
        return userDTO;
    }

    @Override
    public void remove(String userName) {
        userDAO.removeByUsername(userName);
        userRoleDAO.removeByUserName(userName);
    }

    @Override
    public void bindRole(String roleName, String userName) {
        RoleDO roleDO = roleDAO.getRoleByName(roleName);
        if (roleDO == null){
            throw new ServiceException(ErrorMsgConstant.ROLE_NOT_EXIST);
        }

        userRoleDAO.saveRoleByUser(roleName, userName);
    }

    @Override
    public List<RoleDTO> listRolesByUserName(String userName) {
        Set<String> roleNames = userRoleDAO.getRoleNamesByUser(userName);
        if (roleNames == null){
            return null;
        }

        List<RoleDO> rolesDO = roleDAO.getRolesByNames(new ArrayList<>(roleNames));
        return Convert.convertToRoleDTOs(rolesDO);
    }

    public boolean checkRole(String roleName, String userName) {
        Set<String> roleNames = userRoleDAO.getRoleNamesByUser(userName);
        if (roleNames == null){
            return false;
        }

        return roleNames.contains(roleName);
    }

}
