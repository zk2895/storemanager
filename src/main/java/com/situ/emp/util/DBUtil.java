package com.situ.emp.util;



import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DBUtil {
//连接数据库
	private static DataSource dataSource;
	static {
		Properties ps = new Properties();
		try {
			ps.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("db.properties"));
			dataSource = DruidDataSourceFactory.createDataSource(ps);
		} catch (Exception e) {
			System.out.println("初始化数据库失败");
			e.printStackTrace();
		}
	}

	// DML方法
	public static int executeDML(String sql, Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行dml失败");
		} finally {
			DBUtil.close(ps, conn);
		}
		return 0;
	}

//DQL方法
	public static <T> List<T> executeDQL(String sql, Class<T> c, Object... params) {

		List<T> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement(sql);

			if (params != null) {
				for (int i = 0; i < params.length; i++) {
					ps.setObject(i + 1, params[i]);
				}
			}

			rs = ps.executeQuery();

			//rs.getMetaData(); 原数据

			ResultSetMetaData rsmd = rs.getMetaData();

			while (rs.next()) {
				T obj = c.newInstance();
				for (int i = 1; i <= rsmd.getColumnCount(); i++) {
					Object value = rs.getObject(i);
					String label = rsmd.getColumnLabel(i);
					// 那一次循环第i列的值
					rsmd.getColumnLabel(i);
					try {
						Field field = c.getDeclaredField(label);// 找到跟表头名重名的成员变量;
						field.setAccessible(true);
						field.set(obj, value);
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println(label + ":这一列没找到");
					}
				}

				/*
				 * Teacher t = new Teacher(); t.setId(rs.getInt("id"));
				 * t.setName(rs.getString("name")); t.setCash(rs.getInt("cash"));
				 */
				list.add(obj);

			}
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			DBUtil.close(rs, ps, conn);
		}
		return list;
	}
	
	public static Long executeCount(String sql,Object... params) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps=conn.prepareStatement(sql);
			if(params !=null) {
				for(int i = 0;i<params.length;i++) {
					ps.setObject(i+1,params[i]);
				}
			}
			rs = ps.executeQuery();
			rs.next();//指向结果集第一行
			return rs.getLong(1);
		}catch (Exception e) {
			e.printStackTrace();
			return 0L;
		}finally {
			DBUtil.close(rs, ps, conn);
		}		
	}

	public static Connection getConnection() throws SQLException {

		return dataSource.getConnection();
	}

	public static void close(PreparedStatement ps, Connection conn) {
		  close(null, ps, conn);
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection conn) {
		if (rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
