package com.java.business.machine.utils;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author manbu
 * @description  获取磁盘信息
 * @date 2019/12/416:15
 */
public class SpaceUtils {

	private static DecimalFormat DECIMALFORMAT = new DecimalFormat("#.##");

	/**
	 * 获取磁盘使用信息
	 *
	 * @return
	 */
	public static List<Map<String, String>> getInfo() {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		File[] roots = File.listRoots();// 获取磁盘分区列表
		for (File file : roots) {
			Map<String, String> map = new HashMap<String, String>();

			long freeSpace=file.getFreeSpace();
			long totalSpace=file.getTotalSpace();
			long usableSpace=totalSpace-freeSpace;

			map.put("path", file.getPath());
			map.put("freeSpace", freeSpace / 1024 / 1024 / 1024 + "G");// 空闲空间
			map.put("usableSpace", usableSpace / 1024 / 1024 / 1024 + "G");// 可用空间
			map.put("totalSpace",totalSpace / 1024 / 1024 / 1024 + "G");// 总空间
			map.put("percent", DECIMALFORMAT.format(((double)usableSpace/(double)totalSpace)*100)+"%");// 总空间

			list.add(map);
		}

		return list;
	}

	public static void main(String[] args) {

		System.out.println(getInfo());

	}

}
