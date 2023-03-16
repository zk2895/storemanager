package com.situ.emp.filter;

import java.io.IOException;




import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;

//字符集过滤器
@WebFilter("/*")
public class CharSetFilter implements Filter {

	@Override
	public void doFilter(ServletRequest  request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		//HttpServletRequest req = (HttpServletRequest)request;
		//HttpServletResponse resp = (HttpServletResponse) response;
		request.setCharacterEncoding("UTF-8");
	
//		System.out.println("chaer set filter");
		//这行代码上边的代码表示放行前执行
		chain.doFilter(request, response);//这行代码就是放行
		//在影响之前执行
		
	}

}
