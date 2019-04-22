package com.yhcoo.tsc.personal.client;

import com.yhcoo.tsc.msg.api.MdspMessageApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("mdsp-transaction-service")
public interface PersonalMessageClient extends MdspMessageApi {
}
