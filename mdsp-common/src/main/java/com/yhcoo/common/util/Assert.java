package com.yhcoo.common.util;


import com.yhcoo.common.exception.MdspException;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * 数据校验
 *
 * @author KingKey
 */
public abstract class Assert {

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Boolean ok, String message){
        if( ok ) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Object[] objs, String message){
        if(objs==null||objs.length==0) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Integer integer, String message){
        if(integer==null ) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Collection collection, String message){
        if(collection==null||collection.size()==0) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Map map, String message){
        if(map==null||map.size()==0) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(String str, String message){
        if(str == null || "".equals(str.trim()) ||
           "null".equals(str.toLowerCase()) || RegexUtil.isNull(str)){
            throw new MdspException(message);
        }
    }

    public static void isBlank(Long longs, String message){
        if(longs==null||longs==0) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Number number, String message){
        if( number == null) {
            throw new MdspException(message);
        }
    }

    public static void isBlank(Date date, String message){
        if(date==null) {
            throw new MdspException(message);
        }
    }
}
