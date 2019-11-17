package com.jiaoxf.Thread;
/**
 * 直接继承Thread：重写run方法   //线程体
 * 1.新建线程对象
 * 2.调用run方法   //通知os调度线程执行
 * @author jiaoxf
 *
 */
public class StartThread {
	public static void main(String[] args) {
		//创建线程体对象
		Thread td = new myThread(); 
		
		//启动线程
		td.start(); //使用Tread的start()方法启动线程
		while(true) {
			System.out.println("一边敲代码");
		}
		
	}

}

//方法2：继承Tread类，重写run()方法
class myThread extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.println("一边听音乐");					
		}		
	}
}

