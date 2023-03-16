package com.situ.emp.util;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtil {
	/**
	 * 下划线命名转驼峰命名
	 *
	 * @param name
	 * @return
	 */
	public static String underLineToHump(String name) {
		if (name == null || name.indexOf("_") < 0) {
			return name;
		}
		String[] arr = name.split("_");
		StringBuilder sb = new StringBuilder(arr[0]);
		for (int j = 1; j < arr.length; j++) {
			sb.append(arr[j].substring(0, 1).toUpperCase());
			sb.append(arr[j].substring(1));
		}
		return sb.toString();
	}
	
	public static boolean isNotBlank(String str) {
		return str !=null && !"".equals(str);
	}
	
	//md5加密
	public static String md5(String str) {		
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			return new BigInteger(1,md.digest()).toString(16);
			
		}catch(NoSuchAlgorithmException | UnsupportedEncodingException ignored) {
			return null;
		}		
	}
}
