package com.jiaoxf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.jiaoxf.pojo.AirLine;

public interface AirLineMapper {
	/**
	 * 通过起飞机场或抵达机场查询航班
	 * @param takeoffid
	 * @param landid
	 * @return
	 */
	List<AirLine> selByTfidLdid(@Param("takeoff")int takeoffid,@Param("land")int landid);
}
