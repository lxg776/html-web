package com.xwke.spider.huntsman;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.core.SimpleNewsHandle;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.vo.ExectorVo;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public class NewsPageProcessor implements PageProcessor {

	SimpleNewsHandle mHandle;
	ExectorVo mExectorVo;
	// 网站的配置
	private NewsConfiguration config;
	private ScheduleJob scheduleJob;

	public NewsPageProcessor(SimpleNewsHandle mHandle, ExectorVo exectorVo, ScheduleJob scheduleJob) {
		this.mHandle = mHandle;
		this.mExectorVo = exectorVo;
		this.scheduleJob = scheduleJob;

	}

	@Override
	public void process(Page page) {
		// TODO Auto-generated method stub
		mHandle.handleNewsByExeutor(mExectorVo, scheduleJob, page, false);

	}

	@Override
	public Site getSite() {
		// TODO Auto-generated method stub
		if (config == null) {
			config = new NewsConfiguration(mExectorVo.getConfigJsonText());
		}

		return config.getSite();
	}

}
