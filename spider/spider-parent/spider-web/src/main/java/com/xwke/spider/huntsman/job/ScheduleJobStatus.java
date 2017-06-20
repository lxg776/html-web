package com.xwke.spider.huntsman.job;

/**
 * Created by jimin on 16/5/8.
 */

public enum ScheduleJobStatus {

	INIT(-1, "未开始过"), STARTED(1, "运行中"), STOPPED(0, "暂停");

	private int code;
	private String desc;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	ScheduleJobStatus(int code, String desc) {
		this.code = code;
		this.desc = desc;
	}
}