package com.jiaoxf.Thread;

/**
 * 静态代理：
 * 	1.真实角色
 * 	2.代理角色
 * @author acer
 *
 */
public class StaticProxy {
	public static void main(String[] args) {
		//创建目标对象--真实角色
		Lovers ls = new Lovers();
		//创建代理角色
		new MarryCompany(ls).HappyMarry();
		
//		new MarryCompany(new Lovers()).HappyMarry();
	}	
}
//接口
interface Marry{
	public void HappyMarry();
}

//真实角色：
class Lovers implements Marry{
	@Override
	public void HappyMarry() {
		System.out.println("the Lovers finally get married..");		
	}
}
//代理角色
class MarryCompany implements Marry{
	//目标对象（要代理的真实角色）
	private Marry target;
	public MarryCompany(Marry target) {
		this.target = target;
	}
	
	@Override
	public void HappyMarry() {
		ready();
		this.target.HappyMarry();	
		after();
	}
	
	private void ready() {
		System.out.println("布置新房");
	}
	private void after() {
		System.out.println("送亲");
	}
}
