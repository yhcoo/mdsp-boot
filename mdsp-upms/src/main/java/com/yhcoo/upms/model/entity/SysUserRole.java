package com.yhcoo.upms.model.entity;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 用户角色关联表
 * </p>
 *
 */
@Data
@Accessors(chain = true)
public class SysUserRole{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long userId;

    /**
     * 主键
     */
    @JsonSerialize(using= ToStringSerializer.class)
    private Long roleId;


}
