package com.jiaoxf.Thread;
/**
 *  ͼƬ����
 *  @author jiaoxf
 */
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

public class webDownload {
	/**
	 * ͼƬ���ع��ߣ�
	 * @param url
	 * @param name
	 */
	public static void Download(String url, String name) {
		try {
			FileUtils.copyURLToFile(new URL(url), new File(name));
		} catch (MalformedURLException e) {
			System.out.println("url���Ϸ���");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("ͼƬ����ʧ�ܣ�");
			e.printStackTrace();
		}
	}
}
