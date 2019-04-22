package com.yhcoo.gen.model.query;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yhcoo.gen.model.entity.TableInfo;
import lombok.Data;


@Data
public class TableInfoQuery extends Page<TableInfo> {

    private String tableName;


}
