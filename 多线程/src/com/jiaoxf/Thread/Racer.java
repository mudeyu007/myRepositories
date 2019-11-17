package com.jiaoxf.Thread;
/**
 * 模拟龟兔赛跑
 * 
 * @author acer
 *
 */
public class Racer implements Runnable{
	private String Winner;
	@Override
	public void run() {
		for(int steps=1;steps<=10000;steps++) {
			//模拟睡觉
			if(Thread.currentThread().getName().equals("rabbit")) {
				steps++;//兔子倍速
				if(steps%50==0) {	//兔子美十步休息100ms
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
//			System.out.println(Thread.currentThread().getName()+"-->"+steps);
			boolean flag = isGameover(steps);
			if(flag) {	//游戏结束
				break;
			}
		}
	}
	
	private boolean isGameover(int steps) {
		if(Winner!=null) { //存在胜利者
			return true;
		}
		if(steps==10000) {
			Winner = Thread.currentThread().getName(); //记录胜利者
			System.out.println("winner:"+Winner);
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		//创建赛道资源
		Racer rc = new Racer();
		//创建线程代理
		new Thread(rc,"tortoise").start();
		new Thread(rc,"rabbit").start();
	}

}
