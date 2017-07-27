package com.xwke.spider.huntsman.core;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Repository;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.vo.ExectorVo;

import us.codecraft.webmagic.Page;

/**
 * 新闻抓取处理者
 * 
 * @author Administrator
 *
 */
@Repository
public abstract class BaseNewsHandle implements NewsHandle {

	/** 抓取的配置 */
	protected ExectorVo mExecutor;

	protected List<NewsModle> newsList = new ArrayList();

	public List<NewsModle> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<NewsModle> newsList) {
		this.newsList = newsList;
	}

	public void handleNewsByExeutor(NewsConfiguration config, ExectorVo executor, Page page, boolean isPreview) {
		mExecutor = executor;
		if (mExecutor == null) {
			return;
		}

		if (isNewsListPage(mExecutor, page)) {
			/**
			 * 当前页面是新闻列表
			 */

			// 获取新闻列放入爬虫
			List<String> urls = getLinksUrl(mExecutor, page);
			urls = handleLinkUrl(urls);
			if (urls.size() > 0 && null != urls) {

				if (isPreview) {
					if (urls.size() > 0) {
						page.addTargetRequest(urls.get(0));
					}
				} else {
					page.addTargetRequests(urls);
				}
			}

		} else if (isNewsDetailPage(mExecutor, page)) {
			/**
			 * 当前页面是新闻详情页面
			 */
			NewsModle newModle = getNewsByExeutor(mExecutor, page);
			if (isPreview) {
				newsList.add(newModle);
			} else {
				saveNews(newModle);
			}
		}

	}

	/**
	 * 判断是否新闻列表
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract boolean isNewsListPage(ExectorVo executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract List<String> getLinksUrl(ExectorVo executor, Page page);

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
	public abstract NewsModle getNewsByExeutor(ExectorVo executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract boolean isNewsDetailPage(ExectorVo executor, Page page);

	/**
	 * 获取新闻列表链接
	 * 
	 * @param executor
	 * @param page
	 * @return
	 */
	public abstract void saveNews(NewsModle newsModle);

	public abstract Document getDocument(ExectorVo executor, Page page);

	public abstract Document getListDocument(ExectorVo executor, Page page);

}
