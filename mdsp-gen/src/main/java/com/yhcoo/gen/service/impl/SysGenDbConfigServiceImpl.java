package com.yhcoo.gen.service.impl;


import com.yhcoo.gen.model.entity.SysGenDbConfig;
import com.yhcoo.gen.mapper.SysGenDbConfigMapper;
import com.yhcoo.gen.model.query.SysGenDbConfigQuery;
import com.yhcoo.gen.service.SysGenDbConfigService;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author kingkey
 */
@Service
public class SysGenDbConfigServiceImpl extends ServiceImpl<SysGenDbConfigMapper, SysGenDbConfig> implements SysGenDbConfigService {

  @Autowired
  private SysGenDbConfigMapper sysGenDbConfigMapper;

  @Override
  public SysGenDbConfigQuery pageByQuery(SysGenDbConfigQuery query) {
    query.setDesc("create_time");
    sysGenDbConfigMapper.pageByQuery(query);
    return query;
  }

}