package com.hsbc.security.model;

import com.hsbc.security.model.RoleDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.N;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDO {
    private String UserName;
    private String PasswordHash;
}
