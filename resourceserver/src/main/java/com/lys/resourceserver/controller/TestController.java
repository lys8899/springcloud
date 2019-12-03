package com.lys.resourceserver.controller;

import com.lys.resourceserver.feign.UserManagerClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.HeaderParam;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @description: 1111111
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-06 15:34
 **/
@RestController
@RequestMapping("/ttttt")
public class TestController {
    Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    UserManagerClient userManagerClient;

    @Autowired
    ApplicationContext context;


    @PreAuthorize("hasAuthority('fMark')")
    @GetMapping
    public Map test1111(@RequestHeader("Authorization") String authorization2) {
        long start = System.currentTimeMillis();
        Map responseEntity5 = userManagerClient.getUsers(1, 10, "", "all");
        Map responseEntityt = userManagerClient.getUser("01", "all");
        Map responseEntityr = userManagerClient.getUser("01", "simple");
        String responseEntity4 = userManagerClient.deleteUsers("11111,22222");
        String responseEntity6 = userManagerClient.deleteUsers("11111,22222");
        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.printf(String.valueOf(time));
        return responseEntityr;
    }


    /*@PreAuthorize("hasAuthority('fMark')")
    @GetMapping("/t1")
    public Long test1(@RequestHeader("Authorization") String authorization2) throws ExecutionException, InterruptedException {
        TestController bean = context.getBean(TestController.class);
        long start = System.currentTimeMillis();

        Future<Map<String, Object>> mapFuture = bean.userManagerClientgetUser(authorization2);
        Future<Map<String, Object>> mapFuture3 = bean.userManagerClientgetUsers(authorization2);
        Future<String> mapFuture2 = bean.userManagerdeleteUsers(authorization2);

        Map<String, Object> stringObjectMap = mapFuture.get();
        Map<String, Object> stringObjectMap2 = mapFuture3.get();
        String stringObjectMap3 = mapFuture2.get();

        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.printf(String.valueOf(time));
        return time;
    }*/

   /* *//**
     * 同步
     *
     * @return
     *//*
    @PreAuthorize("hasAuthority('fMark')")
    @GetMapping("/t2")
    public Long test2(@RequestHeader("Authorization") String authorization2) throws ExecutionException, InterruptedException {
        long start = System.currentTimeMillis();

        Future<Map<String, Object>> mapFuture = userManagerClientgetUser(authorization2);
        Future<Map<String, Object>> mapFuture3 = userManagerClientgetUsers(authorization2);
        Future<String> mapFuture2 = userManagerdeleteUsers(authorization2);

        Map<String, Object> stringObjectMap = mapFuture.get();
        Map<String, Object> stringObjectMap2 = mapFuture3.get();
        String stringObjectMap3 = mapFuture2.get();


        long end = System.currentTimeMillis();
        long time = end - start;
        System.out.printf(String.valueOf(time));
        return time;
    }


    @Async
    protected Future<Map<String, Object>> userManagerClientgetUser(String authorization2) {
        Map responseEntity5 = userManagerClient.getUsers(1, 10, "", "all", authorization2);
        for (int i = 0; i < 10; i++) {
            logger.info("userManagerClientgetUser");
        }

        return new AsyncResult<>(responseEntity5);
    }

    @Async
    protected Future<Map<String, Object>> userManagerClientgetUsers(String authorization2) {
        Map responseEntityt = userManagerClient.getUser("01", "all", authorization2);
        for (int i = 0; i < 10; i++) {
            logger.info("userManagerClientgetUsers");
        }

        return new AsyncResult<>(responseEntityt);
    }

    @Async
    protected Future<String> userManagerdeleteUsers(String authorization2) {
        String responseEntity6 = userManagerClient.deleteUsers("11111,22222", authorization2);
        for (int i = 0; i < 10; i++) {
            logger.info("userManagerdeleteUsers");
        }
        return new AsyncResult<>(responseEntity6);
    }*/

}
