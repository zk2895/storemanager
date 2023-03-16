package com.situ.emp.vo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import com.alibaba.fastjson2.annotation.JSONField;

import lombok.Data;

@Data
public class DocketVO {
	private Integer id;

	private Integer id1;
	
	private String name;

	private String factory;

	private String model;

	private String spec;

	private String status;

	private Integer storenum;

	private Integer productid;

	private BigDecimal num;

	private String type;

	private String type1;
	//@JSONField(format = "yyyy-MM-dd")
	private Date storedate;

	// LocalDataTime
	// LocalTime
	// LocalDate
	private LocalDateTime createtime;

	private Integer createby;

	private String company;

	private String contacts;

}
