package com.hsbc.security.service;

import com.hsbc.security.api.RoleService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RoleServiceTest {

    @Autowired
    private RoleService roleSerice;

    @Test
    public void AddRole() {
        roleSerice.saveRole("11");
        Assertions.assertNotNull(roleSerice.getRole("11"));
    }

    @Test
    public void removeRole() {
        roleSerice.saveRole("11");
        roleSerice.removeRoleByName("11");
        Assertions.assertNull(roleSerice.getRole("11"));
    }


}
