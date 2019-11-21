package com.jiaoxf.sorm.core;

public class mysqlTypeConverter implements TypeConverter{

	@Override
	public String databaseType2JavaType(String ColumnType) {
		if("verchar".equals(ColumnType)||"char".equals(ColumnType)) {
			return "String";
		}else if("int".equals(ColumnType)||
				"smallint".equals(ColumnType)||
				"smallint".equals(ColumnType)
				) {
			return "Integer";
		}else if("double".equals(ColumnType)) {
			return "Double";
		}else if("time".equals(ColumnType)){
			return "java.sql.Time";
		}else if("date".equals(ColumnType)){
			return "java.sql.Date";
		}//...
		
		
		return null;
	}

	@Override
	public String JavaType2DatabaseType(String JavaType) {
		return null;
	}

}
