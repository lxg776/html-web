package com.xwke.spider.quartz.quartz;

import javax.annotation.Resource;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.xwke.spider.dao.ExecutorDao;
import com.xwke.spider.dao.NewsDao;
import com.xwke.spider.huntsman.JxGovPageHunter;
import com.xwke.spider.huntsman.util.MyDataUtil;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.quartz.vo.ScheduleJobVo;

/**
 * author : fengjing createTime : 2016-08-04 description : 异步任务工厂 version : 1.0
 */

@Component
public class JxGovSpiderJobFactory extends QuartzJobBean {

	@Resource
	JxGovPageHunter jxGovPageHunter;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);
		
		
		String urls[] = null;
		if (scheduleJob.getUrl() != null) {
			urls = scheduleJob.getUrl().split(",");
		}
		if (null != urls && urls.length > 1) {
			jxGovPageHunter.crawl(urls);
		}
	}
}
