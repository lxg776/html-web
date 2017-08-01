package com.xwke.spider.huntsman;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.core.SimpleNewsHandle;
import com.xwke.spider.vo.ExectorVo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class NewsPageProcessor implements PageProcessor {

	SimpleNewsHandle mHandle;
	ExectorVo mExectorVo;
	// 网站的配置
	private NewsConfiguration config;

	public NewsPageProcessor(SimpleNewsHandle mHandle, ExectorVo exectorVo, NewsConfiguration config) {
		this.mHandle = mHandle;
		this.mExectorVo = exectorVo;
	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		mHandle.handleNewsByExeutor(config, mExectorVo, page, false);

	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		return config.getSite();
	}

}
