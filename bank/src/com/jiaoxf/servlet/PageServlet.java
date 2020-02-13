package com.jiaoxf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiaoxf.pojo.PageInfo;
import com.jiaoxf.service.PageService;
import com.jiaoxf.service.impl.PageServiceImpl;
@WebServlet("/show")
public class PageServlet extends HttpServlet{
	PageService ps = new PageServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int pageSize = 2;
		String pageSizeStr = req.getParameter("pageSize");
		if(pageSizeStr!=null&&!pageSizeStr.equals("")) {
			pageSize = Integer.parseInt(pageSizeStr);
		}
		int pageNumber =1;
		String pageNumberStr = req.getParameter("pageNumber");
		if(pageNumberStr!=null&&!pageNumberStr.equals("")) {
			pageNumber = Integer.parseInt(pageNumberStr);
		}
		
		PageInfo pi = ps.showPage(pageNumber, pageSize);
		req.setAttribute("pageInfo", pi);
		//请求转发
		req.getRequestDispatcher("/log.jsp").forward(req, resp);
		
	}
}
