package com.jiaoxf.Thread;
/**
 * Lamabda���ʽ�Ƶ�
 * 
 * @author acer
 *
 */
public class LambdaTest03 {
		
	public static void main(String[] args) {
		//1.�ⲿ��ʵ�ֽӿ�
		iInterest ii1 = new interest1();
		System.out.println(ii1.Lambda(1,2));
				
		iInterest ii2 =(a,b)->{
			System.out.println("i love Lambda");
			return a+b;
		};
		System.out.println(ii2.Lambda(10, 20));
		
		iInterest ii3=(a,b)->{
			return a+b;
		};
		System.out.println(ii3.Lambda(50, 60));
		
	}	
}

interface iInterest{
	public int Lambda(int a,int b);
}

//1.�ⲿʵ����
class interest1 implements iInterest{
	@Override
	public int Lambda(int a,int b) {
		System.out.println("i love Lambda");
		return a+b;
		
	}
}