package com.lys.usermanager;

import com.lys.usermanager.entity.SysDepartment;
import com.lys.usermanager.entity.SysUser;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.context.ApplicationContext;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

@SpringBootTest
class UsermanagerApplicationTests {

    @Autowired
    ApplicationContext applicationContext;




    @Test
    void contextLoads() {

    }

    @Test
    void addUser() {



    }

}
