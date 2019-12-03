package com.lys.usermanager.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @description: aop日志处理
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2018-09-08 20:31
 **/
@Aspect
@Component
public class AopLogConfig {

    @Pointcut("execution(public * com.lys.usermanager.service.impl.*.save*(..))")
    public void savaLog() {
    }

    @Pointcut("execution(public * com.lys.usermanager.service.impl.*.delete*(..))")
    public void deleteLog() {
    }

    @Pointcut("execution(public * com.lys.usermanager.service.impl.*.update*(..))")
    public void updateLog() {
    }

    /**
     * 业务层保存,删除方法日志
     *
     * @Param: [joinPoint]
     * @return: void
     * @Date: 2018/9/8
     */
    @AfterReturning("savaLog() || deleteLog()")
    public void saveOrDeleteAfterReturning(JoinPoint joinPoint) {
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        String collectArgs = Arrays.asList(args).stream().map(o -> o.toString())
                .collect(Collectors.joining(",", "[", "]"));
        String methodName = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(target.getClass());
        if (logger.isWarnEnabled()) {
            logger.warn("exq method : {} paramer : {}", methodName, collectArgs);
        }

    }


    /**
     * 业务层修改方法日志
     *
     * @Param: [joinPoint]
     * @return: void
     * @Date: 2018/9/8
     */
    @AfterReturning(value = "updateLog()", returning = "rvt")
    public void updateReturning(JoinPoint joinPoint, Object rvt) {
        Object target = joinPoint.getTarget();
        Object[] args = joinPoint.getArgs();
        String collectArgs = Arrays.asList(args).stream().map(o -> o.toString())
                .collect(Collectors.joining(",", "[", "]"));
        String returnArgs = rvt.toString();
        String methodName = joinPoint.getSignature().getName();
        Logger logger = LoggerFactory.getLogger(target.getClass());
        if (logger.isWarnEnabled()) {
            logger.warn("exq method : {} paramer : {} return :{}", methodName, collectArgs, returnArgs);
        }

    }


}
