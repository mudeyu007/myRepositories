package com.jiaoxf.sorm.core;
/**
 * 	根据配置信息，负责连接对象的管理(增加连接池管理)
 * @author acer
 *
 */
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com.jiaoxf.sorm.bean.Configuration;
import com.jiaoxf.sorm.pool.DBConnPool;

public class DBmanager {
	private static Configuration conf;
	private static DBConnPool pool;
	
	static {	//静态块：加载配置文件“db.properties”的文本参数到配置对象conf
		Properties pros = new Properties();
		
		try {
			pros.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		conf.setDriver(pros.getProperty("driver"));
		conf.setUrl(pros.getProperty("url"));
		conf.setUser(pros.getProperty("user"));
		conf.setPwd(pros.getProperty("pwd"));
		conf.setUsingDB(pros.getProperty("usingDB"));
		conf.setSrcPath(pros.getProperty("srcPath"));
		conf.setQueryClass(pros.getProperty("QueryClass"));
		conf.setPoolMaxSize(Integer.parseInt(pros.getProperty("poolMaxSize")));
		conf.setPoolMinSize(Integer.parseInt(pros.getProperty("poolMinSize")));
	}
	
	/**
	 * 	创建数据库连接：DBConnPool使用，连接池创建连接对象
	 * @return
	 */
	public static Connection creatConn() {
		try {
			Class.forName(conf.getDriver());
			return DriverManager.getConnection(conf.getUrl(),
					conf.getUser(), conf.getPwd());//直接建立连接，后期增加连接池操作，提高效率！！
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	/**
	 * 	获取连接对象
	 * @return
	 */
	public static Connection getConn() {
		if(pool==null) {
			pool = new DBConnPool();
		}
		return pool.getConn();
	}
	
	/**
	 * 	获取配置信息
	 * @return
	 */
	public static Configuration getConfig() {
		return conf;
	}
	
	/**
	 * 	关闭资源
	 * @param rs ：查询结果集
	 * @param stmt：sql封装对象
	 * @param conn：连接对象
	 */
	public static void close(ResultSet rs,Statement stmt,Connection conn) {
			try {
				if(rs!=null) {
				rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if(stmt!=null) {
					stmt.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
//			try {
//				if(conn!=null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}	
			pool.CloseConn(conn);
	}
	
}
