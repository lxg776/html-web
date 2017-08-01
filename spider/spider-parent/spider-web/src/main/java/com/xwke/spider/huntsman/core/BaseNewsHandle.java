package com.xwke.spider.huntsman.core;

import java.util.List;
import javax.annotation.Resource;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Repository;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PreviewDataModle;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ExecutorService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.selector.PlainText;

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
	@Resource
	ExecutorService executorService;

	/**
	 * 处理本地的数据
	 * 
	 * @param executor
	 */
	public NewsModle handleNewsByHtml(ExectorVo executor) {
		mExecutor = executor;
		if (mExecutor == null) {
			return null;
		}

		PreviewDataModle dataModle = executorService.getModleByExecutorIdAndType(executor.getId(), "news");
		Page page = new Page();
		page.setRawText(dataModle.getHtmlData());
		Request request = new Request(executor.getLinkUrl());
		page.setUrl(new PlainText(request.getUrl()));
		page.setRequest(request);

		if (isNewsDetailPage(mExecutor, page)) {
			/**
			 * 当前页面是新闻详情页面
			 */
			return getNewsByExeutor(mExecutor, page);

		}
		return null;

	}

	public NewsModle handleNewsByExeutor(NewsConfiguration config, ExectorVo executor, Page page, boolean isPreview) {
		mExecutor = executor;
		if (mExecutor == null) {
			return null;
		}

		if (isNewsDetailPage(mExecutor, page)) {
			/**
			 * 当前页面是新闻详情页面
			 */
			NewsModle newModle = getNewsByExeutor(mExecutor, page);
			if (isPreview) {
				// 保存到本地
				PreviewDataModle dataModle = new PreviewDataModle();
				dataModle.setExecutorId(mExecutor.getId());
				dataModle.setHtmlData(page.getHtml().toString());
				dataModle.setType("news");
				executorService.savePreviewData(dataModle);
				return getNewsByExeutor(mExecutor, page);
			} else {
				saveNews(newModle, executor);
			}
		} else if (isNewsListPage(mExecutor, page)) {
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

		}

		return null;

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

	public abstract Document getDocument(ExectorVo executor, Page page);

	public abstract Document getListDocument(ExectorVo executor, Page page);

	public abstract void saveNews(NewsModle newsModle, ExectorVo exectorVo);

}
