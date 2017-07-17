package com.xwke.spider.vo;

import java.util.List;
import java.util.Map;
import com.xwke.spider.modle.DataOperationModle;
import com.xwke.spider.modle.ExecutorModle;

public class ExectorVo extends ExecutorModle{
	
	
	
	public final static String KEY_NEWSLIST="newsList"; 
	public final static String KEY_NEWSDETAIL="detail"; 
	public final static String KEY_TITLEL="title"; 
	public final static String KEY_PUB_DATE="pubDate";
	public final static String KEY_AUTHOR="author"; 
	public final static String KEY_SOURCE="source"; 
	public final static String KEY_IMGURLS="imgurls"; 
	
	
	public static String OPERATION_LOCATION="location"; //定位操作
	public static String OPERATION_CUT="cut"; //定位操作
	public static String OPERATION_GET_TEXT="gettext"; //获取text操作 过滤文本


	
	
	
	private Map<String,List<DataOperationModle>> operationMap;

	public Map<String, List<DataOperationModle>> getOperationMap() {
		return operationMap;
	}

	public void setOperationMap(Map<String, List<DataOperationModle>> operationMap) {
		this.operationMap = operationMap;
	}

}
