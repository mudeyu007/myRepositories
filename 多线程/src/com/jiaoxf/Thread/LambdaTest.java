package com.jiaoxf.Thread;
/**
 * Lamabda���ʽ�Ƶ�
 * 
 * @author acer
 * 
 */
public class LambdaTest {
	//�ڲ���̬��
	private static class Like2 implements iLike{
		@Override
		public void Like() {
			System.out.println("i like lambda2!");			
		}
	}
	
	public static void main(String[] args) {
		//1.�ⲿ��ʵ�ֽӿ�
		iLike il = new like();
		il.Like();
		
		//2.�ڲ���̬��ʵ��
		iLike il2 = new Like2();
		il2.Like();
		
		//3.�ֲ���ʵ��
		class Like3 implements iLike{
			@Override
			public void Like() {
				System.out.println("i like lambda3!");			
			}
		}		
		iLike il3 = new Like3();
		il3.Like();
		
		//4. ������
		iLike li4=new iLike() {			
			@Override
			public void Like() {
				System.out.println("i like lambda4!");					
			}
		};
		li4.Like();
		
		//5. jdk8:Lambda���ʽ
		iLike li5 = ()->{
			System.out.println("i like lambda5!");					
		};
		li5.Like();
	
		
	}	
}

interface iLike{
	public void Like();
}

//1.�ⲿʵ����
class like implements iLike{
	@Override
	public void Like() {
		System.out.println("i like Lambda!");
		
	}
}