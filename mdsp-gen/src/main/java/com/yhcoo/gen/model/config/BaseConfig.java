package com.yhcoo.gen.model.config;

import lombok.Data;

@Data
public class BaseConfig {

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


    public String getEntityPackageName() {
        return getPackageName() + ".model.entity";
    }

    public String getQueryPackageName() {
        return getPackageName() + ".model.query";
    }

    public String getServicePackageName() {
        return getPackageName() + ".service";
    }

    public String getServiceApiPackageName() {
        return getPackageName() + ".api";
    }

    public String getDaoPackageName() {
        return getPackageName() + ".dao";
    }

    public String getMapperPackageName() {
        return getPackageName() + ".mapper";
    }

    public String getControllerPackageName() {
        return getPackageName() + ".controller";
    }

}
