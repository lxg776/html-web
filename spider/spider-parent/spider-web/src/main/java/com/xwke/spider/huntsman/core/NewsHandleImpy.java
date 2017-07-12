package com.xwke.spider.huntsman.core;

import org.springframework.stereotype.Repository;

import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.NewsModle;

import us.codecraft.webmagic.Page;

/**
 * 新闻抓取处理者
 * 
 * @author Administrator
 *
 */
@Repository
public abstract class NewsHandleImpy implements NewsHandle {
	NewsModle newModle;

	public NewsModle getNewsModleByExeutor(ExecutorModle executor, Page page) {

		if (isNewsListPage(executor, page)) {
			/**
			 * 判断当前页面是否新闻列表
			 */

		}

		return newModle;
	}

	public abstract boolean isNewsListPage(ExecutorModle executor, Page page);

}
