package com.situ.emp.service;

import java.util.List;


import com.situ.emp.entity.Product;

public interface ProductService {

	List<Product> all(Integer page, Integer limit, String keyword1,String keyword2);

	Long count(String keyword1,String keyword2);

	void delete(Integer id);
	
	void save(Product p);

	Product selectById(Integer id);

	

}
