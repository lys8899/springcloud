package com.lys.usermanager.controller;


import com.lys.usermanager.entity.SysFunction;
import com.lys.usermanager.service.FunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 权限
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-10-15 16:30
 **/
@RestController
@RequestMapping("function")
public class FunctionController {
    @Autowired
    private FunctionService functionService;

    @PostMapping
    public ResponseEntity saveFunction(SysFunction function) {
        SysFunction saveFunction = functionService.saveFunction(function);
        return ResponseEntity.ok(saveFunction.getfId());
    }


}
