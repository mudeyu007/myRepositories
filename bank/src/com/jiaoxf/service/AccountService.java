package com.jiaoxf.service;

import java.io.IOException;

import com.jiaoxf.pojo.Account;

public interface AccountService {
	/**
	 * 	账号密码不匹配
	 */
	int ACCNO_PWD_NOT_MATCH=1;
	/**
	 * 	户名账号不匹配
	 */
	int NAME_ACCNO_NOT_MATCH=2;
	/**
	 * 	余额不足
	 */
	int BALANCE_NOT_ENOUGH=3;
	/**
	 * 	转账失败
	 */
	int ERROR=4;
	/**
	 * 	转账成功
	 */
	int SUCESS=5;
	/**
	 * 	转账
	 * @param accIn
	 * @param accOut
	 * @return
	 */
	int transfer(Account accIn,Account accOut)throws IOException ;
}
