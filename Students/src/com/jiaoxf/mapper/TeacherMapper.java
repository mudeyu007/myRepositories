package com.jiaoxf.mapper;

import com.jiaoxf.pojo.Teacher;

public interface TeacherMapper {
	/**
	 * 	根据id查询教师信息
	 * @param id
	 * @return
	 */
	Teacher selById(int id);
}
