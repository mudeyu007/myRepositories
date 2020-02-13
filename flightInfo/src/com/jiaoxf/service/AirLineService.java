package com.jiaoxf.service;

import java.util.List;

import com.jiaoxf.pojo.AirLine;

public interface AirLineService {
	/**
	 * 根据起飞机场或降落机场查找航班
	 * @param takeoffid
	 * @param landid
	 * @return
	 */
	List<AirLine> showLine(int takeoffid, int landid);
}
