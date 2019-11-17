package com.jiaoxf.Thread;

public class RunDownload implements Runnable{
	private String url;		//远程路径
	private String name;		//存储路径
	
	public RunDownload(String url, String name) {
		super();
		this.url = url;
		this.name = name;
	}
	
	@Override
	public void run() {
		webDownload.Download(url, name);
		System.out.println(name);
	}
	
	//测试代码
	public static void main(String[] args) {
		RunDownload rd1 = new RunDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564866329108&di=cd60d409d8c050de8f8eb6ba13a7296c&imgtype=0&src=http%3A%2F%2Fwww.desktx.com%2Fd%2Ffile%2Fwallpaper%2FPeople%2Ffemales%2F20180712%2F4c701ca68fc5f7e8846dd0c3bc784b96.jpg", "img/jsy0.jpg");
		RunDownload rd2 = new RunDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564866278364&di=e5a94e01a1b1d080a08d2051303a533b&imgtype=0&src=http%3A%2F%2Fpic1.win4000.com%2Fwallpaper%2F2018-02-28%2F5a963bec9b1a4.jpg", "img/rb0.jpg");
		RunDownload rd3 = new RunDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564866502511&di=d432fb6be010601c3186eb674039b7e7&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn18%2F190%2Fw440h550%2F20180515%2Fb961-hapkuvm3447637.jpg", "img/nz0.jpg");
		
		//使用代理对象启动线程
		new Thread(rd1).start();
		new Thread(rd2).start();
		new Thread(rd3).start();

		
	}

}
