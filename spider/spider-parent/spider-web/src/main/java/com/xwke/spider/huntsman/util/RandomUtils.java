package com.xwke.spider.huntsman.util;

import java.util.Random;

/**
 * 随机数工具类
 * 
 * @author liuyazhuang
 * 
 */
public final class RandomUtils {
	/**
	 * 获取指定位数的随机数
	 * 
	 * @param num
	 * @return
	 */
	public static String getRandom(int num) {
		Random random = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num; i++) {
			sb.append(String.valueOf(random.nextInt(10)));
		}
		return sb.toString();
	}

	public static String getRandomString(int length) { // length表示生成字符串的长度
		String base = "abcdefghijklmnopqrstuvwxyz0123456789";
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(base.length());
			sb.append(base.charAt(number));
		}
		return sb.toString();
	}
}