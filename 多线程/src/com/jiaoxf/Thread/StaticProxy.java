package com.jiaoxf.Thread;

/**
 * ��̬����
 * 	1.��ʵ��ɫ
 * 	2.�����ɫ
 * @author acer
 *
 */
public class StaticProxy {
	public static void main(String[] args) {
		//����Ŀ�����--��ʵ��ɫ
		Lovers ls = new Lovers();
		//���������ɫ
		new MarryCompany(ls).HappyMarry();
		
//		new MarryCompany(new Lovers()).HappyMarry();
	}	
}
//�ӿ�
interface Marry{
	public void HappyMarry();
}

//��ʵ��ɫ��
class Lovers implements Marry{
	@Override
	public void HappyMarry() {
		System.out.println("the Lovers finally get married..");		
	}
}
//�����ɫ
class MarryCompany implements Marry{
	//Ŀ�����Ҫ�������ʵ��ɫ��
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
		System.out.println("�����·�");
	}
	private void after() {
		System.out.println("����");
	}
}
