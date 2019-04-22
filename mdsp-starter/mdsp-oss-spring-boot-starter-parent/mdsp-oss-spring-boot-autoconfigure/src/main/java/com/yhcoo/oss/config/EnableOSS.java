package com.yhcoo.oss.config;

import com.yhcoo.oss.OSSConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author KingKey
 * @date 2018/7/21
 * 开启pigx swagger
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({OSSConfig.class})
public @interface EnableOSS {
}
