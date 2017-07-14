package com.xwke.spider.huntsman.core;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.web.service.ImageRecordService;
import com.xwke.spider.web.service.NewsService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.xsoup.Xsoup;

@Component
public class SimpleNewsHandle extends BaseNewsHandle {

	@Resource
	ThreadPoolTaskExecutor taskExecutor;

	@Resource
	NewsService newsService;

	@Resource
	ImageRecordService imageRecordService;

	NewsConfiguration config;

	@Override
	public boolean isNewsListPage(ExecutorModle executor, Page page) {
		Html html = page.getHtml();
		String content = html.$(executor.getListDocmentSelector()).get();
		// TODO Auto-generated method stub
		if (StringUtil.isBlank(content)) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public List<String> getLinksUrl(ExecutorModle executor, Page page) {
		// TODO Auto-generated method stub

		// 把"amp"转化成空格
		List<String> urls = page.getHtml().$(executor.getLinksUrlSelector())
				.regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>").all();
		return urls;
	}

	@Override
	public List<String> handleLinkUrl(List<String> urls) {
		// TODO Auto-generated method stub
		List<String> linkUrls = new ArrayList();
		if (null != urls && urls.size() > 0) {
			for (String url : urls) {
				url = url.replace("amp;", "");
				linkUrls.add(url);
			}

		}
		return linkUrls;
	}

	@Override
	public NewsModle getNewsByExeutor(ExecutorModle executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		NewsModle newsModle = new NewsModle();

		// 标题
		String title="";
		if (!StringUtil.isBlank(executor.getTitleSelector())) {
			title = html.$(executor.getTitleSelector()).get();
		}
		// 发布时间
		String date = "";
		if (!StringUtil.isBlank(executor.getDateSelector())) {
			title = html.$(executor.getDateSelector()).get();
		}

		// date = date.substring(date.indexOf("<span>") + "<span>".length(),
		// date.indexOf("来源："));

		String author = "";
		if (!StringUtil.isBlank(executor.getAuthorSelector())) {
			author = Xsoup.compile(executor.getAuthorSelector()).evaluate(mDocument).get().toString();
		}

		String source = "";
		if (!StringUtil.isBlank(executor.getSourceSelector())) {
			source = Xsoup.compile(executor.getSourceSelector()).evaluate(mDocument).get().toString();
		}

		String sourceUrl = page.getUrl().get();

		List<String> imgUrls = null;

		if (!StringUtil.isBlank(executor.getSourceSelector())) {
			imgUrls = html.$(executor.getImgUrlsSelector()).regex("<img(?:\\s+.+?)*?\\s+src=\"([^\"]*?)\".+>").all();
			source = Xsoup.compile(executor.getSourceSelector()).evaluate(mDocument).get().toString();
		}

		newsModle.setTitle(title);
		newsModle.setPubTime(date);
		newsModle.setAuthor(author);
		newsModle.setSource(source);
		newsModle.setSourceUrl(sourceUrl);

		CommonUtil.handleImagesByContent(newsModle, imgUrls, config, taskExecutor, imageRecordService);

		return newsModle;
	}

	@Override
	public boolean isNewsDetailPage(ExecutorModle executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		String content = html.$(executor.getListDocmentSelector()).get();
		// TODO Auto-generated method stub
		if (StringUtil.isBlank(content)) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void saveNews(NewsModle newsModle) {
		// TODO Auto-generated method stub
		newsService.addNews(newsModle);
	}

	@Override
	public Document getDocument(ExecutorModle executor, Page page) {
		Html html = page.getHtml();
		String content = html.$(executor.getDocmentSelector()).get();
		Document document = Jsoup.parse(content);
		// TODO Auto-generated method stub
		return document;
	}

	@Override
	public Document getListDocument(ExecutorModle executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		String content = html.$(executor.getListDocmentSelector()).get();
		Document document = Jsoup.parse(content);
		return document;
	}

	@Override
	public ExecutorModle geExecutorModle(NewsConfiguration config) {
		// TODO Auto-generated method stub
		ExecutorModle executor = new ExecutorModle();
		executor.setListDocmentSelector(".container .list");
		executor.setLinksUrlSelector(".container .list li");
		executor.setExecutorDescribe("靖西政府网");
		executor.setTitleSelector(".show h1");
		executor.setDateSelector(".show .info span");
		return executor;
	}

}
