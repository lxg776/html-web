package com.xwke.spider.vo;

import com.xwke.spider.modle.ContentTagModle;

public class ContentTagVo extends ContentTagModle {
	// 关键词
	private String keyWord;
	
	public static String STATUS_WORK = "work";
	public static String STATUS_STOP= "stop";

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
