package com.xwke.spider.modle;

import com.dexcoder.dal.annotation.Table;
import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;

@TableName(name = "s_content_tag")
@Table(name = "s_content_tag", pkField = "id", pkColumn = "id")
public class ContentTagModle extends BaseModle {

	private Long id;
	@FieldName(name = "tag_name")
	private String tagName;
	//备注
	private String remarks;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

}
