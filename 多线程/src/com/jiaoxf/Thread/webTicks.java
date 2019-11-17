package com.jiaoxf.Thread;
/**
 * 使用线程代理：
 * 			Runnable
 * 			可实现 一份资源多个代理,并发(线程安全)
 * 
 * @author acer
 *
 */

public class webTicks implements Runnable{
	private int num=99;	//资源
	@Override
	public void run() {
		while (true) {
			if(num<0) {
				break;
			}
			try {
				Thread.sleep(200);	//模拟网络延迟200ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"-->"+num--);		
		}		
	}
	
	public static void main(String[] args) {
		//穿件一个资源
		webTicks wt = new webTicks();
		System.out.println(Thread.currentThread().getName());
		//创建多个代理
		new Thread(wt,"码农").start();
		new Thread(wt,"码畜").start();
		new Thread(wt,"蚂蟥").start();
	}
}

