package com.lhc.filter;

import com.alibaba.fastjson.JSON;
import com.lhc.pojo.Result;
import com.lhc.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        //1.获取请求url
        String url = req.getRequestURL().toString();
        log.info("请求的url:{}", url);

        //2.判断是否login,如果是，则放行
        if(url.contains("login")){
            log.info("登录操作，放行...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        //3.获取请求头中的token
        String jwt = req.getHeader("token");

        //4.判断token是否存在，如果不存在，则返回error
        if(!StringUtils.hasLength(jwt)){
            log.info("请求头token为空，返回未登录的信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSON.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //5.解析token，如果解析失败，则返回error
        try{
            JwtUtils.parseJWT(jwt);
        }catch (Exception e){
            e.printStackTrace();
            log.info("解析令牌失败，返回未登录错误信息");
            Result error = Result.error("NOT_LOGIN");
            String notLogin = JSON.toJSONString(error);
            resp.getWriter().write(notLogin);
            return;
        }

        //6.放行
        log.info("令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
