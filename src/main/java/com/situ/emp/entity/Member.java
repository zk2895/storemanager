package com.situ.emp.entity;

import java.io.Serializable;
//import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

import lombok.Data;

@Data
public class Member implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String username;
	
	private String password;
	
	private String tel;
	
	private String name;
	
	private LocalDateTime createtime;
	
	private String status;
	
	private String sex;
	
	private Date birthday;
	
	private String avatar;
	
	public String getBirthday1() {
		if(birthday !=null) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(birthday);
		}
		return null;
	}
}
