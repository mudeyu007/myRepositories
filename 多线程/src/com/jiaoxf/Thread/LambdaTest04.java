package com.jiaoxf.Thread;
/**
 * 
 * @author acer
 *
 */
public class LambdaTest04 {
	public static void main(String[] args) {
		new Thread(()->{
			for(int i=0;i<100;i++) {
				System.out.println("һ��д���롣��");
			}
		}).start();
		
		new Thread(()->{
			for(int i=0;i<100;i++) {
				System.out.println("һ�߱����С���");
			}
		}).start();
	}
}
