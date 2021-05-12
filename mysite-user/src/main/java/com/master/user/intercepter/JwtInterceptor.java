package com.master.user.intercepter;

import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Classname JwtInterceptor
 * @Description TODO
 * @Date 2021/5/9 12:36
 * @Created by Zhao.J
 */

@Component
public class JwtInterceptor implements HandlerInterceptor{


    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("经过了拦截器...");

        String authHeader = request.getHeader("Authorization");//获取头信息
        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            final String token = authHeader.substring(7); // The part after "Bearer "
            Claims claims = jwtUtil.parseJWT(token);
            if (claims != null) {
                if("admin".equals(claims.get("roles"))){
                    //如果是管理员
                    request.setAttribute("admin_claims", claims);
                }
                if("user".equals(claims.get("roles"))){
                    //如果是用户
                    request.setAttribute("user_claims", claims);
                }
            }
        }

        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
    }
}
