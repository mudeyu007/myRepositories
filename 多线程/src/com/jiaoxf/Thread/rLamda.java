package com.jiaoxf.Thread;

/**
 * Lamada���ʽ��
 * 		step1. ��̬�ڲ���  -->ֻ����ʹ��rLamdaʱ�ż��ر���
 * 		step2. �ֲ��ڲ���  -->ֻ���ڷ���������ʱ�ż���
 * 		step3. �����ڲ���  -->�����ڲ����ʹ�ñ�������ӿڻ��߸��ࡣ 
 * 		step4. Lambda���ʽ  -->ֻ���ע�߳����ʵ�֣��ڽӿ����������ӿ���ֻ��һ��������
 * @author jiaoxf
 *
 */
public class rLamda {
	//�ڲ���̬��
	static class Test implements Runnable{
		@Override
		public void run() {
			for(int i=0;i<1000;i++){
				System.out.println("һ���ô���");
			}		
		}
	}
	
	
	public static void main(String[] args) {		
		
	//step1.�ڲ���̬�ֻࣺ��ʹ��ʱ�ż���
//		new Thread(new Test()).start();
		
	//step2.�ֲ��ڲ���
//		class Test2 implements Runnable{
//			@Override
//			public void run() {
//				for(int i=0;i<1000;i++){
//					System.out.println("һ���ô���");
//				}	
//				
//			}
//		}
		
//		new Thread(new Test2()).start();
		
	//step3. ʹ�������ڲ��ࣺ�����ڲ����ʹ�ñ�������ӿڻ��߸���
//		new Thread(new Runnable() {			
//			@Override
//			public void run() {
//				for(int i=0;i<100;i++){
//					System.out.println("һ���ô���");
//				}				
//			}
//		}).start();
		
	//step4. jdk8��lambda���ʽ
		new Thread(()->{
			for(int i=0;i<100;i++){
				System.out.println("һ���ô���");
			}
		}).start();  
		
		
		for(int i=0;i<100;i++){
			System.out.println("һ��������");
		}
	}

}


