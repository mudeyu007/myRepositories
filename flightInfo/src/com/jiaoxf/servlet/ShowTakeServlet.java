package com.jiaoxf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jiaoxf.service.AirPortService;
import com.jiaoxf.service.impl.AirPortServiceImpl;

@WebServlet("/showtake")
public class ShowTakeServlet extends HttpServlet{
	private AirPortService as = new AirPortServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("takeport", as.showTakeoffPort());
		req.getRequestDispatcher("/showland").forward(req, resp);
		
	}
}
