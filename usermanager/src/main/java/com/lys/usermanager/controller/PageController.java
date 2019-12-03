package com.lys.usermanager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description: 首页
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 16:43
 **/
/*@Controller("/page")*/
public class PageController {

    /**
     * @Description 跳转主页
     * @date 2017/12/20 10:2
     */
    @GetMapping("/index")
    public String goToIndex() {
        return "index";
    }

    /**
     * @Description 跳转主页
     * @date 2017/12/20 10:2
     */
    @GetMapping("/login")
    public String goToLogin() {
        return "login";
    }
}
