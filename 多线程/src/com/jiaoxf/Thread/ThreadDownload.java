package com.jiaoxf.Thread;

public class ThreadDownload extends Thread{
	private String url;		//远程路径
	private String name;		//存储路径
	
	public ThreadDownload(String url, String name) {
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
		ThreadDownload td1 = new ThreadDownload("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1564864019854&di=14c0937e8db324156d2e4309cd7102c1&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Fsinacn%2Fw415h536%2F20180125%2Fb555-fyqwiqk6749625.jpg", "img/jsy.jpg");
		ThreadDownload td2 = new ThreadDownload("http://n.sinaimg.cn/sinacn05/w1024h1536/20180315/672f-fyscsmv8051725.jpg", "img/rb.jpg");
		ThreadDownload td3 = new ThreadDownload("http://n.sinaimg.cn/sinacn20112/73/w1080h2193/20190308/9ef0-htzuhtn6162733.jpg", "img/nz.jpg");
		
		//启动线程
		td1.start();
		td2.start();
		td3.start();
		
	}

}
