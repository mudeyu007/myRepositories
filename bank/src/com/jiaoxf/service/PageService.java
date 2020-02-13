package com.jiaoxf.service;

import java.io.IOException;

import com.jiaoxf.pojo.PageInfo;

public interface PageService {
	PageInfo showPage(int pageNumber,int pageSize) throws IOException;
}
