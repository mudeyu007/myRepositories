package com.jiaoxf.Thread;

/**
 * Lamada表达式：
 * 		step1. 静态内部类  -->只有在使用rLamda时才加载编译
 * 		step2. 局部内部内  -->只有在方法被调用时才加载
 * 		step3. 匿名内部类  -->匿名内部类的使用必须借助接口或者父类。 
 * 		step4. Lambda表达式  -->只需关注线程体的实现（在接口中声明；接口中只有一个方法）
 * @author jiaoxf
 *
 */
public class rLamda {
	//内部静态类
	static class Test implements Runnable{
		@Override
		public void run() {
			for(int i=0;i<1000;i++){
				System.out.println("一边敲代码");
			}		
		}
	}
	
	
	public static void main(String[] args) {		
		
	//step1.内部静态类：只有使用时才加载
//		new Thread(new Test()).start();
		
	//step2.局部内部类
//		class Test2 implements Runnable{
//			@Override
//			public void run() {
//				for(int i=0;i<1000;i++){
//					System.out.println("一边敲代码");
//				}	
//				
//			}
//		}
		
//		new Thread(new Test2()).start();
		
	//step3. 使用匿名内部类：匿名内部类的使用必须借助接口或者父类
//		new Thread(new Runnable() {			
//			@Override
//			public void run() {
//				for(int i=0;i<100;i++){
//					System.out.println("一边敲代码");
//				}				
//			}
//		}).start();
		
	//step4. jdk8：lambda表达式
		new Thread(()->{
			for(int i=0;i<100;i++){
				System.out.println("一边敲代码");
			}
		}).start();  
		
		
		for(int i=0;i<100;i++){
			System.out.println("一边听音乐");
		}
	}

}


