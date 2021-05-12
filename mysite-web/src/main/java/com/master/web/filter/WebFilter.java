package com.master.web.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname WebFilter
 * @Description TODO
 * @Date 2021/5/12 1:39
 * @Created by Zhao.J
 */
@Component
public class WebFilter extends ZuulFilter {

    @Override
    public String filterType(){
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;// 优先级为0，数字越大，优先级越低
    }

    @Override
    public boolean shouldFilter() {
        return true;// 是否执行该过滤器，此处为true，说明需要过滤
    }

    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器...");

        //得到request上下文
        RequestContext requestContext = RequestContext.getCurrentContext();
        //获取request域
        HttpServletRequest request = requestContext.getRequest();
        //获取头信息
        String authorization = request.getHeader("Authorization");

        if(authorization!=null){
            requestContext.addZuulRequestHeader("Authorization",authorization);
        }
        return null;

    }

}
