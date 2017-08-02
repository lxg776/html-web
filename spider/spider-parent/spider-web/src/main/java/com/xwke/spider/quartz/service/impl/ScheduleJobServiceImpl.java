package com.xwke.spider.quartz.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dexcoder.commons.bean.BeanConverter;
import com.dexcoder.dal.JdbcDao;
import com.dexcoder.dal.build.Criteria;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.quartz.quartz.NewsSpiderJobFactory;
import com.xwke.spider.quartz.service.ScheduleJobService;
import com.xwke.spider.quartz.utils.ScheduleUtils;
import com.xwke.spider.quartz.vo.ScheduleJobVo;

/**
 * author : fengjing createTime : 2016-08-04 description : 定时任务服务实现 version :
 * 1.0
 */
@Service
public class ScheduleJobServiceImpl implements ScheduleJobService {

	public static String EXECUTOR_JXGOV_SPIDER = "jxgov_spider";
	public static String EXECUTOR_NEWS_SPIDER = "news_spider";

	/** 调度工厂Bean */
	@Autowired
	private Scheduler scheduler;

	/** 通用dao */
	@Autowired
	private JdbcDao jdbcDao;

	@Resource
	NewsSpiderJobFactory newsSpiderJobFactory;

	public void initScheduleJob() {
		List<ScheduleJob> scheduleJobList = jdbcDao.queryList(Criteria.select(ScheduleJob.class));
		if (CollectionUtils.isEmpty(scheduleJobList)) {
			return;
		}
		for (ScheduleJob scheduleJob : scheduleJobList) {

			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobName(),
					scheduleJob.getJobGroup());

			Class<? extends Job> jobClass = null;

			// 不存在，创建一个
			if (cronTrigger == null) {
				ScheduleUtils.createScheduleJob(scheduler, scheduleJob, jobClass);
			} else {
				// 已存在，那么更新相应的定时设置
				ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
			}
		}
	}

	public Class<? extends Job> getJobClassByVo(ScheduleJobVo scheduleJobVo) {
		Class<? extends Job> jobClass = null;

		if (EXECUTOR_NEWS_SPIDER.equals(scheduleJobVo.getExecutor())) {
			jobClass = NewsSpiderJobFactory.class;
		} else {
			jobClass = newsSpiderJobFactory.getClass();
		}
		return jobClass;
	}

	public Long insert(ScheduleJobVo scheduleJobVo) {
		ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob, getJobClassByVo(scheduleJobVo));
		return jdbcDao.insert(scheduleJob);
	}

	public void update(ScheduleJobVo scheduleJobVo) {
		ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
		ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
		jdbcDao.update(scheduleJob);
	}

	public void delUpdate(ScheduleJobVo scheduleJobVo) {
		ScheduleJob scheduleJob = scheduleJobVo.getTargetObject(ScheduleJob.class);
		// 先删除
		ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
		// 再创建
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob, getJobClassByVo(scheduleJobVo));
		// 数据库直接更新即可
		jdbcDao.update(scheduleJob);
	}

	public void delete(Long scheduleJobId) {
		ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
		// 删除运行的任务
		ScheduleUtils.deleteScheduleJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
		// 删除数据
		jdbcDao.delete(ScheduleJob.class, scheduleJobId);
	}

	public void runOnce(Long scheduleJobId) {
		ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
		ScheduleUtils.runOnce(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
	}

	public void pauseJob(Long scheduleJobId) {
		ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
		ScheduleUtils.pauseJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
		// 演示数据库就不更新了
	}

	public void resumeJob(Long scheduleJobId) {
		ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
		ScheduleUtils.resumeJob(scheduler, scheduleJob.getJobName(), scheduleJob.getJobGroup());
		// 演示数据库就不更新了
	}

	public ScheduleJobVo get(Long scheduleJobId) {
		ScheduleJob scheduleJob = jdbcDao.get(ScheduleJob.class, scheduleJobId);
		return scheduleJob.getTargetObject(ScheduleJobVo.class);
	}

	public List<ScheduleJobVo> queryList(ScheduleJobVo scheduleJobVo) {

		List<ScheduleJob> scheduleJobs = jdbcDao.queryList(scheduleJobVo.getTargetObject(ScheduleJob.class));

		List<ScheduleJobVo> scheduleJobVoList = BeanConverter.convert(ScheduleJobVo.class, scheduleJobs);
		try {
			for (ScheduleJobVo vo : scheduleJobVoList) {

				JobKey jobKey = ScheduleUtils.getJobKey(vo.getJobName(), vo.getJobGroup());
				List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
				if (CollectionUtils.isEmpty(triggers)) {
					continue;
				}

				// 这里一个任务可以有多个触发器， 但是我们一个任务对应一个触发器，所以只取第一个即可，清晰明了
				Trigger trigger = triggers.iterator().next();
				vo.setJobTrigger(trigger.getKey().getName());

				Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
				vo.setStatus(triggerState.name());

				if (trigger instanceof CronTrigger) {
					CronTrigger cronTrigger = (CronTrigger) trigger;
					String cronExpression = cronTrigger.getCronExpression();
					vo.setCronExpression(cronExpression);
				}
			}
		} catch (SchedulerException e) {
			// 演示用，就不处理了
		}
		return scheduleJobVoList;
	}

	/**
	 * 获取运行中的job列表
	 * 
	 * @return
	 */
	public List<ScheduleJobVo> queryExecutingJobList() {
		try {
			// 存放结果集
			List<ScheduleJobVo> jobList = new ArrayList<ScheduleJobVo>();

			// 获取scheduler中的JobGroupName
			for (String group : scheduler.getJobGroupNames()) {
				// 获取JobKey 循环遍历JobKey
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.<JobKey>groupEquals(group))) {
					JobDetail jobDetail = scheduler.getJobDetail(jobKey);
					JobDataMap jobDataMap = jobDetail.getJobDataMap();
					ScheduleJob scheduleJob = (ScheduleJob) jobDataMap.get(ScheduleJobVo.JOB_PARAM_KEY);
					ScheduleJobVo scheduleJobVo = new ScheduleJobVo();
					BeanConverter.convert(scheduleJobVo, scheduleJob);
					List<? extends Trigger> triggers = scheduler.getTriggersOfJob(jobKey);
					Trigger trigger = triggers.iterator().next();
					Trigger.TriggerState triggerState = scheduler.getTriggerState(trigger.getKey());
					scheduleJobVo.setJobTrigger(trigger.getKey().getName());
					scheduleJobVo.setStatus(triggerState.name());
					if (trigger instanceof CronTrigger) {
						CronTrigger cronTrigger = (CronTrigger) trigger;
						String cronExpression = cronTrigger.getCronExpression();
						scheduleJobVo.setCronExpression(cronExpression);
					}
					// 获取正常运行的任务列表
					if (triggerState.name().equals("NORMAL")) {
						jobList.add(scheduleJobVo);
					}
				}
			}

			/** 非集群环境获取正在执行的任务列表 */
			/**
			 * List<JobExecutionContext> executingJobs =
			 * scheduler.getCurrentlyExecutingJobs(); List<ScheduleJobVo>
			 * jobList = new ArrayList<ScheduleJobVo>(executingJobs.size()); for
			 * (JobExecutionContext executingJob : executingJobs) {
			 * ScheduleJobVo job = new ScheduleJobVo(); JobDetail jobDetail =
			 * executingJob.getJobDetail(); JobKey jobKey = jobDetail.getKey();
			 * Trigger trigger = executingJob.getTrigger();
			 * job.setJobName(jobKey.getName());
			 * job.setJobGroup(jobKey.getGroup());
			 * job.setJobTrigger(trigger.getKey().getName());
			 * Trigger.TriggerState triggerState =
			 * scheduler.getTriggerState(trigger.getKey());
			 * job.setStatus(triggerState.name()); if (trigger instanceof
			 * CronTrigger) { CronTrigger cronTrigger = (CronTrigger) trigger;
			 * String cronExpression = cronTrigger.getCronExpression();
			 * job.setCronExpression(cronExpression); } jobList.add(job); }
			 */

			return jobList;
		} catch (SchedulerException e) {
			return null;
		}

	}
}
