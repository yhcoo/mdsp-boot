package com.yhcoo.log.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yhcoo.log.model.entity.SysLog;
import com.yhcoo.log.model.query.SysLogQuery;
import org.springframework.stereotype.Component;


@Component
public interface SysLogMapper extends BaseMapper<SysLog> {

    /**
     * 日志信息分页查询
     * @param query
     */
    IPage<SysLog> pageByQuery(SysLogQuery query);
}
