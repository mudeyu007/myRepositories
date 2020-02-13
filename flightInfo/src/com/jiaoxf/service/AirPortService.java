package com.jiaoxf.service;

import java.util.List;

import com.jiaoxf.pojo.AirPort;

public interface AirPortService {
	/**
	 * 显示全部起飞机场
	 * @return
	 */
	List<AirPort> showTakeoffPort();
	/**
	 * 显示全部到达机场
	 * @return
	 */
	List<AirPort> showLandPort();
}
