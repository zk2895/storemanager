package com.situ.emp.controller;

import java.io.IOException;

import java.util.Enumeration;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionContext;

import com.situ.emp.service.DocketService;
import com.situ.emp.service.MemberService;
import com.situ.emp.service.impl.DocketServiceImpl;
import com.situ.emp.service.impl.MemberServiceImpl;
import org.springframework.stereotype.Controller;

@Controller
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   //get的方式表示需要返回登录页面
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	
	
	}
//post方式表示要验证用户名密码
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//获取session里的验证码captcha
		String captcha = request.getParameter("captcha");
		
		if (!((String)(request.getSession().getAttribute("code"))).equals(captcha)){
			request.setAttribute("msg", "验证码错误，请重新输入!");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);   	   
	       }else {
	    	   	       		
		//派service验证正确
	    MemberService service = new MemberServiceImpl();
		Boolean right = service.check(username,password,request.getSession());
		if(right) {
			//保存一下这个用户登录成功的状态
			//request.getSession().setAttribute("currentMember",username);
			
			response.sendRedirect("/index");
			
		}else {
			//转发带数据
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
			//response.sendRedirect("/login");
		}
		}
	}

}
