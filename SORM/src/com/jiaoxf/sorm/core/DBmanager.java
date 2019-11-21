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

public class DBmanager {
	private static Configuration conf;
	
	static {//静态代码块，只在类加载时执行一次
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
	}
	
	//建立数据库连接，static 只建立一次
	public static Connection getConn() {
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
	
	//关闭资源,
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
			try {
				if(conn!=null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
	}
	/**
	 * 	获取配置信息
	 * @return
	 */
	public static Configuration getConfig() {
		return conf;
	}
	
}
