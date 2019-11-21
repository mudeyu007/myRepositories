package com.jiaoxf.sorm.bean;
/**
 * 封装了Java属性 和Get/Set方法的源代码
 * @author acer
 *
 */
public class JavaFieldGetSet {
	/**
	 * 属性的源码信息:如 private int id;
	 */
	String FieldInfo;
	/**
	 * Get方法的源码信息：如 public String getId(){return this.id;}
	 */
	String GetInfo;
	/**
	 * Set方法的源码信息：如public void setId(int id){this.id = id;}
	 */
	String SetInfo;
	
	
	
	@Override
	public String toString() {
		System.out.println(FieldInfo);
		System.out.println(GetInfo);
		System.out.println(SetInfo);
		return super.toString();
	}
		
	public String getFieldInfo() {
		return FieldInfo;
	}
	public void setFieldInfo(String fieldInfo) {
		FieldInfo = fieldInfo;
	}
	public String getGetInfo() {
		return GetInfo;
	}
	public void setGetInfo(String getInfo) {
		GetInfo = getInfo;
	}
	public String getSetInfo() {
		return SetInfo;
	}
	public void setSetInfo(String setInfo) {
		SetInfo = setInfo;
	}
	
	public JavaFieldGetSet(String fieldInfo, String getInfo, String setInfo) {
		super();
		FieldInfo = fieldInfo;
		GetInfo = getInfo;
		SetInfo = setInfo;
	}
	public JavaFieldGetSet() {		
	}
	
	
}
