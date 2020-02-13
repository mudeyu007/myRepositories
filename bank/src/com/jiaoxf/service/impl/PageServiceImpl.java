package com.jiaoxf.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.jiaoxf.pojo.Log;
import com.jiaoxf.pojo.PageInfo;
import com.jiaoxf.service.PageService;

public class PageServiceImpl implements PageService{

	@Override
	public PageInfo showPage(int pageNumber, int pageSize) throws IOException {
		PageInfo pi = new PageInfo();
		InputStream is=Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory=new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		Map<String, Object> map = new HashMap<>();
		map.put("pageStart", pageSize*(pageNumber-1));
		map.put("pageSize", pageSize);
		List<Log> list = session.selectList("com.jiaoxf.mapper.LogMapper.selPage",map);
		
		pi.setList(list);
		pi.setPageNumber(pageNumber);
		pi.setPageSize(pageSize);
		long count = session.selectOne("com.jiaoxf.mapper.LogMapper.selCount");
		pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
		return pi;
	}

}
