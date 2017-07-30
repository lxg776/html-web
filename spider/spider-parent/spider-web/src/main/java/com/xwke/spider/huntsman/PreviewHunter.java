package com.xwke.spider.huntsman;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.core.SimpleNewsHandle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PreviewDataModle;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ExecutorService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class PreviewHunter implements PageProcessor {

	// 网站的配置
	private NewsConfiguration config;
	private ExectorVo exectorVo;
	private Page listPage;
	private Page detailPage;



	@Resource
	SimpleNewsHandle handle;

	private NewsModle newsModle;
	
	

	public NewsModle getNewsModle() {
		return newsModle;
	}

	public void setNewsModle(NewsModle newsModle) {
		this.newsModle = newsModle;
	}

	public Page getListPage() {
		return listPage;
	}

	public void setListPage(Page listPage) {
		this.listPage = listPage;
	}

	public Page getDetailPage() {
		return detailPage;
	}

	public void setDetailPage(Page detailPage) {
		this.detailPage = detailPage;
	}

	public ExectorVo getExectorVo() {
		return exectorVo;
	}

	public void setExectorVo(ExectorVo exectorVo) {
		config = null;
		this.exectorVo = exectorVo;
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		NewsModle modle = handle.handleNewsByExeutor(config, exectorVo, page, true);
		if (null != modle) {
			newsModle = modle;
		}

	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		if (config == null) {
			config = new NewsConfiguration();
			config.setConfig(exectorVo.getConfigJsonText());

		}
		return config.getSite();

	}

	/**
	 * 抓取多urls
	 * 
	 * @param urls
	 */
	public void crawl(ExectorVo exectorVo) {

		// jxGovConfig.setConfig("123123");
		this.exectorVo = exectorVo;
		Spider.create(this).addUrl(exectorVo.getLinkUrl()).run();

	}

	/**
	 * 抓取多urls
	 * 
	 * @param urls
	 */
	public NewsModle localHandle(ExectorVo exectorVo) {

		// jxGovConfig.setConfig("123123");
		this.exectorVo = exectorVo;
		return handle.handleNewsByHtml(exectorVo);

	}



}
