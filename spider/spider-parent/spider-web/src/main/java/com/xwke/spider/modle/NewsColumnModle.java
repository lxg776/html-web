package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;
import com.xwke.base.core.beans.Po;

@TableName(name = "s_news_column")
public class NewsColumnModle extends Po {

	private long id;
	@FieldName(name="c_name")
	private String columnName;
	@FieldName(name="c_describe")
	private String columnDescribe;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	public String getColumnDescribe() {
		return columnDescribe;
	}

	public void setColumnDescribe(String columnDescribe) {
		this.columnDescribe = columnDescribe;
	}



}
