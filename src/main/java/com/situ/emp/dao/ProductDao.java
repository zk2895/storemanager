package com.situ.emp.dao;

import java.util.List;

import com.situ.emp.entity.Product;
import com.situ.emp.util.DBUtil;

public class ProductDao {

	public List<Product> all(int index, Integer limit, String keyword1,String keyword2) {
		String sql;
	//	if(keyword1 == null || keyword1.equals("")) {
			//sql="select * from product where status=0 limit ?,?";					
		//}else{
			sql ="select * from product where instr(name,'"+keyword1+"')>0 and instr(factory,'"+keyword2+"')>0 and status=0 limit ?,?";		
		//}
			return DBUtil.executeDQL(sql, Product.class,index,limit);
	}

	public Long count(String keyword1,String keyword2) {
		String sql="select count(1) from product where instr(name,'"+keyword1+"')>0 and instr(factory,'"+keyword2+"')>0 and status=0";
//		if(keyword != null || !keyword.equals("")) {
//				sql +=" where instr(name,'"+keyword+"')>0";				
//		}											
		return DBUtil.executeCount(sql);
	}

	public void Delete(Integer id) {
		String sql="update  product set status=1 where id = ?";
		DBUtil.executeDML(sql, id);	
		
	}

	public void insert(Product p) {
		String sql = "insert into product(name,factory,model,spec,status,storenum) values(?,?,?,?,?,?)";
		DBUtil.executeDML(sql, p.getName(),p.getFactory(),p.getModel(),p.getSpec(),p.getStatus(),p.getStorenum());
	}

	public void update(Product p) {
		String sql="update product set name=?,factory=?,model=?,spec=?,status=?,storenum=? where id = ?";
		DBUtil.executeDML(sql,p.getName(),p.getFactory(),p.getModel(),p.getSpec(),p.getStatus(),p.getStorenum(),p.getId());
		
	}

	public Product selectById(Integer id) {
		String sql="select * from product where id = ?";
		List<Product> list = DBUtil.executeDQL(sql, Product.class,id);
		if(list.size()>0) {
			return list.get(0);
		}
		return null;
	}
	
}
