package com.situ.emp.controller;

import java.io.IOException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import com.alibaba.fastjson2.JSON;
import com.situ.emp.entity.Member;
import com.situ.emp.service.MemberService;
import com.situ.emp.service.impl.MemberServiceImpl;
import com.situ.emp.util.BaseController;
import com.situ.emp.vo.LayuiTableVO;
import com.situ.emp.vo.ResultVO;
import org.springframework.stereotype.Controller;

@Controller
@WebServlet("/newMember")
public class MemberNewController extends BaseController {
	private static final long serialVersionUID = 1L;

	MemberService service = new MemberServiceImpl();

//打开管理员管理的主页面
	public void page(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/member.jsp").forward(request, response);
	}

//list传数据
	public LayuiTableVO list(Integer page, Integer limit, String keyword1,String keyword2) throws ServletException, IOException {
		// 让service 查出当前页的数据
		List<Member> list = service.all(page, limit, keyword1,keyword2);
		// System.out.println(list);
		LayuiTableVO vo = new LayuiTableVO();
		vo.setCode(0);
		vo.setCount(service.count(keyword1,keyword2));
		vo.setData(list);
		return vo;
		// String str = JSON.toJSONString(vo);
		// System.out.println(str);
	}

//根据ID删除一个会员
	public void delete(Integer id) {
		service.delete(id);
	}

//编辑保存会员
	public ResultVO save(Member m, HttpSession session) {

		ResultVO vo = service.save(m, session);
		return vo;
	}

	// 通过id查所有数据
	public ResultVO select(Integer id) {

		return service.selectById(id);
	}
	
	// 修改密码
		public ResultVO changePwd(String newPwd, HttpSession session) {
			return service.changePwd(newPwd, session);
		}

		// 验证旧密码是否一致
		public ResultVO checkOldPwd(String oldPwd, HttpSession session) {
			return service.checkOldPwd(oldPwd, session);
		}
}
