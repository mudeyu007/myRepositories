package com.jiaoxf.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jiaoxf.pojo.Account;
import com.jiaoxf.service.AccountService;
import com.jiaoxf.service.impl.AccountServiceImpl;

@WebServlet("/transfer")
public class AccountServlet extends HttpServlet{
	AccountService as = new AccountServiceImpl();
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Account accOut=new Account();
		int accOutaccNo = Integer.parseInt(req.getParameter("accOutaccNo"));
		int pwd = Integer.parseInt(req.getParameter("accOutpassword"));
		double balance = Double.parseDouble(req.getParameter("balance"));
		accOut.setAccNo(accOutaccNo);
		accOut.setPassword(pwd);
		accOut.setBalance(-balance);
		Account accIn = new Account();
		accIn.setAccNo(Integer.parseInt(req.getParameter("accInaccNo")));
		accIn.setName(req.getParameter("accInname"));
		accIn.setBalance(balance);
		
		int index = as.transfer(accIn, accOut);
		if(index==as.SUCESS) {
			//转账成功，重定向
			resp.sendRedirect("/bank/show");
		}else {
			//转账事变，重定向
			HttpSession session = req.getSession();
			session.setAttribute("code", index);
			resp.sendRedirect("/bank/error/error.jsp");
		}
		
	}
}
