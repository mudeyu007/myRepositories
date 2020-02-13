package com.jioxf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.jiaoxf.service.AirportService;
import com.jiaoxf.service.impl.AirportServiceImpl;
import com.sun.accessibility.internal.resources.accessibility;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
@WebServlet("/airport")
public class AirportServlet extends HttpServlet{
	private AirportService airportService;
	@Override
	public void init() throws ServletException {
		//获取applicationContext
		//1.手动加载spring容器：applicationContext.xml
		//ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		/*
		 * 2.使用tomacat监听器自动加载Spring容器;
		 * spring和tomcat整合后所有信息存放在WebApplicationContext（ApplicationContext的子接口）中，
		 */
		WebApplicationContext ac = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		airportService= ac.getBean("airportService",AirportServiceImpl.class);		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("list", airportService.show());
		req.getRequestDispatcher("/index.jsp").forward(req, resp);
	}
}
