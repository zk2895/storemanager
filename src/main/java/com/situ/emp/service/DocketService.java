package com.situ.emp.service;


import java.util.List;

import javax.servlet.http.HttpSession;

import com.situ.emp.entity.Docket;
import com.situ.emp.vo.DocketVO;
import com.situ.emp.vo.LayuiTableVO;

public interface DocketService {

	void save(Docket d,HttpSession session);

	LayuiTableVO list(Integer page, Integer limit,String keyword);

	Docket selectById(Integer id);

	void delete(Integer id);
	

	Long count(String keyword);

	void inoutstore(Docket d);

	 List<Docket> selectByYear(String year);
		
	 List<Docket> selectByMonth(String month);

	 List<Docket> selectByDay(String day);
}
