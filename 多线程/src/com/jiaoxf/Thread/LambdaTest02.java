package com.jiaoxf.Thread;
/**
 * Lamabda表达式推导
 * 
 * @author acer
 *
 */
public class LambdaTest02 {
	//内部静态类
	private static class Love2 implements iLove{
		@Override
		public void Lambda(int a) {
			System.out.println("i love Lambda"+a);
			
		}
	}
	
	public static void main(String[] args) {
		//1.外部类实现接口
		iLove il1 = new Love1();
		il1.Lambda(1);
		
		//2.内部静态类实现
		iLove il2 = new Love2();
		il2.Lambda(2);
		
		//3.局部类实现
		class Love3 implements iLove{
			@Override
			public void Lambda(int a) {
				System.out.println("i love Lambda"+a);				
			}
		}
		iLove il3 = new Love3();
		il3.Lambda(3);
		
		//4. 匿名类
		iLove il4 = new iLove() {			
			@Override
			public void Lambda(int a) {
				System.out.println("i love Lambda"+a);				
			}
		};
		il4.Lambda(4);
		
		//5. jdk8:Lambda表达式
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

//1.外部实现类
class Love1 implements iLove{
	@Override
	public void Lambda(int a) {
		System.out.println("i love Lambda"+a);
		
	}
}