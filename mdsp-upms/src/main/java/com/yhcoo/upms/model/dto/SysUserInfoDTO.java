package com.yhcoo.upms.model.dto;

import com.yhcoo.upms.model.entity.SysUser;
import lombok.Data;

import java.util.List;

@Data
public class SysUserInfoDTO {

    private SysUser sysUser;

    private List<String> roles;

    private List<String> permissions;
}
