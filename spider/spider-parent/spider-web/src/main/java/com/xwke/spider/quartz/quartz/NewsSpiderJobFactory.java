package com.xwke.spider.quartz.quartz;

import javax.annotation.Resource;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import com.xwke.spider.huntsman.NewsSpiderHunter;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.quartz.vo.ScheduleJobVo;

@Component
public class NewsSpiderJobFactory extends QuartzJobBean {
	
	@Resource
	NewsSpiderHunter newsSpiderHunter;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// TODO Auto-generated method stub
		JobDataMap mergedJobDataMap = context.getMergedJobDataMap();
		ScheduleJob scheduleJob = (ScheduleJob) mergedJobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);
		newsSpiderHunter.crawl(scheduleJob);
	}

}
