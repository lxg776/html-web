package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;

@TableName(name = "s_preview_data")
public class PreviewDataModle extends BaseModle{
	private long id;

	@FieldName(name = "executor_id")
	private long executorId;
	@FieldName(name = "d_type")
	private String type;
	@FieldName(name = "html_data")
	private String htmlData;
	
	

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getExecutorId() {
		return executorId;
	}

	public void setExecutorId(long executorId) {
		this.executorId = executorId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHtmlData() {
		return htmlData;
	}

	public void setHtmlData(String htmlData) {
		this.htmlData = htmlData;
	}

}
