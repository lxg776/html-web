package com.xwke.spider.huntsman.core;

import org.jsoup.nodes.Document;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
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
public abstract class BaseNewsHandle implements NewsHandle {

	NewsModle newModle;
	protected Document mDocument;
	protected Document mListDocument;

	/** 抓取的配置 */
	protected ExecutorModle executor;

	public void handleNewsByExeutor(NewsConfiguration config, Page page) {
		executor = geExecutorModle(config);
		if (executor == null) {
			return;
		}
		mDocument = getDocument(executor, page);
		mListDocument = getListDocument(executor, page);

		if (isNewsListPage(executor, page)) {
			/**
			 * 当前页面是新闻列表
			 */

			// 获取新闻列放入爬虫
			List<String> urls = getLinksUrl(executor, page);
			urls = handleLinkUrl(urls);
			if (urls.size() > 0 && null != urls) {
				page.addTargetRequests(urls);
			}

		} else if (isNewsListPage(executor, page)) {
			/**
			 * 当前页面是新闻详情页面
			 */
			NewsModle newModle = getNewsByExeutor(executor, page);
			saveNews(newModle);

		}

	}

	/**
	 * 判断是否新闻列表
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract boolean isNewsListPage(ExecutorModle executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract List<String> getLinksUrl(ExecutorModle executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract List<String> handleLinkUrl(List<String> url);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract NewsModle getNewsByExeutor(ExecutorModle executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract boolean isNewsDetailPage(ExecutorModle executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract void saveNews(NewsModle newsModle);

	public abstract Document getDocument(ExecutorModle executor, Page page);

	public abstract Document getListDocument(ExecutorModle executor, Page page);

	/**
	 * 获取抓取配置
	 * 
	 * @param config
	 * @return
	 */
	public abstract ExecutorModle geExecutorModle(NewsConfiguration config);

}
