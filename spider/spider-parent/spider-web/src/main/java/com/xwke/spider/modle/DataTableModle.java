package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;

@TableName(name = "sd_data_table")
public class DataTableModle extends BaseModle {

	private long id;
	@FieldName(name = "table_name")
	private String tableName;
	@FieldName(name = "show_table_name")
	private String tableShowName;
	@FieldName(name = "user_id")
	private long userId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableShowName() {
		return tableShowName;
	}

	public void setTableShowName(String tableShowName) {
		this.tableShowName = tableShowName;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
