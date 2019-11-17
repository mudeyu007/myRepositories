package com.jiaoxf.Thread;
/**
 * Lamabda表达式推导
 * 
 * @author acer
 * 
 */
public class LambdaTest {
	//内部静态类
	private static class Like2 implements iLike{
		@Override
		public void Like() {
			System.out.println("i like lambda2!");			
		}
	}
	
	public static void main(String[] args) {
		//1.外部类实现接口
		iLike il = new like();
		il.Like();
		
		//2.内部静态类实现
		iLike il2 = new Like2();
		il2.Like();
		
		//3.局部类实现
		class Like3 implements iLike{
			@Override
			public void Like() {
				System.out.println("i like lambda3!");			
			}
		}		
		iLike il3 = new Like3();
		il3.Like();
		
		//4. 匿名类
		iLike li4=new iLike() {			
			@Override
			public void Like() {
				System.out.println("i like lambda4!");					
			}
		};
		li4.Like();
		
		//5. jdk8:Lambda表达式
		iLike li5 = ()->{
			System.out.println("i like lambda5!");					
		};
		li5.Like();
	
		
	}	
}

interface iLike{
	public void Like();
}

//1.外部实现类
class like implements iLike{
	@Override
	public void Like() {
		System.out.println("i like Lambda!");
		
	}
}