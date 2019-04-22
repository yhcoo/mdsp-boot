package com.yhcoo.tsc.alipay.client;

import com.yhcoo.tsc.msg.api.MdspMessageApi;
import org.springframework.cloud.openfeign.FeignClient;


@FeignClient("mdsp-transaction-service")
public interface AlipayMessageClient extends MdspMessageApi {
}
