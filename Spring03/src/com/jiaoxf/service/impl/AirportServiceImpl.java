package com.jiaoxf.service.impl;

import java.util.List;

import com.jiaoxf.mapper.AirportMapper;
import com.jiaoxf.pojo.Airport;
import com.jiaoxf.service.AirportService;
/**
 * 	由Spring来管理实现类，加载applicationContext.xml后即创建实现类对象和AirPortMapper对象；同时
 * 	将AirPortMapper对象注入给私有属性airportMapper.
 * @author acer
 *
 */
public class AirportServiceImpl implements AirportService{
	
	private AirportMapper airportMapper;
	
	public AirportMapper getAirportMapper() {
		return airportMapper;
	}

	public void setAirportMapper(AirportMapper airportMapper) {
		this.airportMapper = airportMapper;
	}

	@Override
	public List<Airport> show() {
		return airportMapper.selAll();
	}

}
