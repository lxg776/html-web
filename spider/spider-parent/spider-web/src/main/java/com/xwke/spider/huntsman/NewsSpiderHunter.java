package com.xwke.spider.huntsman;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.SiteConfigDao;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.SiteConfigModle;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class NewsSpiderHunter implements PageProcessor {

	@Resource
	SiteConfigDao siteConfigDao;
	
	// 网站的配置
	private NewsConfiguration config;
	
	// 内容解析者
	private ExecutorModle executorModle;

	public void crawl() {
		Spider.create(this).addUrl(getSite().getDomain()).thread(20).run();
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub

	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		if (config == null) {
			config = new NewsConfiguration();
			List<SiteConfigModle> list = siteConfigDao.list(new WherePrams("c_alias", " = ", "jxGov"));
			if (list != null && list.size() > 0) {
				config.setConfig(list.get(0).getConfigJsonText());
			}
		}
		return config.getSite();
	}

	/**
	 * 抓取多urls
	 * 
	 * @param urls
	 */
	public void crawl(String[] urls) {

		// jxGovConfig.setConfig("123123");
		Spider.create(this).addUrl(urls).thread(20).run();
	}

}
