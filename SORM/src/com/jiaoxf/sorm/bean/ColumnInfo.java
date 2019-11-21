package com.jiaoxf.sorm.bean;
/**
 * 	封装字段信息
 * @author acer
 *
 */
public class ColumnInfo {
	/**
	 * 字段名称
	 */
	private String columnName;
	/**
	 * 	字段类型
	 */
	private String ColumnType;
	/**
	 * 主键信息（0：普通键；1：主键；2：外键）
	 */
	private int priKey;
	
	
	
	public String getColumnName() {
		return columnName;
	}
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}
	public String getColumnType() {
		return ColumnType;
	}
	public void setColumnType(String columnType) {
		ColumnType = columnType;
	}
	public int getPriKey() {
		return priKey;
	}
	public void setPriKey(int priKey) {
		this.priKey = priKey;
	}
	public ColumnInfo(String columnName, String columnType, int priKey) {
		super();
		this.columnName = columnName;
		ColumnType = columnType;
		this.priKey = priKey;
	}
	public ColumnInfo() {

	}	
	
}
