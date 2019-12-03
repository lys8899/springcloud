package com.lys.resourceserver.feign;

import com.lys.resourceserver.config.FeignConfig;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author liyongsen
 */
@FeignClient(value = "USERMANAGER", configuration = FeignConfig.class, fallback = UserManagerClientHystrix.class)
public interface UserManagerClient {

    /**
     * 查询用户列表
     *
     * @param page
     * @param size
     * @param name
     * @param type
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    Map<String, Object> getUsers(@RequestParam("page") int page,
                                 @RequestParam("size") int size,
                                 @RequestParam("name") String name,
                                 @RequestParam("type") String type);


    /**
     * 获取用户详细信息
     *
     * @param userId 用户id
     * @param type   simple:用户表信息；all:包含角色权限
     * @return ids
     */
    @RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
    Map<String, Object> getUser(@PathVariable("userId") String userId,
                                @RequestParam("type") String type);


    /**
     * 删除用户
     *
     * @param ids 删除id用,号拼接
     * @return ids
     */
    @RequestMapping(value = "/user", method = RequestMethod.DELETE)
    String deleteUsers(@RequestParam("ids") String ids);


}
