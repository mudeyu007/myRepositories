package com.jiaoxf.service.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jiaoxf.mapper.StudentMapper;
import com.jiaoxf.mapper.TeacherMapper;
import com.jiaoxf.pojo.PageInfo;
import com.jiaoxf.pojo.Student;
import com.jiaoxf.service.StudentService;
import com.jiaoxf.util.MyBatisUtil;

public class StudentServiceImpl implements StudentService{

	@Override
	public PageInfo showPage(String sname, String tname, String pageSizeStr, String pageNumberStr) {
		int pageSize = 2;
		if(pageSizeStr!=null&&!pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		int pageNumber =1;
		if(pageNumberStr!=null&&!pageNumberStr.equals("")) {
			pageNumber = Integer.parseInt("pageNumber");
		}
		PageInfo pi = new PageInfo();
		pi.setPageNumber(pageNumber);
		pi.setPageSize(pageSize);
		pi.setPageStart(pageSize*(pageNumber-1));
		pi.setSname(sname);
		pi.setTname(tname);
		
		SqlSession session = MyBatisUtil.getSesstion();
		StudentMapper smapper = session.getMapper(StudentMapper.class);		
		List<Student> list = smapper.selByPage(pi);
		
		//查询学生表中教师信息
		TeacherMapper tmapper = session.getMapper(TeacherMapper.class);
		for(Student st:list) {
			st.setTeacher(tmapper.selById(st.getId()));
		}
		
		pi.setList(list);
		long count = smapper.selCount(pi);
		pi.setTotal(count%pageSize==0?count/pageSize:count/pageSize+1);
				
		return pi;
	}

}
