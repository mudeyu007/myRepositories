package com.jiaoxf.Thread;
/**
 * Lamabda���ʽ�Ƶ�
 * 
 * @author acer
 *
 */
public class LambdaTest02 {
	//�ڲ���̬��
	private static class Love2 implements iLove{
		@Override
		public void Lambda(int a) {
			System.out.println("i love Lambda"+a);
			
		}
	}
	
	public static void main(String[] args) {
		//1.�ⲿ��ʵ�ֽӿ�
		iLove il1 = new Love1();
		il1.Lambda(1);
		
		//2.�ڲ���̬��ʵ��
		iLove il2 = new Love2();
		il2.Lambda(2);
		
		//3.�ֲ���ʵ��
		class Love3 implements iLove{
			@Override
			public void Lambda(int a) {
				System.out.println("i love Lambda"+a);				
			}
		}
		iLove il3 = new Love3();
		il3.Lambda(3);
		
		//4. ������
		iLove il4 = new iLove() {			
			@Override
			public void Lambda(int a) {
				System.out.println("i love Lambda"+a);				
			}
		};
		il4.Lambda(4);
		
		//5. jdk8:Lambda���ʽ
		iLove il5 = (int a)->{
				System.out.println("i love Lambda"+a);				
		};
		il5.Lambda(5);
		
		il5 = (a)->{
			System.out.println("i love Lambda"+a);
		};
		il5.Lambda(50);
		
		il5 = a->{
			System.out.println("i love Lambda"+a);
		};
		il5.Lambda(100);
		il5 = a->System.out.println("i love Lambda"+a);
		il5.Lambda(120);
	
		
	}	
}

interface iLove{
	public void Lambda(int a);
}

//1.�ⲿʵ����
class Love1 implements iLove{
	@Override
	public void Lambda(int a) {
		System.out.println("i love Lambda"+a);
		
	}
}