package com.yhcoo.tsc.alipay.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhcoo.tsc.alipay.pojo.AlipayTradeOrder;
import org.apache.ibatis.annotations.Select;


public interface AlipayTradeOrderMapper extends BaseMapper<AlipayTradeOrder> {
    @Select("SELECT * FROM alipay_trade_order WHERE order_no = #{orderNo}")
    AlipayTradeOrder queryAlipayTradeOrderByOrderNo(String orderNo);
}
