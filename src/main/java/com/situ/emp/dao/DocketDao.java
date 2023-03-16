package com.situ.emp.dao;

import java.util.ArrayList;
import java.util.List;



import com.situ.emp.entity.Docket;
import com.situ.emp.entity.Member;
import com.situ.emp.util.DBUtil;
import com.situ.emp.vo.DocketVO;

public class DocketDao {

	public void insert(Docket d) {
		
		String sql = "insert into docket(productid,num,type,storedate,createtime,createby,company,contacts) values(?,?,?,?,?,?,?,?)";
		DBUtil.executeDML(sql, d.getProductid(),d.getNum(),d.getType(),d.getStoredate(),d.getCreatetime(),d.getCreateby(),d.getCompany(),d.getContacts());
		
	}

	public void update(Docket d) {
		String sql="update docket set productid=?,num=?,type=?,storedate=?,createtime=?,createby=?,company=?,contacts=? where id = ?";
		DBUtil.executeDML(sql,d.getProductid(),d.getNum(),d.getType(),d.getStoredate(),d.getCreatetime(),d.getCreateby(),d.getCompany(),d.getContacts(),d.getId());
		
	}

	public List<DocketVO> select(int index, Integer limit,String keyword) {
//		String sql="select * from docket limit ?,?";
		String sql = "SELECT d.*, p.id AS id1, p.type AS type1, p.name AS name,p.factory AS factory,p.model AS model,\r\n"
				+ "p.spec AS spec,p.storenum AS storenum\r\n"
				+ "FROM docket AS d\r\n"
				+ "LEFT JOIN product AS p\r\n"
				+ "ON p.id = d.productid   where instr(storedate,'"+keyword+"')>0 and status=0 limit ?, ?";
		return DBUtil.executeDQL(sql, DocketVO.class,index,limit);
	}
//统计和模糊查询
	public Long count(String keyword) {
		String sql="select count(1) from docket";
		if(keyword != null || !keyword.equals("")) {
				sql +=" where instr(productid,'"+keyword+"')>0";				
		}
											
		return DBUtil.executeCount(sql);
	}

	public Docket selectById(int id) {
		String sql="select * from docket where id = ?";
		List<Docket> list = DBUtil.executeDQL(sql, Docket.class,id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}

	public void Delete(Integer id) {
		String sql="delete from docket where id = ?";
		DBUtil.executeDML(sql, id);		
	}

	public List<Member> selectByUsername(String username) {
		String sql="select * from member where username = ?";
		return DBUtil.executeDQL(sql, Member.class,username);
	}

	public Object inoutstore(Docket d) {
		//入库为0，出库为1
		String sql = "";
		if(d.getType().contains("1")) {
			sql = "update product set product.storenum=product.storenum-? WHERE product.id=?";
		}else {
		    sql = "update product set product.storenum=product.storenum+? WHERE product.id=?";
		}
		DBUtil.executeDML(sql,d.getNum(),d.getProductid());
		return null;
	}

	public List<Docket> selectByYear(String year) {
		//Integer.valueOf(year);
		List<Docket> l = new ArrayList<>();;
			
		String sql="SELECT SUM(num) AS num FROM docket WHERE INSTR(storedate,?)>0 and type = 1 ";
		String sql2="SELECT SUM(num) AS num FROM docket WHERE INSTR(storedate,?)>0 and type = 0";
		l.addAll(DBUtil.executeDQL(sql, Docket.class,year));
		l.addAll(DBUtil.executeDQL(sql2, Docket.class,year));
		System.out.println(l);
		return l;
	}

	public List<Docket> selectByMonth(String month) {
		List<Docket> l = new ArrayList<>();;
		
		String sql="SELECT SUM(num) AS num FROM docket WHERE INSTR(storedate,?)>0 and type = 1 ";
		String sql2="SELECT SUM(num) AS num FROM docket WHERE INSTR(storedate,?)>0 and type = 0";
		l.addAll(DBUtil.executeDQL(sql, Docket.class,month));
		l.addAll(DBUtil.executeDQL(sql2, Docket.class,month));
		System.out.println(l);
		return l;
	}

	public List<Docket> selectByDay(String day) {
       List<Docket> l = new ArrayList<>();;
		
		String sql="SELECT SUM(num) AS num FROM docket WHERE INSTR(storedate,?)>0 and type = 1 ";
		String sql2="SELECT SUM(num) AS num FROM docket WHERE INSTR(storedate,?)>0 and type = 0";
		l.addAll(DBUtil.executeDQL(sql, Docket.class,day));
		l.addAll(DBUtil.executeDQL(sql2, Docket.class,day));
		System.out.println(l);
		return l;
	}

}
