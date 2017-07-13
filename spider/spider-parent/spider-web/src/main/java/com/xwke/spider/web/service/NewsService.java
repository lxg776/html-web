package com.xwke.spider.web.service;

import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PageOnterModle;

public interface NewsService {

	/**
	 * 获取新闻列表
	 */
	public PageOnterModle getNewsList(int pageNum);

	/**
	 * 保持新闻
	 */
	public int addNews(NewsModle modle);

}
