package com.jiaoxf.sorm.core;
import java.lang.reflect.Field;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.jiaoxf.sorm.bean.ColumnInfo;
import com.jiaoxf.sorm.bean.TableInfo;
import com.jiaoxf.sorm.utils.JDBCUtils;
import com.jiaoxf.sorm.utils.ReflectUtils;

public class MysqlQuery implements Query{

	@Override
	public int excuteDML(String sql, Object[] params) {
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
	
	@Override
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

	@Override
	public void delete(Class clazz, Object id) {
		//Emp.class,2--> delete from emp where id =2;
		//通过Class查找TableInfo
		TableInfo tableinfo = TableContext.poClassTableMap.get(clazz);
		//获取主键
		ColumnInfo onlyPriKey = tableinfo.getOnlyPriKey();
		
		String sql = "delete from "+tableinfo.getTableName()+" where "
				+onlyPriKey.getColumnName()+"=?;";
		
		excuteDML(sql, new Object[] {id});
				
	}

	@Override
	public void delete(Object obj) {
		Class c = obj.getClass();
		TableInfo tableinfo = TableContext.poClassTableMap.get(c);
		ColumnInfo onlyPriKey = tableinfo.getOnlyPriKey();
		
		//通过反射机制，调用属性对应的get方法
		Object priKeyValue = ReflectUtils.invokeGet(onlyPriKey.getColumnName(), obj);
		
		delete(c, priKeyValue);
	}

	@Override 
	public void update(Object obj, String[] fieldNames) {
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

	@Override
	public List queryRow(String sql, Class clazz, Object[] params) {
		Connection conn = DBmanager.getConn();
		List list = null;//存储查询结果
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//给sql传参
			JDBCUtils.handleParams(ps, params);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			ResultSetMetaData metaData = rs.getMetaData();//元数据
			//多行
			while(rs.next()) {
				if(list==null) {
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
		} finally {
			DBmanager.close(rs, ps, conn);
		}	
		
		return list;
	}

	@Override
	public Object queryUniqueRow(String sql, Class clazz, Object[] params) {
		List list = queryRow(sql, clazz, params);
		
		return (list!=null&&list.size()>0)?null:list.get(0);
	}

	@Override
	public Object queryValue(String sql, Object[] params) {
		Connection conn = DBmanager.getConn();
		Object value = null;//存储查询结果
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			//给sql传参
			JDBCUtils.handleParams(ps, params);
			System.out.println(ps);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				
				value = rs.getObject(1);				
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBmanager.close(rs, ps, conn);
		}	
		
		return value;

	}

	@Override
	public Number queryNumber(String sql, Object[] params) {
		// TODO Auto-generated method stub
		return null;
	}

}
