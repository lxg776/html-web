package com.xwke.spider.huntsman.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyDataUtil {
	
	private final static String DEFAULT_DATETIME_PATTERN = "yyyy-MM-dd HH:mm:ss";  
    private final	static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";  
    private final static String DEFAULT_TIME_PATTERN = "HH:mm:ss";  
    
    
    public static String getNowDate(){
    	SimpleDateFormat sdf =new SimpleDateFormat(DEFAULT_DATETIME_PATTERN);
    
    	return sdf.format(new Date());
    	
    }
    
    public static String getNowDate2FileName(){
    	SimpleDateFormat sdf =new SimpleDateFormat("yyyymmdd");
    
    	return sdf.format(new Date());
    	
    }

}
