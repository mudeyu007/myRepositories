package com.jiaoxf.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jiaoxf.mapper.AirLineMapper;
import com.jiaoxf.pojo.AirLine;
import com.jiaoxf.service.AirLineService;
import com.jiaoxf.util.MyBatisUtil;

public class AirLineServiceImpl implements AirLineService{

	@Override
	public List<AirLine> showLine(int takeoffid, int landid) {
		SqlSession session = MyBatisUtil.getSesstion();
		return session.getMapper(AirLineMapper.class).selByTfidLdid(takeoffid, landid);
	}
	
}
