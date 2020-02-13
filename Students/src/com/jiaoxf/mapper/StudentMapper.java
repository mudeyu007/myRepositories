package com.jiaoxf.mapper;

import java.util.List;

import com.jiaoxf.pojo.PageInfo;
import com.jiaoxf.pojo.Student;

public interface StudentMapper {
	/**
	 * 	分页查询
	 * @param pi
	 * @return
	 */
	List<Student> selByPage(PageInfo pi);
	/**
	 * 	查询分页总数
	 * @param pi
	 * @return
	 */
	long selCount(PageInfo pi);
	
}
