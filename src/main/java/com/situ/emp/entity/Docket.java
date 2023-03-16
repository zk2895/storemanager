package com.situ.emp.entity;

import java.math.BigDecimal;

import java.time.LocalDateTime;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.Data;



@Data
public class Docket {
	
	private Integer id;
	
	private Integer productid;
	
	private BigDecimal num;
	
	private String type;
	@JSONField(format = "yyyy-MM-dd")
	private Date storedate;
	
	//LocalDataTime
	//LocalTime
	//LocalDate
	private LocalDateTime createtime;
	
	private Integer createby;
	
	private String company;
	
	private String contacts;

}
