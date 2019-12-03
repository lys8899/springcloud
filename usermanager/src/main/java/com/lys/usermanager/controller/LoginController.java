package com.lys.usermanager.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;




/**
 * @author 李永森[825760990@qq.com]
 * @time 14:06  2017/12/4
 * @describe 用户登陆
 */
@RestController
@RequestMapping(("login"))
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);


    /**
     * 跳转登陆页面
     *
     * @date 2017/12/20 10:2
     */
    @GetMapping("/page")
    public ModelAndView goToPageLogin() {
        return new ModelAndView("login");
    }

    /**
     * 登陆失败页面
     *
     * @date 2017/12/20 10:2
     */
    /*@GetMapping("/login-error")
    public ModelAndView goToPageLoginError(HttpSession session) {
        RuntimeException attribute = (RuntimeException) session.getAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        String message = attribute.getMessage();
        session.setAttribute(WebAttributes.AUTHENTICATION_EXCEPTION, new RuntimeException("测试错误信息！"));
        ModelAndView modelAndView=new ModelAndView("login");
        modelAndView.addObject("loginError", true);
        return modelAndView;
    }*/


    /**
     * @Description 页面跳转用户列表
     * @date 2017/12/1 15:22
     */
    @GetMapping("/users")
    public String goToPageUserList() {
        return "user/userList";
    }


    /**
     * @Description 页面跳转用户列表
     * @date 2017/12/1 15:22
     */
    @RequestMapping("/userAdd")
    public String goToPageUserAdd() {
        return "user/userAdd";
    }

}
