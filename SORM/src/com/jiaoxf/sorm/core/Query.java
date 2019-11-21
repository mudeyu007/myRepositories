package com.jiaoxf.sorm.core;

import java.util.List;

/**
 * 	负责查询：对外提供服务的核心类
 * 
 * @author acer
 *
 */

public interface Query {
	/**
	 * 	直接执行DML语句。
	 * 
	 * @param sql ：要执行的sql语句
	 * @param params ：传入sql的参数
	 * @return int ：成功执行的sql后，受影响记录的行数。
	 */
	public int excuteDML(String sql,Object[] params); 
	
	/**
	 * 	将给定的对象存储到数据中
	 * @param obj:要存储的对象
	 */
	public void insert(Object obj);
	
	/**
	 * 	删除clazz对应记录中，指定id的记录。
	 * 
	 * @param clazz:要删除记录所在表对应的Class对象
	 * @param id:要删除记录的id（主键值）
	 */
	public void delete(Class clazz,Object id);//delete * from user where id=?
	/**
	 * 	删除指定对应在数据库中对应的记录
	 * 		对象所在的类对应到表，对象属性值对应到主键的值
	 * 
	 * @param obj:要删除的指定对象
	 */
	public void delete(Object obj);
	
	/**
	 * 	更新对象对应的记录，且只更新指定的字段值
	 * 
	 * @param obj：要更新记录对应的对象
	 * @param fildNames ：要更新的字段对应的属性
	 * @return int:更新执行后影响的记录行数
	 */
	public void update(Object obj,String[] fieldNames);//update user set uname=? id=?
	
	/**
	 * 	查询多行数据，查询结果封装到Class对象指定的类中。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public List queryRow(String sql,Class clazz,Object[] params);
	/**
	 * 	查询一行数据，查询结果封装到Class对象指定的类中。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[] params);
	/**
	 * 	查询一行一列数据。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public Object queryValue(String sql,Object[] params);
	/**
	 * 	查询一个数字(一行一列数据)。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public Number queryNumber(String sql,Object[] params);
		
}
