package com.yhcoo.tsc.msg.convert;

import com.yhcoo.tsc.msg.dto.MessageLogDto;

import com.yhcoo.tsc.msg.pojo.MessageLog;

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    date = "2019-04-21T22:53:29+0800",

    comments = "version: 1.1.0.Final, compiler: javac, environment: Java 1.8.0_201 (Oracle Corporation)"

)

public class MessageConvertImpl implements MessageConvert {

    @Override

    public MessageLog dtoToPojo(MessageLogDto messageLogDto) {

        if ( messageLogDto == null ) {

            return null;
        }

        MessageLog messageLog = new MessageLog();

        return messageLog;
    }
}

