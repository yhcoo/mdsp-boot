package com.yhcoo.upms.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * <p>
 * 角色表
 * </p>
 *
 */
@Data
@Accessors(chain = true)
public class SysRole{

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(value = "role_id", type = IdType.ID_WORKER)
    private Long roleId;

    /**
     * 角色code用于springsecurity角色标识码
     */
    private String roleCode;

    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    private LocalDateTime modifyTime;

    /**
     * 是否删除 1-删除，0-未删除
     */
    private String delFlag;


}
