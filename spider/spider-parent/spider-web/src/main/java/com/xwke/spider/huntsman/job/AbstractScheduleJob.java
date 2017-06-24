package com.xwke.spider.huntsman.job;

//import com.app.mvc.common.SpringHelper;
//import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Created by jimin on 16/5/8.
 */

public abstract class AbstractScheduleJob extends QuartzJobBean {

 //   private static ScheduleExecuteResultDao scheduleExecuteResultDao = SpringHelper.popBean(ScheduleExecuteResultDao.class);

	
	
	
	
  

    public abstract String scheduleId();

    public abstract String groupId();
}