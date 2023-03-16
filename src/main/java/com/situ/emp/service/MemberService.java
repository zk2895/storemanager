package com.situ.emp.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.situ.emp.entity.Member;
import com.situ.emp.vo.ResultVO;

public interface MemberService {

	

	ResultVO save(Member m,HttpSession session);

	ResultVO selectById(int parseInt);

	void delete(int parseInt);

	Long count(String keyword1,String keyword2);

	List<Member> all(int page,int limit,String keyword1,String keyword2);

	Boolean check(String username, String password,HttpSession session);

	

	ResultVO changePwd(String newPwd, HttpSession session);

	ResultVO checkOldPwd(String oldPwd, HttpSession session);

	Member selectByUsername(String username);

}
