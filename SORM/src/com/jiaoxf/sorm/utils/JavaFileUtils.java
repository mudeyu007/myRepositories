package com.jiaoxf.sorm.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jiaoxf.sorm.bean.ColumnInfo;
import com.jiaoxf.sorm.bean.JavaFieldGetSet;
import com.jiaoxf.sorm.bean.TableInfo;
import com.jiaoxf.sorm.core.DBmanager;
import com.jiaoxf.sorm.core.TypeConverter;

/**
 *	 封装生成Java文件(源代码)的常用操作
 * @author acer
 *
 */
public class JavaFileUtils {
	/**
	 * 生成Java属性和相关的get/set方法的源码
	 * 	例：verchar name -> private String username;
	 * 
	 * @param column ：对应的数据库字段
	 * @param converter：类型转化器
	 * @return：Java属性和相应的get/set方法源码
	 */
	public static JavaFieldGetSet creatFileSetGet(ColumnInfo column,
			TypeConverter converter) {
		JavaFieldGetSet jfgs = new JavaFieldGetSet();
		/**
		 * 属性类型信息
		 */
		String javaFieldType = converter.databaseType2JavaType(column.getColumnType());
		/**
		 * 设置属性源码：private int id;
		 */
		jfgs.setFieldInfo("\tprivate "+javaFieldType+" "+column.getColumnName()+";\n\n");
		
		/**
		 * 设置get方法：public int getId(){return this.id;}
		 */
		StringBuilder getsrc = new StringBuilder();
		getsrc.append("\tpublic "+javaFieldType+" get");
		getsrc.append(StringUtils.firstChar2UpperCase(column.getColumnName())+"(){\n");
		getsrc.append("\t\treturn this."+column.getColumnName()+";\n");
		getsrc.append("\t}\n");
		jfgs.setGetInfo(getsrc.toString());
		
		
		/**
		 * 设置 set方法：public void setId(int id){this.id = id;}
		 */
		StringBuilder setsrc = new StringBuilder();
		setsrc.append("\tpublic void set");
		setsrc.append(StringUtils.firstChar2UpperCase(column.getColumnName())+"(");
		setsrc.append(column.getColumnType()+" "+column.getColumnName()+"){\n");
		setsrc.append("\t\tthis."+column.getColumnName()+" = "+column.getColumnName()+";\n");
		setsrc.append("\t}\n");
		jfgs.setSetInfo(setsrc.toString());
			
		return jfgs;
	}

	/**
	 * 	根据表信息生成Java类源码
	 * @param table：表信息
	 * @param converter：类型转换器
	 * @return：java类源码
	 */
	public static String javaClassSrc(TableInfo table,TypeConverter converter) {
		/**
		 *	获取表的字段map
		 */
		Map<String, ColumnInfo> columns = table.getColumns();		
		
		/**
		 * 	生成并保存属性及相应的get/set源码
		 */
		List<JavaFieldGetSet> javaFields = new ArrayList<>();		
		for(ColumnInfo c:columns.values()) {
			javaFields.add(JavaFileUtils.creatFileSetGet(c, converter));
		}
		/**
		 *	 生成类的源码
		 */
		StringBuilder jsrc = new StringBuilder();
		//添包头
		jsrc.append("package "+DBmanager.getConfig()+";\n\n");
		//添加导入包
		jsrc.append("import java.util.*;\n");
		jsrc.append("import java.sql.*;\n\n");
		//添加类头部
		jsrc.append("public class "+StringUtils.firstChar2UpperCase(table.getTableName())+"{\n");
		//添加属性列表
		for(JavaFieldGetSet field:javaFields) {
			jsrc.append(field.getFieldInfo());
		}
		//添加get方法列表
		for(JavaFieldGetSet field:javaFields) {
			jsrc.append(field.getGetInfo());
		}		
		//添加set方法列表
		for(JavaFieldGetSet field:javaFields) {
			jsrc.append(field.getSetInfo());
		}		
		/**
		 * 	添加空构造器
		 */
		jsrc.append("public "+StringUtils.firstChar2UpperCase(table.getTableName()));
		jsrc.append("(){\n");
		jsrc.append("}\n");
		
		
		/**
		 * 	添加类结束符
		 */
		jsrc.append("}\n");
		return jsrc.toString();
	}
	/**
	 * 	创建java类的PO(persistance Objects)包文件
	 * @param table
	 * @param converter
	 */
	public static void creatJavaPOFile(TableInfo table,TypeConverter converter) {
		/**
		 * 	获取java类源码
		 */
		String src = JavaFileUtils.javaClassSrc(table, converter);
		/**
		 * 	获取PO包文件路径
		 */
		String srcPath = DBmanager.getConfig().getSrcPath()+"\\";
		String POPath = DBmanager.getConfig().getPoPackage().replace("\\.", "\\\\");//java中一个"\"要变为两个"\\"		
		/**
		 * 	创建PO包对象
		 */
		File f = new File(srcPath+POPath);
		if(!f.exists()) {
			f.mkdirs();
		}
		/**
		 * 	源代码写入PO包
		 */
		BufferedWriter bw = null;		
		try {
			bw=new BufferedWriter(new FileWriter(f.getAbsoluteFile()+"/"+
					StringUtils.firstChar2UpperCase(table.getTableName())+".java"));
			bw.write(src);
			/**
			 * 	打印日志
			 */
			System.out.println("建立表"+table.getTableName()+"对应的Java类："+
					StringUtils.firstChar2UpperCase(table.getTableName())+".java");			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bw!=null) {
				bw.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
		
	}
	
	/**
	 * 	测试代码
	 */
//	public static void main(String[] args) {
//		ColumnInfo ci = new ColumnInfo("id", "int",0);
//		JavaFieldGetSet f = JavaFileUtils.creatFileSetGet(ci, new mysqlTypeConverter());
//		System.out.println(f);
//	}
		
}
