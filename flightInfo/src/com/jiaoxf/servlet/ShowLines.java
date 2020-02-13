package com.jiaoxf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiaoxf.service.AirLineService;
import com.jiaoxf.service.impl.AirLineServiceImpl;

@WebServlet("/show")
public class ShowLines extends HttpServlet{
	private AirLineService as = new AirLineServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int takeoffid = 0;
		String takeoffidStr = req.getParameter("takeoffid");
		if(takeoffidStr!=null&&!takeoffidStr.equals("")) {
			takeoffid = Integer.parseInt(takeoffidStr);
		}
		int landid = 0;
		String landidStr = req.getParameter("landid");
		if(landidStr!=null&&!landidStr.equals("")) {
			landid = Integer.parseInt(landidStr);
		}
			
		req.setAttribute("list", as.showLine(takeoffid, landid));
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
