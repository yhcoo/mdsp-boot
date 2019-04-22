package com.yhcoo.tsc.msg.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhcoo.tsc.msg.pojo.MessageLog;
import org.apache.ibatis.annotations.Select;


public interface MessageLogMapper extends BaseMapper<MessageLog> {

    @Select("SELECT msg.* FROM `message_log` msg WHERE message_id = #{messageId}")
    MessageLog queryMessageLogByMessageId(String messageId);
}
