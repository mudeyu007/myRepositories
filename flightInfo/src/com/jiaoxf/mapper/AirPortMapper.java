package com.jiaoxf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jiaoxf.pojo.AirPort;

public interface AirPortMapper {
	/**
	 * 查询全部起飞机场信息
	 * @return
	 */
	@Select("select * from airport where id in(select distinct takeoffid from airline)")
	List<AirPort> selTakePort();
	/**
	 * 查询全部到达机场
	 * @return
	 */
	@Select("select * from airport where id in(select distinct landid from airline)")
	List<AirPort> selLandPort();
}
