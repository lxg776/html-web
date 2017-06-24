package com.xwke.spider.huntsman.job;


import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.PersistJobDataAfterExecution;

@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class HuntsManJob extends AbstractScheduleJob {



	

	@Override
	public String scheduleId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String groupId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		// TODO Auto-generated method stub
		System.out.println("im here!!");
	}

}
