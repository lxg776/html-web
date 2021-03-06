package com.xwke.spider.quartz.model;

import java.util.Date;

import com.dexcoder.commons.pager.Pageable;

/**
 * author : fengjing createTime : 2016-08-04 description : 计划任务模型 version : 1.0
 */
public class ScheduleJob extends Pageable {

	private static final long serialVersionUID = 4888005949821878223L;

	/** 任务id */
	private Long scheduleJobId;

	/** 任务名称 */
	private String jobName;

	/** 任务别名 */
	private String aliasName;

	/** 任务分组 */
	private String jobGroup;

	/** 触发器 */
	private String jobTrigger;

	/** 任务状态 */
	private String status;

	/** 任务运行时间表达式 */
	private String cronExpression;

	/** 执行者 */
	private String executor;

	/** 任务描述 */
	private String description;

	/** 创建时间 */
	private Date gmtCreate;

	/** 修改时间 */
	private Date gmtModify;

	/** 任务执行url */
	private String url;

	/** 任务执行url */
	private String classPath;
	
	
	
	private String nodeIds;
	
	/** 任务id */
	private Long executorId;
	
	
	
	
	
	

	public String getNodeIds() {
		return nodeIds;
	}

	public void setNodeIds(String nodeIds) {
		this.nodeIds = nodeIds;
	}

	public Long getExecutorId() {
		return executorId;
	}

	public void setExecutorId(Long executorId) {
		this.executorId = executorId;
	}

	public String getExecutor() {
		return executor;
	}

	public void setExecutor(String executor) {
		this.executor = executor;
	}

	public Long getScheduleJobId() {
		return scheduleJobId;
	}

	public void setScheduleJobId(Long scheduleJobId) {
		this.scheduleJobId = scheduleJobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}

	public String getJobGroup() {
		return jobGroup;
	}

	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}

	public String getJobTrigger() {
		return jobTrigger;
	}

	public void setJobTrigger(String jobTrigger) {
		this.jobTrigger = jobTrigger;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCronExpression() {
		return cronExpression;
	}

	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(Date gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtModify() {
		return gmtModify;
	}

	public void setGmtModify(Date gmtModify) {
		this.gmtModify = gmtModify;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ScheduleJob{" + "scheduleJobId=" + scheduleJobId + ", jobName='" + jobName + '\'' + ", aliasName='"
				+ aliasName + '\'' + ", jobGroup='" + jobGroup + '\'' + ", jobTrigger='" + jobTrigger + '\''
				+ ", status='" + status + '\'' + ", cronExpression='" + cronExpression + '\'' + ", executor='" + executor
				+ '\'' + ", description='" + description + '\'' + ", gmtCreate=" + gmtCreate + ", gmtModify="
				+ gmtModify + ", nodeIds='" + nodeIds + '\''+", executorId='" + executorId + '\''+", url='" + url + '\'' + '}';
	}
}
