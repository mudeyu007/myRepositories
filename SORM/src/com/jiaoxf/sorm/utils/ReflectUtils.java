package com.jiaoxf.sorm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *	 封装反射常用操作
 * @author acer
 *
 */
public class ReflectUtils {
	/**
	 * 	利用反射机制获取属性的Get方法
	 * @param fieldName：属性名称
	 * @param obj：Get方法作用的对象
	 * @return
	 */
	public static Object invokeGet(String fieldName,Object obj) {
		try {
			Class c = obj.getClass();
			Method m = c.getMethod("get"+StringUtils.firstChar2UpperCase(
					fieldName),null);
			return m.invoke(obj, null);
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 		
	}
	/**
	 * 	利用反射机制获取属性set方法
	 * @param obj
	 * @param columnName
	 * @param columnValue
	 */
	public static void invokeSet(Object obj,String columnName,Object columnValue) {
		try {
			Method m = obj.getClass().getDeclaredMethod("set"+StringUtils.firstChar2UpperCase(
					columnName),columnName.getClass());
			
			m.invoke(obj,columnValue);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
}
