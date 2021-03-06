package com.yhcoo.tsc.msg.convert;


import com.yhcoo.tsc.msg.dto.MessageLogDto;
import com.yhcoo.tsc.msg.pojo.MessageLog;
import org.mapstruct.factory.Mappers;



@org.mapstruct.Mapper
public interface MessageConvert {
    MessageConvert MAPPER = Mappers.getMapper(MessageConvert.class);

    MessageLog dtoToPojo(MessageLogDto messageLogDto);

}
