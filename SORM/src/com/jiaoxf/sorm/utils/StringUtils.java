package com.jiaoxf.sorm.utils;


/**
 * 	封装字符串常用操作
 * @author acer
 *
 */
public class StringUtils {
	/**
	 * 	将数据库字段名转化为首字母大写的Java属性名
	 * @param column 数据库字段名
	 * @return 首字母大写的Java属性名
	 */
	public static String firstChar2UpperCase(String column) {
		/**
		 * 	转换步骤：abcd ->ABCD ->Abcd
		 */
		return column.toUpperCase().substring(0, 1)+column.substring(1);
	}
	
	
	
}
