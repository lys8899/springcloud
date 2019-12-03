package com.lys.resourceserver.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

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

        /*Map<String, Collection<String>> queries = requestTemplate.queries();
        Collection<String> strings = queries.get(AUTHORIZATION);
        strings.forEach(header -> requestTemplate.header(AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOlsidXNlcm1hbmFnZXIiLCJyZXNvdXJzZTIiLCJyZXNvdXJzZTEiXSwidXNlcl9uYW1lIjoibHlzIiwic2NvcGUiOlsiczEiLCJzMiIsIlNDT1BFX1dSSVRFIl0sImV4cCI6MTU3NTI4MDA1MywiYXV0aG9yaXRpZXMiOlsiZk1hcmsiXSwianRpIjoiMWQ0MGFlZDgtY2FlOS00MjM4LTk5YTctMjc3MjQ4NWM3YmY2IiwiY2xpZW50X2lkIjoiY2xpZW50YXBwIn0.D9Dv-QoufJqKPCqA2k0Wq_bstCRkaayWSDKoYSlgtgU"));
*/
    }

}
