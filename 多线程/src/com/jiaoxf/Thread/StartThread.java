package com.jiaoxf.Thread;
/**
 * ֱ�Ӽ̳�Thread����дrun����   //�߳���
 * 1.�½��̶߳���
 * 2.����run����   //֪ͨos�����߳�ִ��
 * @author jiaoxf
 *
 */
public class StartThread {
	public static void main(String[] args) {
		//�����߳������
		Thread td = new myThread(); 
		
		//�����߳�
		td.start(); //ʹ��Tread��start()���������߳�
		while(true) {
			System.out.println("һ���ô���");
		}
		
	}

}

//����2���̳�Tread�࣬��дrun()����
class myThread extends Thread{
	@Override
	public void run() {
		while(true) {
			System.out.println("һ��������");					
		}		
	}
}

