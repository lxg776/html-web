package com.xwke.spider.quartz.quartz;

import javax.annotation.Resource;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.xwke.spider.dao.NewsCoumnDao;
import com.xwke.spider.dao.NewsDao;
import com.xwke.spider.huntsman.util.MyDataUtil;
import com.xwke.spider.modle.NewsColumnModle;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.quartz.vo.ScheduleJobVo;

/**
 * author : fengjing createTime : 2016-08-04 description : 异步任务工厂 version : 1.0
 */

@Component
public class JxGovSpiderJobFactory extends QuartzJobBean {

	@Resource
	NewsCoumnDao newsCoumnDao;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		NewsColumnModle newsColumnModle = new NewsColumnModle();
		newsColumnModle.setColumnName("我是10s先生");
		newsColumnModle.setColumnDescribe(MyDataUtil.getNowDate());
		newsCoumnDao.add(newsColumnModle);
	}
}
