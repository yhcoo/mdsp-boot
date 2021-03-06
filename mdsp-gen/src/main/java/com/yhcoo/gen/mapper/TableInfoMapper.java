package com.yhcoo.gen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yhcoo.gen.model.entity.TableInfo;
import com.yhcoo.gen.model.query.TableInfoQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
public interface TableInfoMapper extends BaseMapper<TableInfo> {

    /**
     * 分页查询表信息
     * @param tableInfoQuery
     * @return
     */
    IPage<TableInfo> pageByQuery(TableInfoQuery tableInfoQuery);


    /**
     * 查询单个表信息
     * @param tableName
     * @return
     */
    TableInfo getOne(@Param("tableName") String tableName);

}
