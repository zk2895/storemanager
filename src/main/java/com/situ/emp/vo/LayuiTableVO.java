package com.situ.emp.vo;

import java.util.List;

import lombok.Data;
@Data
//定义layui对于table模块默认的数据库解析格式
public class LayuiTableVO {
	//如果code值是0表示这次查询成功了,非0不成功
	private int code;
	//如果code不为0,msg指定失败原因
	private String msg;
    //不分页的前提下，能查到多少数据
	private Long count;
	//当前页的数据
	private List<?> data;
	// /List<Object>
}
