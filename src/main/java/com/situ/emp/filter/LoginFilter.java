package com.situ.emp.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//验证是否登录的过滤器
@WebFilter("/*")
public class LoginFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		
		HttpServletRequest req = (HttpServletRequest)request;
		String uri =req.getRequestURI();//当次请求
		
		//获取登录页面和提交数据校验是否能登录的请求 直接放行
		//获取webapp 中静态资源的请求 直接放行
		
		Boolean isLogin = req.getSession().getAttribute("currentMember")!=null;
		
         if(isLogin || uri.equals("/login") || uri.startsWith("/lib/")||uri.startsWith("/js/")|| uri.startsWith("/css/") 
       		  || uri.startsWith("/images/") || uri.startsWith("/favicon.ico") || uri.startsWith("/image")){//如果请求地址是/login直接放行,不判断是否登录
        	  chain.doFilter(request, response);
          
          } else{
		     
        	  HttpServletResponse resp = (HttpServletResponse) response;
        	  resp.sendRedirect("/login");
        	  
          }
          
	}

}
