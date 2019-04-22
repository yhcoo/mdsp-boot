package com.yhcoo.gen.mapper;

import com.yhcoo.gen.model.entity.SysGenDbConfig;
import com.yhcoo.gen.model.query.SysGenDbConfigQuery;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Param;


/**
 * @author kingkey
 */
@Component
public interface SysGenDbConfigMapper extends BaseMapper<SysGenDbConfig>{
  /**
   * 信息分页查询
   * @param query
   */
  IPage<SysGenDbConfig> pageByQuery(SysGenDbConfigQuery query);
}


