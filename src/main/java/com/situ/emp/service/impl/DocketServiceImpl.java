package com.situ.emp.service.impl;


import java.time.LocalDateTime;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.situ.emp.dao.DocketDao;
import com.situ.emp.entity.Docket;
import com.situ.emp.entity.Member;
import com.situ.emp.service.DocketService;
import com.situ.emp.service.MemberService;
import com.situ.emp.vo.DocketVO;
import com.situ.emp.vo.LayuiTableVO;

public class DocketServiceImpl implements DocketService {
	MemberService  memberService = new MemberServiceImpl();

	DocketDao dao = new DocketDao();
	@Override                   //HttpSession session)
	public void save(Docket d,HttpSession session) {
		if(d.getId()== null) {	
		   d.setCreatetime(LocalDateTime.now());
			//通过session获取是谁登录的系统
			//d.setCreateby(null);		
		
		Object obj = session.getAttribute("currentMember");
	    Member member =(Member) obj;
	    d.setCreateby(member.getId());
	  
			dao.insert(d);
		}else {
			dao.update(d);
		}					
      }
//	}
	@Override
	public LayuiTableVO list(Integer page, Integer limit,String keyword) {
		
		LayuiTableVO vo = new LayuiTableVO();
        vo.setCode(0);
		vo.setCount(dao.count(keyword));
		vo.setData(dao.select((page-1)*limit,limit,keyword));
		
		return vo;
	}
	@Override
	public Docket selectById(Integer id) {
		return dao.selectById(id);
	}
	@Override
	public void delete(Integer id) {
		dao.Delete(id);
		
	}

	@Override
	public Long count(String keyword) {
		
		return dao.count(keyword);
	}
	@Override
	public void inoutstore(Docket d) {
		
		dao.inoutstore(d);
	}
	@Override
	public List<Docket> selectByYear(String year) {
		
		return dao.selectByYear(year);
	}
	@Override
	public List<Docket> selectByMonth(String month) {
		
		return dao.selectByMonth(month);
	}
	@Override
	public List<Docket> selectByDay(String day) {
		
		return dao.selectByDay(day);
	}
	
}