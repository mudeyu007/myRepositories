package com.jiaoxf.sorm.core;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jiaoxf.sorm.bean.ColumnInfo;
import com.jiaoxf.sorm.bean.TableInfo;
import com.jiaoxf.sorm.utils.JDBCUtils;
import com.jiaoxf.sorm.utils.ReflectUtils;

/**
 * 	负责查询：对外提供服务的核心类
 * 
 * @author acer
 *
 */

public abstract class Query implements Cloneable{	
	
	/**
	 * 	使用模板方法模式将JDBC(查询)操作封装为模板，为不同查询类型提供灵活、方便的重用。
	 * @param sql：要执行的sql语句
	 * @param params：sql参数
	 * @param clazz：查询table对应的类对象
	 * @param back：回调接口（接口方法提供现具体的查询操作，由具体的query以内部类方法实现）。
	 * @return：返回查询结果
	 */
	public Object excuteQueryTemplate(String sql,Object[] params,Class clazz,CallBack back) {
		Connection conn = DBmanager.getConn();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//给sql传参
			JDBCUtils.handleParams(ps, params);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			return back.doExcute(conn, ps, rs);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			DBmanager.close(rs, ps, conn);
		}	
		
	}
	
	
	/**
	 * 	直接执行DML语句。
	 * 
	 * @param sql ：要执行的sql语句
	 * @param params ：传入sql的参数
	 * @return int ：成功执行的sql后，受影响记录的行数。
	 */
 	public int excuteDML(String sql,Object[] params) {
		Connection conn = DBmanager.getConn();
		int count = 0;
		PreparedStatement ps = null;
		try {
			ps = (PreparedStatement) conn.prepareStatement(sql);
			
			//给sql传参
			JDBCUtils.handleParams(ps, params);
			
			count = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBmanager.close(null, ps, conn);
		}
		return count;
	}
	
	/**
	 * 	将给定的对象存储到数据中
	 * @param obj:要存储的对象
	 */
	public void insert(Object obj) {
		//insert into table(field1,field2,..) values(?,?,..);
		Class c = obj.getClass();
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
				
		StringBuilder sql = new StringBuilder("insert into "+tableInfo.getTableName()+"(");
				
		//反射机制获得对象的属性和值
		Field[] fs = c.getDeclaredFields();
		int countFields = 0;
		List<Object> params = new ArrayList<Object>();
		for(Field f:fs) {
			countFields++;
			String fieldName = f.getName();
			Object fildValue = ReflectUtils.invokeGet(fieldName, obj);
			//填充参数列表
			params.add(fildValue);
			//拼接sql
			sql.append(fieldName+",");			
		}
				
		sql.setCharAt(sql.length()-1, ')');
		sql.append(" values(");		
		for(int i=0;i<countFields;i++) {
			sql.append("?,");
		}
		sql.setCharAt(sql.length()-1, ')');
		sql.append(";");
				
		excuteDML(sql.toString(), params.toArray());

	}
	
	/**
	 * 	删除clazz对应记录中，指定id的记录。
	 * 
	 * @param clazz:要删除记录所在表对应的Class对象
	 * @param id:要删除记录的id（主键值）
	 */
	public void delete(Class clazz,Object id) {
		//Emp.class,2--> delete from emp where id =2;
				//通过Class查找TableInfo
				TableInfo tableinfo = TableContext.poClassTableMap.get(clazz);
				//获取主键
				ColumnInfo onlyPriKey = tableinfo.getOnlyPriKey();
				
				String sql = "delete from "+tableinfo.getTableName()+" where "
						+onlyPriKey.getColumnName()+"=?;";
				
				excuteDML(sql, new Object[] {id});
	}
	/**
	 * 	删除指定对应在数据库中对应的记录
	 * 		对象所在的类对应到表，对象属性值对应到主键的值
	 * 
	 * @param obj:要删除的指定对象
	 */
	public void delete(Object obj) {
		Class c = obj.getClass();
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPriKey = tableinfo.getOnlyPriKey();
		
		//通过反射机制，调用属性对应的get方法
		Object priKeyValue = ReflectUtils.invokeGet(onlyPriKey.getColumnName(), obj);
		
		delete(c, priKeyValue);
	}
	
	/**
	 * 	更新对象对应的记录，且只更新指定的字段值
	 * 
	 * @param obj：要更新记录对应的对象
	 * @param fildNames ：要更新的字段对应的属性
	 * @return int:更新执行后影响的记录行数
	 */
	public void update(Object obj,String[] fieldNames) {
		// obj{fieldname1,fieldname2,..}-->update table set username=?,pwd=? where id=?;
		Class c = obj.getClass();
		TableInfo tableInfo = TableContext.poClassTableMap.get(c);
		ColumnInfo prikey = tableInfo.getOnlyPriKey();
		List<Object> params = new ArrayList<>();
		StringBuilder sql = new StringBuilder("update "+tableInfo.getTableName()+" set");
				
		for(String f:fieldNames) {
			Object fValue = ReflectUtils.invokeGet(f, obj);
			params.add(fValue);
			sql.append(f+"=?,");
		}
		sql.setCharAt(sql.length()-1, ' ');
		sql.append("where "+prikey.getColumnName()+"=?;");
		params.add(ReflectUtils.invokeGet(prikey.getColumnName(), obj));
				
		excuteDML(sql.toString(), params.toArray());
				
	}
	
	/**
	 * 	查询多行数据，查询结果封装到Class对象指定的类中。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public List queryRow(final String sql,final Class clazz,final Object[] params) {

		/**
		 * 	调用查询模板方法；
		 * 		使用内部类实现回调接口方法。 
		 * 	注意：
		 * 		匿名内部类使用的外部参数必须为常量（final 只初始化一次）。
		 */
		return (List)excuteQueryTemplate(sql, params, clazz,new CallBack() {
			
			@Override
			public Object doExcute(Connection conn, PreparedStatement ps, ResultSet rs) {
				List list = null;
				try {
					ResultSetMetaData metaData = rs.getMetaData();//元数据
					//多行情况：
					while(rs.next()) {
						if(list == null) {
							list = new ArrayList<>();
						}
						
						try {
							Object rowObj = clazz.newInstance();
							
							//多列 select username,pwd,age from emp where id>? and age<?;
							for(int i=0;i<metaData.getColumnCount();i++) {
								String columnName = metaData.getColumnLabel(i+1);
								Object columnValue = rs.getObject(i+1);								
								//调用rowObj对象的属性反射set方法，设置columnValue值
								ReflectUtils.invokeSet(rowObj, columnName, columnValue);								
							}
							list.add(rowObj);
							
						} catch (InstantiationException e) {
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						}

					}
				} catch (SQLException e) {
					e.printStackTrace();
				}

				return list;			
			}
		});
		
	}
	/**
	 * 	查询一行数据，查询结果封装到Class对象指定的类中。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public Object queryUniqueRow(String sql,Class clazz,Object[] params) {
		List list = queryRow(sql, clazz, params);
		
		return (list!=null&&list.size()>0)?null:list.get(0);
	}
	/**
	 * 	查询一行一列数据。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public Object queryValue(String sql,Object[] params) {
		
		/**
		 * 	调用查询模板方法，使用内部类实现回调接口方法的具体查询操作。
		 */
		return excuteQueryTemplate(sql, params, null, new CallBack() {
			
			@Override
			public Object doExcute(Connection conn, PreparedStatement ps, ResultSet rs) {
				Object value = null;
				try {
					while(rs.next()) {					
						value = rs.getObject(1);				
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}	
				
				return value;
			}
		});

	}
	/**
	 * 	查询一个数字(一行一列数据)。
	 * @param sql:查询语句
	 * @param clazz:封装javabean数据的Class对象
	 * @param params:sql语句的参数
	 * @return:查询结果
	 */
	public Number queryNumber(String sql,Object[] params) {

		return (Number)queryValue(sql, params);		
	}
	/**
	 * 	分页查询
	 * @param pageNum ：第几页
	 * @param size：每页显示多少记录
	 * @return
	 */
	public abstract Object queryPagenate(int pageNum,int size);
	
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
