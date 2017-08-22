package com.xwke.spider.elasticsearch.modle;

import com.xwke.spider.modle.NewsModle;

public class EsNewsModle extends NewsModle {

	private String contentkeyWord;

	private String newsId;

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getContentkeyWord() {
		return contentkeyWord;
	}

	public void setContentkeyWord(String contentkeyWord) {
		this.contentkeyWord = contentkeyWord;
	}

}
