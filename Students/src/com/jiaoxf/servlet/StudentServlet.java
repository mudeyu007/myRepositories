package com.jiaoxf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiaoxf.pojo.PageInfo;
import com.jiaoxf.service.StudentService;
import com.jiaoxf.service.impl.StudentServiceImpl;
@WebServlet("/showpage")
public class StudentServlet extends HttpServlet{
	StudentService ss = new StudentServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pageSizeStr = req.getParameter("pageSize");
		String pageNumberStr = req.getParameter("pageNumber");
		String sname = req.getParameter("sname");
		if(sname!=null&&!sname.equals("")) {
			sname = new String(sname.getBytes("iso-8859-1"),"utf-8");			
		}
		String tname = req.getParameter("tname");
		if(tname!=null&&!tname.equals("")) {
			tname = new String(tname.getBytes("iso-8859-1"),"utf-8");			
		}
		
		PageInfo pi=ss.showPage(sname, tname, pageSizeStr, pageNumberStr);
		System.out.println(pi);
		req.setAttribute("pageInfo", pi);
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
