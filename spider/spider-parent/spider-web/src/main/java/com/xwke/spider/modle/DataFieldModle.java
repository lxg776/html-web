package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.TableName;

@TableName(name = "sd_field_table")
public class DataFieldModle extends BaseModle {

	private long id;

	private String field_name;
	private String show_field_name;
	private String field_type;
	private long t_id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getField_name() {
		return field_name;
	}

	public void setField_name(String field_name) {
		this.field_name = field_name;
	}

	public String getShow_field_name() {
		return show_field_name;
	}

	public void setShow_field_name(String show_field_name) {
		this.show_field_name = show_field_name;
	}

	public String getField_type() {
		return field_type;
	}

	public void setField_type(String field_type) {
		this.field_type = field_type;
	}

	public long getT_id() {
		return t_id;
	}

	public void setT_id(long t_id) {
		this.t_id = t_id;
	}

}
