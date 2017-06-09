package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;
import com.xwke.base.core.beans.Po;

@TableName(name = "s_news_column")
public class NewsColumnModle extends Po {

	private long id;
	@FieldName(name="c_name")
	private String name;
	@FieldName(name="c_describe")
	private String describe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

}
