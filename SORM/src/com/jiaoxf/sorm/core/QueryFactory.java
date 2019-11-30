package com.jiaoxf.sorm.core;
/**
 * 	使用单例模式，factory只有唯一一个
 * 		1.构造器私有化；
 * 		2.提供获取单例工厂的静态方法
 * @author acer
 *
 */
public class QueryFactory {
	
	private static QueryFactory factory ;
	
	private static Query  prototypeObj; //原型对象
	static {
		try {
			Class c = Class.forName(DBmanager.getConfig().getQueryClass());//加载指定的Query类
			
			prototypeObj = (Query)c.newInstance(); //加载时创建（只创建一次）			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 构造器私有化
	 */
	private QueryFactory() {	
		
	}
	/**
	 *	 获取工厂
	 * @return：返回单例工厂
	 */
	public static QueryFactory getQueryFactory() {
		if(factory!=null) {
			return factory;
		}else {
			return new QueryFactory();
		}
	}
	
	/**
	 * 	创建Query；使用克隆模式，加快执行效率。
	 * 
	 * 		1.被克隆类须实现Cloneable接口
	 * 		2.被克隆类须重载clone()方法，提供克隆的实现方法
	 * 
	 * @return：克隆(浅克隆)得到的Query对象
	 */
	public Query creatQuery() {
		try {
			return (Query)prototypeObj.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
			return null;
		}
	}
}
