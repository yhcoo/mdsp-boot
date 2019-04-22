package com.yhcoo.upms.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;
import com.yhcoo.upms.model.entity.SysUser;
import com.yhcoo.upms.model.query.SysUserVoQuery;
import com.yhcoo.common.vo.SysUserVo;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 根据用户名查询用户信息（包括角色信息）
     * @param username 用户名
     * @return
     */
    SysUserVo loadUserByUsername(String username);

    /**
     * 根据用户名查询用户信息（包括角色信息）
     * @param mobile 手机号
     * @return
     */
    SysUserVo loadUserByMobile(String mobile);

    /**
     * 用户信息分页查询
     * @param query
     * @return
     */
    IPage<SysUserVo> pageUserVoByQuery(SysUserVoQuery query);

    /**
     * 统计
     * @param username
     * @return
     */
    Long countUserByQuery(@Param("username") String username);

}
