package com.yhcoo.gen.service.impl;

import com.yhcoo.gen.mapper.ColumnInfoMapper;
import com.yhcoo.gen.mapper.TableInfoMapper;
import com.yhcoo.gen.model.dto.BuildConfigDTO;
import com.yhcoo.gen.model.entity.ColumnInfo;
import com.yhcoo.gen.model.entity.TableInfo;
import com.yhcoo.gen.service.SysGenService;
import com.yhcoo.gen.util.GenUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.ZipOutputStream;


@Service
public class SysGenServiceImpl implements SysGenService {

    @Autowired
    private TableInfoMapper tableInfoMapper;

    @Autowired
    private ColumnInfoMapper columnInfoMapper;

    @Override
    public byte[] genCodeByTableName(BuildConfigDTO buildConfigDTO) {

        if(buildConfigDTO.getBuildCodeType() == 1){
            return genCodeSingle(buildConfigDTO);
        }else{
            return genCodeToFile(buildConfigDTO);
        }
    }

    public byte[] genCodeSingle(BuildConfigDTO buildConfigDTO) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);

        for (String tableName : buildConfigDTO.getTableName()) {
            //查询表信息
            TableInfo table = tableInfoMapper.getOne(tableName);
            //查询列信息
            List<ColumnInfo> columns = columnInfoMapper.listByTableName(tableName);
            //生成代码
            GenUtil.generatorCode(buildConfigDTO,table, columns, zip);
        }
        IOUtils.closeQuietly(zip);
        return outputStream.toByteArray();
    }

    public byte[] genCodeToFile(BuildConfigDTO buildConfigDTO) {
        for (String tableName : buildConfigDTO.getTableName()) {
            //查询表信息
            TableInfo table = tableInfoMapper.getOne(tableName);
            //查询列信息
            List<ColumnInfo> columns = columnInfoMapper.listByTableName(tableName);
            //生成代码
            GenUtil.generatorCode(buildConfigDTO,table, columns, null);
        }
        return null;
    }
}
