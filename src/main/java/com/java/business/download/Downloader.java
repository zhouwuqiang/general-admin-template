package com.java.business.download;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author manbu
 * @description
 * @date 2019/12/613:51
 */
public class Downloader {

	/**
	 * 目标地址
	 */
	private URL url;

	/**
	 * 本地文件
	 */
	private File file;

	/**
	 * 线程数据
	 */
	private static final int THREAD_AMOUNT = 8;

	/**
	 * 下载目录
	 */
	private static final String DOWNLOAD_DIR_PATH = "D:/Download";

	/**
	 * 每个线程下载多少
	 */
	private int threadLen;

	public Downloader(String address, String filename) throws IOException {
		url = new URL(address);
		File dir = new File(DOWNLOAD_DIR_PATH);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		file = new File(dir, filename);
	}



	public void download() throws IOException {
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);

		// 获取文件长度
		int totalLen = conn.getContentLength();

		// 计算每个线程要下载的长度
		threadLen = (totalLen + THREAD_AMOUNT - 1) / THREAD_AMOUNT;

		System.out.println("totalLen=" + totalLen + ",threadLen:" + threadLen);

		// 在本地创建一个和服务端大小相同的文件
		RandomAccessFile raf = new RandomAccessFile(file, "rws");

		// 设置文件的大小
		raf.setLength(totalLen);
		raf.close();

		// 开启3条线程, 每个线程下载一部分数据到本地文件中
		for (int i = 0; i < THREAD_AMOUNT; i++)
			new DownloadThread(i).start();
	}


	private class DownloadThread extends Thread {
		private int id;

		public DownloadThread(int id) {
			this.id = id;
		}

		public void run() {
			int start = id * threadLen;                     // 起始位置
			int end = id * threadLen + threadLen - 1;       // 结束位置
			System.out.println("线程" + id + ": " + start + "-" + end);

			try {
				HttpURLConnection conn = (HttpURLConnection) url.openConnection();
				conn.setConnectTimeout(5000);
				conn.setRequestProperty("Range", "bytes=" + start + "-" + end);     // 设置当前线程下载的范围

				InputStream in = conn.getInputStream();
				RandomAccessFile raf = new RandomAccessFile(file, "rws");
				raf.seek(start);            // 设置保存数据的位置

				byte[] buffer = new byte[1024];
				int len;
				while ((len = in.read(buffer)) != -1) {
					raf.write(buffer, 0, len);
				}

				raf.close();

				System.out.println("线程" + id + "下载完毕");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


	public static void main(String[] args) throws IOException {

//		String address = "http://dldir1.qq.com/qqfile/qq/QQ7.9/16621/QQ7.9.exe";
		String address = "https://github.com/asche910/FileDownloader/archive/1.0.zip";
		new Downloader(address, "QQ7.9.exe").download();

//      String address = "http://api.t.dianping.com/n/api.xml?cityId=2";
//      new Downloader(address, "2.xml").download();
	}
}
