package com.jiaoxf.sorm.core;
/**
 * 	负责获取数据库所有表结构和类结构的对应关系，并可以根据表结构生成类结构
 * 
 * @author acer
 *
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.jiaoxf.sorm.bean.ColumnInfo;
import com.jiaoxf.sorm.bean.TableInfo;
import com.jiaoxf.sorm.utils.JavaFileUtils;
import com.jiaoxf.sorm.utils.StringUtils;

public class TableContext {
	/**
	 * key:表名；value:TableInfo
	 * 	便于通过表名查找对应的表信息，从而查找对应的类
	 */
	public static Map<String, TableInfo> tables = new HashMap<>();
	/**
	 * key:Calss对象；value:TableInfo
	 * 	将类对象与表信息TableInfo关联，便于通过类对象查找对应的表信息。
	 */
	public static Map<Class, TableInfo> poClassTableMap = new HashMap<>();
	
	private TableContext() {		
	}
	
	
	/**
	 * 	初始化，加载表信息
	 */
	static {
		try {
				//初始化获得表的信息
				Connection con = DBmanager.getConn();
				DatabaseMetaData dbmd = con.getMetaData(); 
				
				ResultSet tableRet = dbmd.getTables(null, "%","%",new String[]{"TABLE"}); 
				
				while(tableRet.next()){
					String tableName = (String) tableRet.getObject("TABLE_NAME");
					
					TableInfo ti = new TableInfo(tableName,new HashMap<String, ColumnInfo>() ,
							new ArrayList<ColumnInfo>());
					tables.put(tableName, ti);
					
					ResultSet set = dbmd.getColumns(null, "%", tableName, "%");  //查询表中的所有字段
					while(set.next()){
						ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME"), 
								set.getString("TYPE_NAME"), 0);
						ti.getColumns().put(set.getString("COLUMN_NAME"), ci);
					}
					
					ResultSet set2 = dbmd.getPrimaryKeys(null, "%", tableName);  //查询t_user表中的主键
					while(set2.next()){
						ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
						ci2.setPriKey(1);;  //设置为主键类型
						ti.getUnitKeys().add(ci2);
					}
					
					if(ti.getUnitKeys().size()>0){  //取唯一主键。。方便使用。如果是联合主键。则为空！
						ti.setOnlyPriKey(ti.getUnitKeys().get(0));
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
		
		//更新PO包下的java类
		updatePOFile();
		//加载po包下的类：
		loadPoTables();
	}
	
	
	/**
	 *	加载PO包下的类，填充poClassTableMap，
	 *	实现Class类对象与TableInfo的对应，便于重用
	 */
	public static void loadPoTables() {
		for(TableInfo t:tables.values()) {
			try {
				Class c = Class.forName(DBmanager.getConfig().getPoPackage()
						+"."+StringUtils.firstChar2UpperCase(t.getTableName()));
				poClassTableMap.put(c, t);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 	根据表结构，更新配置的PO包下的java类
	 * 	实现从表结构到类结构
	 */
	public static void updatePOFile() {
		Map<String,TableInfo> map = TableContext.tables;
		for(TableInfo t:map.values()) {
			JavaFileUtils.creatJavaPOFile(t, new mysqlTypeConverter());
		}
	}
	
}
