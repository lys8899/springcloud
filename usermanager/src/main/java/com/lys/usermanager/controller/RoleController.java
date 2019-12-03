package com.lys.usermanager.controller;

import com.google.common.base.Strings;
import com.google.common.collect.Sets;
import com.lys.usermanager.entity.SysRole;
import com.lys.usermanager.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 角色
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-09 13:38
 **/
@RestController
@RequestMapping("role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @PostMapping
    public ResponseEntity saveRole(SysRole sysRole) {
        SysRole sysRole1 = roleService.saveRole(sysRole);
        return ResponseEntity.ok(sysRole1.getfId());
    }


    @PostMapping(params = "delete")
    public ResponseEntity deleteRole(String ids) {
        if (Strings.isNullOrEmpty(ids)) {
            ResponseEntity.badRequest().body("ids不能为空");
        }

        String[] split = ids.split(",");
        roleService.deleteRoles(Sets.newHashSet(split));
        return ResponseEntity.ok(ids);
    }


}
