package com.lys.usermanager.config;

import com.lys.usermanager.entity.SysFunction;
import com.lys.usermanager.entity.SysRole;
import com.lys.usermanager.service.FunctionService;
import com.lys.usermanager.service.RoleService;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @description: 初始化缓存
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-10-23 17:10
 **/
@Component
public class InitCacheConfig implements ApplicationListener<ApplicationReadyEvent> {
    private ApplicationContext applicationContext;
    @PersistenceContext
    private EntityManager entityManager;


    private void initRoles() {
        List<SysRole> all = entityManager.createQuery("FROM SysRole").getResultList();
        RoleService bean = applicationContext.getBean(RoleService.class);
        all.forEach(sysRole -> bean.findById(sysRole.getfId()));
    }

    private void initFunctions() {
        List<SysFunction> all = entityManager.createQuery("FROM SysFunction").getResultList();
        FunctionService bean = applicationContext.getBean(FunctionService.class);
        all.forEach(sysRole -> bean.findById(sysRole.getfId()));
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ConfigurableApplicationContext applicationContext = event.getApplicationContext();
        this.applicationContext = applicationContext;
        initRoles();
        initFunctions();
    }



}
