package com.yhcoo.upms.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yhcoo.upms.model.dto.SysRoleDTO;
import com.yhcoo.upms.model.entity.SysRole;
import com.yhcoo.upms.model.query.SysRoleQuery;
import java.util.List;

public interface SysRoleService extends IService<SysRole> {

    /**
     * 添加角色信息 带权限资源信息
     * @param sysRoleDTO
     * @return
     */
    Boolean save(SysRoleDTO sysRoleDTO);

    /**
     * 更新角色信息 带权限资源信息
     * @param sysRoleDTO
     * @return
     */
    Boolean updateById(SysRoleDTO sysRoleDTO);

    /**
     * 根据id删除角色信息  同时删除与其绑定的资源信息
     * @param roleId
     * @return
     */
    Boolean deleteById(Long roleId);

    /**
     * 根据角色id查询角色信息与其绑定的资源id
     * @param roleId
     * @return
     */
    SysRoleDTO getRoleInfoWithResourceById(Long roleId);

    /**
     * 分页条件查询
     * @param query
     * @return
     */
    SysRoleQuery pageByQuery(SysRoleQuery query);

    /**
     * 查询所有的角色
     * @return
     */
    List<SysRole> listSysRole();

}
