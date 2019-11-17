package com.jiaoxf.Thread;
/**
 * ģ���������
 * 
 * @author acer
 *
 */
public class Racer implements Runnable{
	private String Winner;
	@Override
	public void run() {
		for(int steps=1;steps<=10000;steps++) {
			//ģ��˯��
			if(Thread.currentThread().getName().equals("rabbit")) {
				steps++;//���ӱ���
				if(steps%50==0) {	//������ʮ����Ϣ100ms
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
//			System.out.println(Thread.currentThread().getName()+"-->"+steps);
			boolean flag = isGameover(steps);
			if(flag) {	//��Ϸ����
				break;
			}
		}
	}
	
	private boolean isGameover(int steps) {
		if(Winner!=null) { //����ʤ����
			return true;
		}
		if(steps==10000) {
			Winner = Thread.currentThread().getName(); //��¼ʤ����
			System.out.println("winner:"+Winner);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		//����������Դ
		Racer rc = new Racer();
		//�����̴߳���
		new Thread(rc,"tortoise").start();
		new Thread(rc,"rabbit").start();
	}

}
