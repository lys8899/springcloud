package com.lys.usermanager.controller;


import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.lys.usermanager.config.RabbitmqQueueConfig;
import com.lys.usermanager.entity.SysUser;
import com.lys.usermanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @description: 用户
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 15:10
 **/
@RestController
@RequestMapping("user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity saveUser(SysUser sysUser) {
        if (Objects.nonNull(sysUser)) {
            if (Strings.isNullOrEmpty(sysUser.getfId())) {
                SysUser u = userService.addUser(sysUser);
                Map<String, Object> addMsg = new HashMap<>(2);
                addMsg.put("id", u.getfId());
                addMsg.put("type", "userAdd");
                rabbitTemplate.convertAndSend(RabbitmqQueueConfig.Q_CLEAR_CACHE, addMsg);
                return ResponseEntity.ok(u.getfId());
            }
        }
        return ResponseEntity.badRequest().body("错误参数或不应存在id");
    }

    @GetMapping
    public ResponseEntity getUsers(@PageableDefault Pageable pageable, String name) {
        Page<SysUser> users = userService.listTable(name, pageable);
        return ResponseEntity.ok(users);
    }


    @GetMapping(value = "/{userId}", params = "type=simple")
    public ResponseEntity getUser(@PathVariable String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            ResponseEntity.badRequest().body("id不能为空");
        }
        Optional<SysUser> userById = userService.getUserById(userId);
        if (userById.isPresent()) {
            return ResponseEntity.ok(userById.get());
        }
        return ResponseEntity.badRequest().body("用户不存在");
    }

    @GetMapping(value = "/{userId}", params = "type=all")
    public ResponseEntity getUserAllMsg(@PathVariable String userId) {
        if (Strings.isNullOrEmpty(userId)) {
            ResponseEntity.badRequest().body("id不能为空");
        }
        Optional<SysUser> userById = userService.getUserByIdAll(userId);
        if (userById.isPresent()) {
            return ResponseEntity.ok(userById.get());
        }
        return ResponseEntity.badRequest().body("用户不存在");
    }


    @PutMapping(value = "/{userId}", params = "type=bindRoles")
    public ResponseEntity bindRoles(@PathVariable String userId, String roleIds) {
        if (Strings.isNullOrEmpty(roleIds) || Strings.isNullOrEmpty(userId)) {
            ResponseEntity.badRequest().body("roleIds不能为空");
        }

        String[] ids = roleIds.split(",");
        if (ids.length > 0) {
            userService.bindUserAndRoles(userId, ids);
            return ResponseEntity.ok(roleIds);
        } else {
            return ResponseEntity.badRequest().body("参数异常");
        }

    }

    @PutMapping(value = "/{userId}", params = "type=password")
    public ResponseEntity changeUsersPassword(@PathVariable String userId, Long version,
                                              String oldPassword, String newPassword) {
        if (Strings.isNullOrEmpty(userId) || Strings.isNullOrEmpty(oldPassword)
                || Strings.isNullOrEmpty(newPassword) || Objects.isNull(version)) {
            return ResponseEntity.badRequest().body("参数异常");
        }

        int code = userService.changePassword(userId, oldPassword, newPassword, version);
        if (code == 1) {
            return ResponseEntity.ok("修改成功！");
        } else if (code == 2) {
            return ResponseEntity.badRequest().body("用户不存在或当前信息已被修改");
        } else if (code == 3) {
            return ResponseEntity.badRequest().body("老密码不正确");
        }

        return ResponseEntity.badRequest().body("服务器错误，修改失败!");
    }


    @DeleteMapping
    public ResponseEntity deleteUsers(String ids) {
        if (Strings.isNullOrEmpty(ids)) {
            return ResponseEntity.badRequest().body("ids不能为空");
        }
        String[] split = ids.split(",");
        if (split.length > 0) {
            userService.deleteUsers(Sets.newHashSet(split));
            return ResponseEntity.ok(ids);
        } else {
            return ResponseEntity.badRequest().body("参数异常");
        }

    }

    @RequestMapping(value = "/current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(principal.toString());
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>");
        return principal;
    }


}
