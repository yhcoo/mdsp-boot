package com.yhcoo.gateway.feign;

import com.yhcoo.common.vo.SysResourceVO;
import com.yhcoo.gateway.config.FeignConfig;
import com.yhcoo.gateway.feign.fallback.SysResourceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;


@FeignClient(name = "mdsp-upms-service",fallback = SysResourceFallback.class, configuration = FeignConfig.class)
public interface SysResourceService {

    /**
     * 根据角色查询资源信息
     * @param roleCode
     * @return
     */
    @GetMapping("/resource/role/{roleCode}")
    Set<SysResourceVO> listResourceByRole(@PathVariable("roleCode") String roleCode);


}
