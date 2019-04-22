package com.yhcoo.upms.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yhcoo.upms.model.entity.SysRole;
import com.yhcoo.upms.model.query.SysRoleQuery;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 角色表 Mapper 接口
 * </p>
 */
@Component
public interface SysRoleMapper extends BaseMapper<SysRole> {


    /**
     * 分页查询
     * @param query
     * @return
     */
    IPage<SysRole> pageByQuery(SysRoleQuery query);


}
