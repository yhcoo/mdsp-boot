package com.yhcoo.tsc.personal.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yhcoo.tsc.personal.pojo.PersonalBalanceAccount;
import org.apache.ibatis.annotations.Select;


public interface PersonalBalanceAccountMapper extends BaseMapper<PersonalBalanceAccount> {

    @Select("SELECT * FROM `personal_balance_account` WHERE user_id=#{userId}")
    PersonalBalanceAccount queryBalanceTreasureAccountByUserId(long userId);
}
