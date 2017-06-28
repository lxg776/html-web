package com.xwke.spider.modle;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;
import com.xwke.base.core.beans.Po;
@TableName(name = "s_news")
public class NewsModle extends Po{
	
	private long id;
	


	/** 
	* 标题
	*/ 
	private String title; 
	/** 
	* 內容
	*/ 
	private String content;
	/** 
	* 摘要
	*/
	private String summary;
	/** 
	* 抓取時間
	*/
	@FieldName(name="grasping_time")
	private String graspingTime;
	/** 
	* 发布状态
	*/
	@FieldName(name="pub_status")
	private int pubStatus;
	/** 
	* 发布时间
	*/
	private String pubTime;
	
	
	/** 
	* 文章来源
	*/
	private String source;
	
	/** 
	* 原文路径
	*/
	@FieldName(name="source_url")
	private String sourceUrl;
	
	/** 
	* 作者
	*/
	private String author;
	/** 
	* 栏目id
	*/
	@FieldName(name="column_id")
	private long columnId;
	
	@FieldName(name="pic_array")
	private String imagesJsonStr;
	
	@FieldName(name="thum_img")
	private String thumImg;//封面图片
	
	
	
	
	
	
	

	public String getThumImg() {
		return thumImg;
	}
	public void setThumImg(String thumImg) {
		this.thumImg = thumImg;
	}
	public String getImagesJsonStr() {
		return imagesJsonStr;
	}
	public void setImagesJsonStr(String imagesJsonStr) {
		this.imagesJsonStr = imagesJsonStr;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getGraspingTime() {
		return graspingTime;
	}
	public void setGraspingTime(String graspingTime) {
		this.graspingTime = graspingTime;
	}
	public int getPubStatus() {
		return pubStatus;
	}
	public void setPubStatus(int pubStatus) {
		this.pubStatus = pubStatus;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getColumnId() {
		return columnId;
	}
	
	public void setColumnId(long columnId) {
		this.columnId = columnId;
	}
	public String getPubTime() {
		return pubTime;
	}
	public void setPubTime(String pubTime) {
		this.pubTime = pubTime;
	}
	
	
	
}
