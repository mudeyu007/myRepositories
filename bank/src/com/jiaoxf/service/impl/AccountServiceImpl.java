package com.jiaoxf.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;

import com.jiaoxf.pojo.Account;
import com.jiaoxf.pojo.Log;
import com.jiaoxf.service.AccountService;

import sun.util.logging.resources.logging;

public class AccountServiceImpl implements AccountService{

	@Override
	public int transfer(Account accIn, Account accOut) throws IOException {
		InputStream is=Resources.getResourceAsStream("mybatis.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
		SqlSession session = factory.openSession();
		
		//判断账号密码
		Account accOutSelect = session.selectOne("com.jiaoxf.mapper.AccountMapper.selByAccnoPwd",accOut);
		if(accOutSelect!=null) {
			//判断户名和账号
			Account accInSelect = session.selectOne("com.jiaoxf.mapper.AccountMapper.selByNameAccno",accIn);
			if(accInSelect!=null) {
				//判断余额
				if(accOutSelect.getBalance()>=accOut.getBalance()) {
					//转账
					int index = session.update("com.jiaoxf.mapper.AccountMapper.updateByAccno",accOut);
					index += session.update("com.jiaoxf.mapper.AccountMapper.updateByAccno",accIn);
					if(index==2) {
						//记录日志表
						Log log = new Log();
						log.setAccOut(accOutSelect.getName());
						log.setAccIn(accInSelect.getName());
						log.setMoney(accIn.getBalance());
						session.insert("com.jiaoxf.mapper.LogMapper.insert",log);
						
						//在文件中记录日志
						Logger logger = Logger.getLogger("AccountServiceImpl");
						logger.info(log.getAccOut()+"向"+log.getAccIn()+"转账："+log.getMoney()+"；时间："+new Date().toLocaleString());
						session.commit();
						session.close();
						return SUCESS;				
					}else {
						//转账失败
						session.rollback();
						session.close();
						return ERROR;
					}
				}else {
					//余额不足
					session.close();
					return BALANCE_NOT_ENOUGH;
				}
			}else {
				//户名和账号不匹配
				session.close();
				return NAME_ACCNO_NOT_MATCH;
			}			
		}else {
			//账号密码不匹配
			session.close();
			return ACCNO_PWD_NOT_MATCH;
		}
		
	}

}
