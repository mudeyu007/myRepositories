package com.jiaoxf.Thread;
/**
 * ���߳��ص㣺
 * 		�߿��ã����ݲ��ܳ���
 * 		�����ܣ��û�����Ҫ��
 * 		�߲��������û�ͬʱ����
 * 
 * ֱ�Ӽ̳�Thread����дrun����   //�߳���
 * 1.�½��̶߳���
 * 2.����run����   //֪ͨos�����߳�ִ��
 * @author jiaoxf
 *
 */
public class StartRunnable {
	public static void main(String[] args) {
		//�����߳������
		myRun mr = new myRun();
		//�����������
		Thread tr = new Thread(mr); 
		
		//�����߳�
		tr.start(); //ʹ�ô�������start()���������߳�
		
		for(int i=0;i<1000;i++){
			System.out.println("һ��������");
		}
	}

}

//����1��ʵ��Runable�ӿ�
class myRun implements Runnable{  
	@Override
	public void run() {
		for(int i=0;i<1000;i++){
//		while(true){
			System.out.println("һ��coding");
		}
		
	}

}


