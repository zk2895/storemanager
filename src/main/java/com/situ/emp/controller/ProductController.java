package com.situ.emp.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.situ.emp.entity.Product;
import com.situ.emp.service.ProductService;
import com.situ.emp.service.impl.ProductServiceImpl;
import com.situ.emp.util.BaseController;
import com.situ.emp.vo.LayuiTableVO;
import org.springframework.stereotype.Controller;

@Controller
@WebServlet("/product")
public class ProductController extends BaseController {

	private static final long serialVersionUID = 1L;

      ProductService  service = new ProductServiceImpl();
		
	//打开商品管理的主页面
		public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			request.getRequestDispatcher("/WEB-INF/product.jsp").forward(request, response);
		}
		
		//list传数据
		public LayuiTableVO list(Integer page,Integer limit,String keyword1,String keyword2) throws ServletException, IOException {				 
			// 让service 查出当前页的数据		
			List<Product> list = service.all(page,limit,keyword1,keyword2);
			//System.out.println(list);		
			LayuiTableVO vo = new LayuiTableVO();
			vo.setCode(0);
			vo.setCount(service.count(keyword1,keyword2));
			vo.setData(list);
			return vo;
			//String str = JSON.toJSONString(vo);
			//System.out.println(str);				
		}
	//根据ID逻辑删除一个商品
		public void delete(Integer id) {				
			service.delete(id);	
		}
	//编辑保存商品
	    public void save(Product p) {					
	  	     service.save(p);				
	  		 }  					
	 //通过id查所有数据
	  	public Product select(Integer id)   { 					  		 
		   return  service.selectById(id);		   	   		
		}
	}

    


