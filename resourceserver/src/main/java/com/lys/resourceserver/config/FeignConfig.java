package com.lys.resourceserver.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @description:
 * @author: LiYongSen[825760990@qq.com]
 * @create: 2019-11-15 11:18
 **/
public class FeignConfig implements RequestInterceptor {
    public static final String AUTHORIZATION = "Authorization";

    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            HttpServletRequest request = attributes.getRequest();
            requestTemplate.header(AUTHORIZATION, request.getHeader(AUTHORIZATION));
            return;
        }

    }

}
