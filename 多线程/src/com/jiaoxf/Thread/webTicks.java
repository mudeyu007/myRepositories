package com.jiaoxf.Thread;
/**
 * ʹ���̴߳���
 * 			Runnable
 * 			��ʵ�� һ����Դ�������,����(�̰߳�ȫ)
 * 
 * @author acer
 *
 */

public class webTicks implements Runnable{
	private int num=99;	//��Դ
	@Override
	public void run() {
		while (true) {
			if(num<0) {
				break;
			}
			try {
				Thread.sleep(200);	//ģ�������ӳ�200ms
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName()+"-->"+num--);		
		}		
	}
	
	public static void main(String[] args) {
		//����һ����Դ
		webTicks wt = new webTicks();
		System.out.println(Thread.currentThread().getName());
		//�����������
		new Thread(wt,"��ũ").start();
		new Thread(wt,"����").start();
		new Thread(wt,"���").start();
	}
}

