package com.situ.emp.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.situ.emp.dao.MemberDao;
import com.situ.emp.entity.Member;
import com.situ.emp.service.MemberService;
import com.situ.emp.util.DBUtil;
import com.situ.emp.util.StringUtil;
import com.situ.emp.vo.ResultVO;

public class MemberServiceImpl implements MemberService {
      MemberDao dao =new MemberDao();
	
	@Override
	public ResultVO save(Member m,HttpSession session) {
		ResultVO vo = new ResultVO();
		
		if(m.getId()== null) {//新增用户
			
			m.setCreatetime(LocalDateTime.now());
			
			List<Member> member =dao.selectByUsername(m.getUsername());
			if(member.size()>0) {
				vo.setCode(1);
				vo.setMsg("用户名已存在,请更换用户名");
				return vo;
			}
			//md5加密
			m.setPassword(StringUtil.md5(m.getPassword()));
						
			dao.insert(m);
		}else {
			dao.update(m);
		}	
		return vo;
	}
	@Override
	public ResultVO selectById(int id) {
		ResultVO vo = new ResultVO();
		Member m = dao.selectById(id);
		if(m == null) {
			vo.setCode(1);
			vo.setMsg("该用户不存在,请刷新再试");
			
		}else {
			vo.setData(m);
		}
	
		return vo;
	}
	@Override
	public void delete(int id) {
		dao.Delete(id);
		
	}
	@Override
	public Long count(String keyword1,String keyword2) {
		
		return dao.count(keyword1,keyword2);
	}
	@Override
	public List<Member> all(int page, int limit, String keyword1,String keyword2) {
		return dao.all((page-1)*limit,limit,keyword1,keyword2);
	}
	@Override
	
	public Boolean check(String username, String password,HttpSession session) {
		// admin 123456 查数据库
		
		List<Member> m = dao.selectByUsername(username);
		 boolean b = m.size()>0 && m.get(0).getPassword().equals(StringUtil.md5(password));
		if(b) {
			session.setAttribute("currentMember",m.get(0));		
		}
		return b;
		
	}

	@Override
	//修改密码
	public ResultVO changePwd(String newPwd, HttpSession session) {
		
		ResultVO vo = new ResultVO();
		Object obj = session.getAttribute("currentMember");
		Member m=(Member) obj;
		if(newPwd == "") {	
			vo.setCode(1);
			vo.setMsg("输入的密码不能为空!");
			return vo;
		}else {
		dao.changePwd(StringUtil.md5(newPwd),m.getUsername());
		vo.setMsg("修改成功,请重新登录");		
		return vo;
		}
	}
	@Override
	//检查旧密码
	public ResultVO checkOldPwd(String oldPwd, HttpSession session) {
		ResultVO vo = new ResultVO();
		Object obj = session.getAttribute("currentMember");
		Member m=(Member) obj;
		if(! selectByUsername(m.getUsername()).getPassword().equals(StringUtil.md5(oldPwd))) {
			vo.setCode(1);
			vo.setMsg("输入的旧密码有误,请重新输入!");
			return vo;
		}
		return vo;
	}
	// 根据用户名查
		@Override
		public Member selectByUsername(String username) {
			List<Member> l = dao.selectByUsername(username);
			return l.size() > 0 ? l.get(0) : null;
		}		
}
