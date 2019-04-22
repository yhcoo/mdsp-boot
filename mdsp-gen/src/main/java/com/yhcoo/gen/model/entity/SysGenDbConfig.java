package com.yhcoo.gen.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;
import java.util.Date;


/**
 * 代码生成表
 *
 * @author kingkey
 * @date 2019-04-10 01:15:40
 */
@Data
@Accessors(chain = true)
public class SysGenDbConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @JsonSerialize(using= ToStringSerializer.class)
    @TableId(value = "id", type = IdType.ID_WORKER)
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