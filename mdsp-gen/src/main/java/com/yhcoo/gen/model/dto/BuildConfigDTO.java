package com.yhcoo.gen.model.dto;

import lombok.Data;

import java.util.List;


@Data
public class BuildConfigDTO {

    private int buildCodeType; //1:单表，2:多表

    private String genType;

    private List<String> tableName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 模块名
     */
    private String moduleName;

    /**
     * 子项目名
     */
    private String subProjectName;

    /**
     * 子项目名
     */
    private String vueProjecePath;

    /**
     * 作者名称
     */
    private String authorName;

}
