package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;

@TableName(name = "s_content_tag")
public class ContentTagModle extends BaseModle {

	private long id;
	@FieldName(name = "tag_name")
	private String tagName;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
