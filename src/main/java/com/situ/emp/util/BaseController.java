package com.situ.emp.util;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson2.JSON;

public class BaseController extends HttpServlet{

	
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// response.setCharacterEncoding("UTF-8");过滤器执行
		//接受一个行为参数
		//根据参数值判断要做哪个事件
		String action = request.getParameter("action");
		//通过反射获取当前类中所有的声明的方法
//		Class c1= Class.forName("");
//		Class c = MemberNewController.class;
//	    Class c2 = 对象.getClass(); 通用
		       
				Method[] methods = this.getClass().getDeclaredMethods();
				//遍历所有方法 比较哪个方法的方法名与action一致,就执行哪个方法
				Method method =null;//等循环完就保存上要执行的方法
				for(Method m : methods) {
					if(m.getName().equals(action)){
						method =m;
					}
				}
				//调用执行方法
				invokeMethod(method,request,response);
	}
	          //执行方法
				private void invokeMethod(Method m,HttpServletRequest request, HttpServletResponse response) {
					
					try {
						
						Parameter[] parameters = m.getParameters();//获取方法中所有的参数
						//int  count=m.getParameterCount();//获取这个m方法有几个参数
						Class<?>[] types = m.getParameterTypes();//获取m方法所有的参数的类型	
						//先创建一个长度对应原方法参数个数的空数组
						Object[] params =new Object[types.length];	
						//循环判断参数，根据类型放入对应类型的参数，打破两个参数的限制
						for(int i=0;i<types.length;i++) {
							Parameter p = parameters[i];//当次循环对应的那个参数
							String pName = p.getName();//获取这个参数的参数名
							
							if(types[i].equals(HttpServletRequest.class)) {
								
								params[i]=request;

							}else if(types[i].equals(HttpServletResponse.class)) {
								params[i]=response;
								
							}else if(types[i].equals(HttpSession.class)) {
								params[i]=request.getSession();
								
							}else {//此条件注意传参名，可使用编程软件设置,注意传入参数的类型兼容，现只支持4种
								if(types[i].equals(Integer.class)) {//如果参数是integer类型，根据参数名获取请求中对应名字的参数值，转Integer后放入参数列表
									String value = request.getParameter(pName);
			                        if (value != null && !"".equals(value) ) {
			                        	params[i] =Integer.parseInt(value);
			                        	
			                        }
								
								}else if(types[i].equals(String.class)) {
									params[i] =request.getParameter(pName);
									
								}else if(types[i].equals(Double.class)) {
									String value = request.getParameter(pName);
									 if (value != null && !"".equals(value) ) {
				                        	params[i] =Double.parseDouble(value);
				                        	
				                        }
									
								}else if(types[i].equals(Date.class)) {
									String value = request.getParameter(pName);
									 if (value != null && !"".equals(value) ) {
										// if(value.length()<6) {//仅仓库使用
										//	value= value.concat("-01-01");
									//	 }else if(value.length()<8&&value.length()>5) {
									//		value= value.concat("-01");
									//	 }
										 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				                        params[i] = sdf.parse(value);	
				                        	
				                        }
									
								}else {//参数类型前面都不是，我认为是一个我们自己定义的类型
									params[i]=makeObject(request,types[i]);

								}
								//添加的类型足够多，支持的controller类型就够强大
							
							}
						}
					
						
						Object result = m.invoke(this,params);
						if(result != null) {//反射执行controller中的方法时这个方法是有返回值的
							response.setContentType("application/json;charset=UTF-8");
						   	response.getWriter().write(JSON.toJSONString(result));
																			
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				//接收请求对象和一个具体的类型
				private Object makeObject(HttpServletRequest request,Class<?> type) {
					try {
					Object o = type.newInstance();
					  Enumeration<String> names =  request.getParameterNames();//获取请求参数的名字
					 while(names.hasMoreElements()) {//循环枚举所有的名字，返回false代表没有下一个了
						 String name = names.nextElement();//获取出当前这次循环的名字
						 String value = request.getParameter(name);//此参数的值
						try { 
						 Field f = type.getDeclaredField(name);
						f.setAccessible(true);
						if(f.getType().equals(String.class)) {
							f.set(o, value);
						}else if(f.getType().equals(Integer.class)) {
							if(value != null && !"".equals(value)) {
								f.set(o, Integer.parseInt(value));
							}

						}else if(f.getType().equals( BigDecimal.class)) {
							if(value != null && !"".equals(value)) {
								f.set(o, new  BigDecimal(value));
							}

						}else if(f.getType().equals(Date.class)) {
							if(value != null && !"".equals(value)) {//或使用if（StringUtil.isNotBlank(value)）,需工具类StringUtil
								SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
								f.set(o,sdf.parseObject(value));
							}

						}
						
						
						}catch(Exception e){
							System.out.println("对应成员变量数目不对，可能是action");//找不到action
						} 						 						 
					 }																								
					return o;
					}catch(Exception e){
						
						return null;						
					}
					
				}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		     doGet(request, response);
	}

}
