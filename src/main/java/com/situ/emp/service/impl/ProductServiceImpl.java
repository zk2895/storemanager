package com.situ.emp.service.impl;

import java.util.List;


import com.situ.emp.dao.ProductDao;
import com.situ.emp.entity.Product;
import com.situ.emp.service.ProductService;

public class ProductServiceImpl implements ProductService {

	ProductDao dao = new ProductDao();
	@Override
	public List<Product> all(Integer page, Integer limit, String keyword1,String keyword2) {
	
		return dao.all((page-1)*limit,limit,keyword1,keyword2);
	}

	@Override
	public Long count(String keyword1,String keyword2) {
		
		return dao.count(keyword1,keyword2);
	}

	@Override
	public void delete(Integer id) {
		dao.Delete(id);
		
	}

	@Override
	public void save(Product p) {
		if(p.getId()== null) {								
			dao.insert(p);
		}else {
			dao.update(p);
		}			
	}	

	@Override
	public Product selectById(Integer id) {
		
		return dao.selectById(id);
	}

}
