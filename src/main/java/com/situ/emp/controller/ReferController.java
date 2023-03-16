package com.situ.emp.controller;

import java.io.IOException;

import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.situ.emp.entity.Docket;
import com.situ.emp.service.DocketService;
import com.situ.emp.service.impl.DocketServiceImpl;
import com.situ.emp.util.BaseController;

import com.situ.emp.vo.LayuiTableVO;
import org.springframework.stereotype.Controller;

@Controller
@WebServlet("/refer")
public class ReferController extends BaseController{

	private static final long serialVersionUID = 1L;

	//统计页面
	public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/refer.jsp").forward(request, response);
	}

	public LayuiTableVO selectByYear(String year, String month,String day) {
		DocketService service = new DocketServiceImpl();
		List<Docket> l = null;
		if (year != null && !"".equals(year)) {
			l = service.selectByYear(year);
		}else if(month != null && !"".equals(month)) {
			l = service.selectByMonth(month);
		}else if(day != null && !"".equals(day)) {
			l = service.selectByDay(day);
		}

		LayuiTableVO vo = new LayuiTableVO();
		vo.setCode(0);
		vo.setData(l);
		vo.setCount(10l);// 统计数据库有几条数据
		return vo;
	}

}



