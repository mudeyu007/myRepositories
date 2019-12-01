package com.jiaoxf.sorm.pool;
/**
 * 	连接池的类
 * 
 * @author acer
 *
 */

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jiaoxf.sorm.core.DBmanager;

public class DBConnPool {
	/**
	 * 	连接池
	 */
	private List pool;
	
	/**
	 * 	连接池中允许的最大连接个数
	 */
	private static final int Pool_MAX_SIZE =DBmanager.getConfig().getPoolMaxSize() ;
	/**
	 * 	连接池中最小连接个数
	 */
	private static final int Pool_MIN_SIZE =DBmanager.getConfig().getPoolMinSize();
	
	
	
	/**
	 * 	初始化连接池
	 */
	public void initPool() {
		if(pool==null) {
			pool = new ArrayList<Connection>();				
		}
		while(pool.size()<Pool_MIN_SIZE) {
			pool.add(DBmanager.creatConn());
		}
		System.out.println("连接池中的连接数："+pool.size());
	}
	/**
	 * 	从连接池取出一个连接
	 * @return：JDBC连接对象
	 */
	public synchronized Connection getConn() {
		int last_index = pool.size()-1;
		Connection conn= (Connection)pool.get(last_index);		
		pool.remove(last_index);
		
		return conn;		
	}
	/**
	 * 	将连接放回池中
	 * @param conn
	 */
	public synchronized void CloseConn(Connection conn) {
		if(pool.size()>=Pool_MAX_SIZE) {
			try {
				if(conn!=null) {
					conn.close();				
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}else {
			pool.add(conn);		
		}
				
	}
	
	/**
	 * 	构造器
	 */
	public DBConnPool(){
		initPool();
	}
	
	
	
}
