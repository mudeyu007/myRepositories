package com.jiaoxf.sorm.core;
/**
 * 	负责java数据类型与数据库数据类型之间的相互转化
 * @author acer
 *
 */
public interface TypeConverter {
	/**
	 * 	数据库类型转为java数据类型
	 * @param ColumnType
	 * @return
	 */
	public String databaseType2JavaType(String ColumnType);
	
	/**
	 * java数据类型转为数据库类型
	 * @param JavaType
	 * @return
	 */
	public String JavaType2DatabaseType(String JavaType);
	
}
