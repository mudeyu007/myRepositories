package com.jiaoxf.sorm.utils;

import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * 	封装JDBC常用的操作
 * @author acer
 *
 */
public class JDBCUtils {
	/**
	 * 	给sql传递参数
	 * @param ps：预编译sql语句对象
	 * @param params：参数列表
	 */
	public static void handleParams(PreparedStatement ps,Object[] params) {
		if(params!=null) {
			for(int i=0;i<params.length;i++) {
				try {
					ps.setObject(1+1, params[i]);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

		
}







