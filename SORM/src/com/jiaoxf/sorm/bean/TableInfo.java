package com.jiaoxf.sorm.bean;
/**
 * 	封装表结构信息
 * @author acer
 *
 */

import java.util.List;
import java.util.Map;

public class TableInfo {
	/**
	 * 	表名
	 */
	private String tableName;
	/**
	 * 	表字段
	 */
	private Map<String,ColumnInfo> columns;
	/**
	 * 	唯一主键信息（目前仅能处理有且仅有一个主键的情况）
	 */
	private ColumnInfo onlyPriKey;
	/**
	 * 	联合主键信息
	 */
	private List<ColumnInfo> unitKeys;
	
	public TableInfo(String tableName, Map<String, ColumnInfo> columns, List<ColumnInfo> unitKeys) {
		this.tableName = tableName;
		this.columns = columns;
		this.unitKeys = unitKeys;
	}
	public TableInfo() {

	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public Map<String, ColumnInfo> getColumns() {
		return columns;
	}
	public void setColumns(Map<String, ColumnInfo> columns) {
		this.columns = columns;
	}
	public ColumnInfo getOnlyPriKey() {
		return onlyPriKey;
	}
	public void setOnlyPriKey(ColumnInfo onlyPriKey) {
		this.onlyPriKey = onlyPriKey;
	}
	public List<ColumnInfo> getUnitKeys() {
		return unitKeys;
	}
	public void setUnitKeys(List<ColumnInfo> unitKeys) {
		this.unitKeys = unitKeys;
	}
	
	
		
}
