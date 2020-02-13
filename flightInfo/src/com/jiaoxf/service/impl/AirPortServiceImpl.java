package com.jiaoxf.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jiaoxf.mapper.AirPortMapper;
import com.jiaoxf.pojo.AirPort;
import com.jiaoxf.service.AirPortService;
import com.jiaoxf.util.MyBatisUtil;

public class AirPortServiceImpl implements AirPortService{

	@Override
	public List<AirPort> showTakeoffPort() {
		SqlSession session = MyBatisUtil.getSesstion();
		
		AirPortMapper pmapper=session.getMapper(AirPortMapper.class);
		List<AirPort> list = pmapper.selTakePort();		
		return list;
	}

	@Override
	public List<AirPort> showLandPort() {
		SqlSession session = MyBatisUtil.getSesstion();
		AirPortMapper pmapper = session.getMapper(AirPortMapper.class);
		
		return pmapper.selLandPort();
	}

}
