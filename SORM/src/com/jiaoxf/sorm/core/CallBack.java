package com.jiaoxf.sorm.core;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface CallBack {
	/**
	 * 	回调方法，用于实现不同数查询操作。
	 * 		具体实现由不同的query方法以以内部类的形式实现
	 * @param conn：连接对象
	 * @param ps:sql封装对象
	 * @param rs：结果集对象
	 * @return：返回结果集
	 */
	public Object doExcute(Connection conn,PreparedStatement ps,ResultSet rs);
	
	
}
