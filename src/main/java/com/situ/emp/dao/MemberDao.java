package com.situ.emp.dao;

import java.util.List;

import com.situ.emp.entity.Member;
import com.situ.emp.util.DBUtil;
import com.situ.emp.vo.ResultVO;

public class MemberDao {

	//查询和关键字查询
	public List<Member> all(int index,int limit,String keyword1,String keyword2) {		
		
//		if(keyword1 == null || keyword1.equals("")) {
//			sql="select * from member limit ?,?";	
//			
//		}if(keyword1 != null &&!keyword1.equals("")  && keyword2 == null || keyword2.equals("")) {
//			sql ="select * from member where instr(username,'"+keyword1+"')>0 limit ?,?";
//			return DBUtil.executeDQL(sql, Member.class,index,limit);
//		}if(keyword1 == null || keyword1.equals("") && keyword2 != null && !keyword2.equals("")) {
//			sql ="select * from member where instr(name,'"+keyword2+"')>0 limit ?,?";
//			return DBUtil.executeDQL(sql, Member.class,index,limit);
//		}		
		
		String sql ="select * from member where instr(username,'"+keyword1+"')>0 and instr(name,'"+keyword2+"') >0 limit ?,?";				
		
			return DBUtil.executeDQL(sql, Member.class,index,limit);
	}
//增加插入
	public void insert(Member m) {
		String sql = "insert into member(username,password,name,birthday,tel,sex,createtime) values(?,?,?,?,?,?,?)";
		DBUtil.executeDML(sql, m.getUsername(),m.getPassword(),m.getName(),m.getBirthday(),m.getTel(),m.getSex(),m.getCreatetime());
		
	}
//通过id查所有成员
	public Member selectById(int id) {
		String sql="select * from member where id = ?";
		List<Member> list = DBUtil.executeDQL(sql, Member.class,id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
//修改成员信息
	public void update(Member m) {
		String sql="update member set username=?,password=?,name=?,birthday=?,tel=?,sex=? where id = ?";
		DBUtil.executeDML(sql,m.getUsername(),m.getPassword(),m.getName(),m.getBirthday(),m.getTel(),m.getSex(),m.getId());
	}
//通过id删除
	public void Delete(int id) {
		String sql="delete from member where id = ?";
		DBUtil.executeDML(sql, id);	
		
	}
//统计数据条数
	public Long count(String keyword1,String keyword2) {
		String sql="select count(1) from member where instr(username,'"+keyword1+"')>0 and instr(name,'"+keyword2+"') >0";
//		if(keyword != null || !keyword.equals("")) {
//				sql +=" where instr(username,'"+keyword+"')>0";				
//		}
											
		return DBUtil.executeCount(sql);
	}
//通过用户名查所有
	public List<Member> selectByUsername(String username) {
		String sql="select * from member where username = ?";
		return DBUtil.executeDQL(sql, Member.class,username);
	}
//修改密码
	public void changePwd(String password, String username) {
		String sql ="update member set password=? where username=?";
		DBUtil.executeDML(sql,password,username);		
	}
	
	public Object selectByUserName(String username) {
		String sql="select * from member where username = ?";
		return DBUtil.executeDQL(sql, Member.class,username);
	}
		

}
