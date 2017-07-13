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

	// 详情 docment的选择表达
	private String docmentSelector;

	// 列表 docment的选择表达
	private String listDocmentSelector;

	/** 链接列表选择表达式 */
	private String linksUrlSelector;

	/** 标题表达式 */
	private String titleSelector;

	/** 标题选择表达式 */
	private String dateSelector;

	/** 标题选择表达式 */
	private String authorSelector;
	/** 来源选择表达式*/
	private String sourceSelector;
	/** 新闻图片选择表达式*/
	private String imgUrlsSelector;
	
	
	
	

	public String getImgUrlsSelector() {
		return imgUrlsSelector;
	}

	public void setImgUrlsSelector(String imgUrlsSelector) {
		this.imgUrlsSelector = imgUrlsSelector;
	}

	public String getSourceSelector() {
		return sourceSelector;
	}

	public void setSourceSelector(String sourceSelector) {
		this.sourceSelector = sourceSelector;
	}

	public String getDateSelector() {
		return dateSelector;
	}

	public void setDateSelector(String dateSelector) {
		this.dateSelector = dateSelector;
	}

	public String getAuthorSelector() {
		return authorSelector;
	}

	public void setAuthorSelector(String authorSelector) {
		this.authorSelector = authorSelector;
	}

	public String getTitleSelector() {
		return titleSelector;
	}

	public void setTitleSelector(String titleSelector) {
		this.titleSelector = titleSelector;
	}

	public String getLinksUrlSelector() {
		return linksUrlSelector;
	}

	public void setLinksUrlSelector(String linksUrlSelector) {
		this.linksUrlSelector = linksUrlSelector;
	}

	public String getDocmentSelector() {
		return docmentSelector;
	}

	public void setDocmentSelector(String docmentSelector) {
		this.docmentSelector = docmentSelector;
	}

	public String getListDocmentSelector() {
		return listDocmentSelector;
	}

	public void setListDocmentSelector(String listDocmentSelector) {
		this.listDocmentSelector = listDocmentSelector;
	}

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
