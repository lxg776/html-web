package com.xwke.spider.huntsman;

import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.github.pagehelper.util.StringUtil;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.core.SimpleNewsHandle;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ExecutorService;

import us.codecraft.webmagic.Spider;

@Component
public class NewsSpiderHunter {

	
	SimpleNewsHandle newsHandle;
	@Resource
	ExecutorService executorService;

	/**
	 * 抓取多urls
	 * 
	 * @param urls
	 */
	public void crawl(ScheduleJob scheduleJob) {
		String[] urls = null;
		if (StringUtil.isNotEmpty(scheduleJob.getUrl())) {
			urls = scheduleJob.getUrl().split(",");
		}
		ExectorVo exectorVo = executorService.getExecutorAndDataOperationById(scheduleJob.getExecutorId());
		NewsConfiguration config = new NewsConfiguration(exectorVo.getConfigJsonText());
		NewsPageProcessor newsPageProcessor = new NewsPageProcessor(newsHandle, exectorVo, config);
		/*
		 * 进行抓取
		 */
		if (null != urls && urls.length > 0 && config.getSite() != null) {
			Spider.create(newsPageProcessor).addUrl(urls).thread(20).run();
		}

	}

}
