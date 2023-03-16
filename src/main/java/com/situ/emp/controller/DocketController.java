package com.situ.emp.controller;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.situ.emp.entity.Docket;

import com.situ.emp.service.DocketService;
import com.situ.emp.service.impl.DocketServiceImpl;
import com.situ.emp.util.BaseController;
import com.situ.emp.vo.LayuiTableVO;
import org.springframework.stereotype.Controller;

@Controller
@WebServlet("/docket")
public class DocketController extends BaseController{

	private static final long serialVersionUID = 1L;
	
	DocketService service = new DocketServiceImpl();
	Docket d = new Docket();
     
	//打开界面
	public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/WEB-INF/docket.jsp").forward(request, response);
	}
	
	//页面数据查list
	public LayuiTableVO list(Integer page,Integer limit,String keyword) {
		System.out.println(service.list(page,limit,keyword));
		return service.list(page,limit,keyword);	
	}
	//保存商品信息
	public void save(Docket d,HttpSession session) {
		service.save(d,session);
		service.inoutstore(d);
	}
	//根据ID删除一个商品
		public void delete(Integer id) {				
			service.delete(id);	
		}
		//通过id查所有数据
	  	public Docket select(Integer id)   { 				
	  		 
		   return  service.selectById(id);
			   	   		
		}
	
}
