package com.jiaoxf.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.jiaoxf.pojo.Airport;

public interface AirportMapper {
	/**
	 * 查询全部
	 * @return
	 */
	@Select("select * from airport")
	List<Airport> selAll();
}
