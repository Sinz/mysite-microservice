package com.master.manager.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import common.util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Classname ManageFilter
 * @Description 后台网关过滤器
 * @Date 2021/5/12 1:39
 * @Created by Zhao.J
 */
@Component
public class ManageFilter extends ZuulFilter {

    @Autowired
    private JwtUtil jwtUtil;


    @Override
    public String filterType(){
        /*  pre ：可以在请求被路由之前调用
         *  route ：在路由请求时候被调用
         *  post ：在route和error过滤器之后被调用
         *  error ：处理请求时发生错误时被调用
         * @Date 2021/5/12 3:31
         * */
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

    /**
     * @Author Zhao.J
     * @Description return null 表示继续执行；
     *  setsendZuulResponse(false) 表示不在继续执行
     *  需要对头信息进行处理 与 转发
     * @Date 3:33 2021/5/12
     * @return : java.lang.Object
     **/
    @Override
    public Object run() throws ZuulException {
        System.out.println("zuul过滤器...");

        RequestContext requestContext = RequestContext.getCurrentContext(); //获取header
        HttpServletRequest request = requestContext.getRequest();

        // 转发放行
        if(request.getMethod().equals("OPTIONS")){
            return null;
        }

        // 登录放行
        String url=request.getRequestURL().toString();
        if(url.indexOf("/admin/login")>0){
            return null;
        }

        String authHeader = request.getHeader("Authorization");//获取头信息
        if(authHeader !=null && authHeader.startsWith("Bearer ")){
            final String token = authHeader.substring(7); // The part after "Bearer "

            try{
                Claims claims = jwtUtil.parseJWT(token);
                if (claims != null) {
                    if("admin".equals(claims.get("roles"))){
                        //如果是管理员
                        requestContext.addZuulRequestHeader("Authorization",authHeader);
                        System.out.println("token 验证通过，添加了头信息"+authHeader);
                        return null;
                    }
                }
            }catch (Exception e){
                requestContext.setSendZuulResponse(false);
            }

        }
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(403);//http状态码
        requestContext.setResponseBody("权限不足");
        requestContext.getResponse().setContentType("text/html;charset=utf-8");
        return null;
    }

}
