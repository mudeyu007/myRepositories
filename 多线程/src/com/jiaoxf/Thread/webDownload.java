package com.jiaoxf.Thread;
/**
 *  图片下载
 *  @author jiaoxf
 */
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class webDownload {
	/**
	 * 图片下载工具：
	 * @param url
	 * @param name
	 */
	public static void Download(String url, String name) {
		try {
			FileUtils.copyURLToFile(new URL(url), new File(name));
		} catch (MalformedURLException e) {
			System.out.println("url不合法！");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("图片下载失败！");
			e.printStackTrace();
		}
	}
}
