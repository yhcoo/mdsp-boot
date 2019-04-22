package com.yhcoo.gen.service;


import com.yhcoo.gen.model.entity.SysGenDbConfig;
import com.yhcoo.gen.model.query.SysGenDbConfigQuery;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author kingkey
 */
public interface SysGenDbConfigService extends IService<SysGenDbConfig> {

  /**
   * 分页条件查询
   * @param query
   * @return
   */
    SysGenDbConfigQuery pageByQuery(SysGenDbConfigQuery query);

}