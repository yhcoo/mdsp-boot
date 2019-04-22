package com.yhcoo.gen.service;


import com.yhcoo.gen.model.dto.BuildConfigDTO;

public interface SysGenService {

    /**
     * 根据表名生成代码
     * @param buildConfigDTO
     * @return
     */
    byte[] genCodeByTableName(BuildConfigDTO buildConfigDTO);

}
