package com.xwke.spider.huntsman.job;

/**
 * Created by jimin on 16/5/8.
 */

public class ScheduleJobSettingParam {

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	private Integer id;

	private String cron;

	private Integer status;
}