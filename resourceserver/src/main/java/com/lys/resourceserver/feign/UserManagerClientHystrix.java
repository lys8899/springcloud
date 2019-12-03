package com.lys.resourceserver.feign;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liyongsen
 */
@Component
public class UserManagerClientHystrix implements UserManagerClient {
    @Override
    public Map<String, Object> getUsers(int page, int size, String name,String type) {
        Map map = new HashMap(2);
        map.put("code", "Hystrix");
        return map;
    }

    @Override
    public Map getUser(String userId, String type) {
        Map map = new HashMap(2);
        map.put("code", "Hystrix");
        return map;
    }

    @Override
    public String deleteUsers(String ids) {
        return "Hystrix";
    }
}
