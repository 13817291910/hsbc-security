package com.hsbc.security.service;

import com.hsbc.security.api.RoleService;
import com.hsbc.security.api.UserService;
import com.hsbc.security.api.dto.CreateUserRequest;
import com.hsbc.security.api.dto.RoleDTO;
import com.hsbc.security.api.dto.UserDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @BeforeEach
    public void prepare() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("kd");
        request.setPassword("123");
        userService.create(request);
        roleService.saveRole("1");
        userService.bindRole("1", "kd");
    }

    @Test
    public void createUser() {
        CreateUserRequest request = new CreateUserRequest();
        request.setUsername("kd1");
        request.setPassword("1234");
        userService.create(request);
        UserDTO userDTO = userService.getByUserName(request.getUsername());
        Assertions.assertEquals(userDTO.getUsername(), userDTO.getUsername());
    }

    @Test
    public void removeUser() {
        userService.remove("kd");
        UserDTO userDTO = userService.getByUserName("kd");
        Assertions.assertNull(userDTO);
    }

    @Test
    public void bindRole() {
        roleService.saveRole("11");
        userService.bindRole("11", "kd");
        Assertions.assertTrue(userService.checkRole("11", "kd"));
    }

    @Test
    public void listRules() {
        List<RoleDTO> rolesDTO = userService.listRolesByUserName("kd");
        Assertions.assertEquals("1", rolesDTO.get(0).getRoleName());
    }

}
