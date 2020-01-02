package com.lys.usermanager.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author liyongsen
 */
@RestController
@RequestMapping("TestGateway")
public class TestGatewayController {

    @Value("${server.port}")
    private String port;

    @RequestMapping("test01")
    public String test01() {
        return "test01_" + port;
    }

    @RequestMapping("test02")
    public String test02() {
        return "test02_" + port;
    }

    @RequestMapping("test03")
    public String test03() {
        return "test03_" + port;
    }


}
