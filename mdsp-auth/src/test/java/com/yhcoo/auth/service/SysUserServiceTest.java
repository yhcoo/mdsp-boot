package com.yhcoo.auth.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserServiceTest {
//
//    @Autowired
//    private SysUserService sysUserService;

//    @Test
//    public void findByUsername() {
//        SysUserVo sysUserVo = sysUserService.loadUserByUsername("yhcoo");
//        System.out.println("=============="+sysUserVo.getUsername());
//    }

    @Test
    public void testPassword() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = passwordEncoder.encode("mdspwd");
        System.out.println("======= fisher ======="+password);
    }


}