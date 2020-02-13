package com.jiaoxf.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jiaoxf.pojo.Airport;
import com.jiaoxf.service.AirportService;
import com.jiaoxf.service.impl.AirportServiceImpl;

public class Test {
	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		
//		String[] names =ac.getBeanDefinitionNames();
//		for(String s:names) {
//			System.out.println(s);
//		}
		
		AirportService bean = ac.getBean("airportService",AirportServiceImpl.class);
		List<Airport> list = bean.show();
		for (Airport ap:list) {
			System.out.println(ap);
		}
	}
}
