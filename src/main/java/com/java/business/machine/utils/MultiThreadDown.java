package com.java.business.machine.utils;

/**
 * @author manbu
 * @description
 * @date 2019/12/416:34
 */
public class MultiThreadDown {

	public static void main(String[] args)
			throws Exception {
		//初始化DownUtil对象
		final DownUtils downUtil = new DownUtils("http://dldir1.qq.com/qqfile/qq/QQ7.9/16621/QQ7.9.exe", "QQ7.9.exe", 4);
		//开始下载
		downUtil.download();
		new Thread(() ->
		{
			while (downUtil.getCompleteRate() < 1) {
				//每隔0.1秒查询一次任务的完成进度
				//GUI程序中可根据该进度来绘制进度条
				System.out.println("已完成：" + downUtil.getCompleteRate());
				try {
					Thread.sleep(100);
				} catch (Exception ex) {
				}

			}
		}).start();
	}

}
