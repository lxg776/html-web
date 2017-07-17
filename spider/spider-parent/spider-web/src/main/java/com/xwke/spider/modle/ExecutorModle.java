package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;
import com.xwke.base.core.beans.Po;

@TableName(name = "s_executor")
public class ExecutorModle extends BaseModle {

	private long id;
	@FieldName(name = "c_name")
	private String name;
	@FieldName(name = "c_describe")
	private String executorDescribe;

	private String type;

	private String linkUrl;

	// // 详情 docment的选择表达
	// private String docmentSelector;
	//
	// // 列表 docment的选择表达
	// private String listDocmentSelector;
	//
	//
	//
	//
	//
	//
	// /** 链接列表选择表达式 */
	// private String linksUrlSelector;
	//
	// /** 标题表达式 */
	// private String titleSelector;
	//
	// /** 标题选择表达式 */
	// private String dateSelector;
	//
	// /** 标题选择表达式 */
	// private String authorSelector;
	// /** 来源选择表达式*/
	// private String sourceSelector;
	// /** 新闻图片选择表达式*/
	// private String imgUrlsSelector;

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

	public String getExecutorDescribe() {
		return executorDescribe;
	}

	public void setExecutorDescribe(String executorDescribe) {
		this.executorDescribe = executorDescribe;
	}

}
