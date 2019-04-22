package com.yhcoo.gen.model.query;

import com.yhcoo.gen.model.entity.SysGenDbConfig;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import java.util.Date;


/**
 * @author kingkey
 */
@Data
public class SysGenDbConfigQuery extends  Page<SysGenDbConfig> {

        /**
     * 主键
     */
    private Long id;
        /**
     * 数据库地址
     */
    private String host;
        /**
     * 数据库端口
     */
    private String port;
        /**
     * 数据库类型
     */
    private String dbType;
        /**
     * jdbc驱动类名
     */
    private String driverClassName;
        /**
     * 具体数据库名
     */
    private String databaseName;
        /**
     * 用户名
     */
    private String userName;
        /**
     * 密码
     */
    private String password;
        /**
     * 创建时间
     */
    private Date createTime;
        /**
     * 更新时间
     */
    private Date modifyTime;
    
}
