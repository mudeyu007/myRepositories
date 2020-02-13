package com.jiaoxf.service;

import com.jiaoxf.pojo.PageInfo;

public interface StudentService {
	/**
	 * 	分页查询
	 * @param sname
	 * @param tname
	 * @param pageSizeStr
	 * @param pageNumberStr
	 * @return
	 */
	PageInfo showPage(String sname,String tname,String pageSizeStr,String pageNumberStr);
}
