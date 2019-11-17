package com.jiaoxf.Thread;
/**
 * 多线程特点：
 * 		高可用：数据不能出错
 * 		高性能：用户体验要好
 * 		高并发：多用户同时操作
 * 
 * 直接继承Thread：重写run方法   //线程体
 * 1.新建线程对象
 * 2.调用run方法   //通知os调度线程执行
 * @author jiaoxf
 *
 */
public class StartRunnable {
	public static void main(String[] args) {
		//创建线程体对象
		myRun mr = new myRun();
		//创建代理对象
		Thread tr = new Thread(mr); 
		
		//启动线程
		tr.start(); //使用代理对象的start()方法启动线程
		
		for(int i=0;i<1000;i++){
			System.out.println("一边听音乐");
		}
	}

}

//方法1：实现Runable接口
class myRun implements Runnable{  
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
//		while(true){
			System.out.println("一边coding");
		}
		
	}

}


