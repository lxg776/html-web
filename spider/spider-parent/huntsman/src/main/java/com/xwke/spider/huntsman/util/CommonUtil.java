package com.xwke.spider.huntsman.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.listener.DownImgListener;
import com.xwke.spider.huntsman.thread.DownTask;
import com.xwke.spider.modle.NewsModle;

public class CommonUtil {
	
	
	public static String array2String(List<String> array){
		return null;
		
	}
	
	
	
	
	/**
	 * 处理内容中的图片下载
	 * @param newsModle 新闻
	 * @param imgUrls 图片集
	 * @param config 抓取的配置信息
	 * @param taskExecutor 线程池
	 */
	public static void  handleImagesByContent(NewsModle newsModle,List<String> imgUrls,NewsConfiguration config, ThreadPoolTaskExecutor taskExecutor){
		
		
		String content =newsModle.getContent();
		if(null!=imgUrls&&imgUrls.size()>0){
			for(String itemImgUrl:imgUrls){
				String dateFileName= MyDataUtil.getNowDate2FileName();
				String endType = itemImgUrl.substring(itemImgUrl.lastIndexOf(".")+1, itemImgUrl.length());
				String fileName = getUUid()+"."+endType;
				String filePath=config.getImgFolder()+dateFileName;
				String replaceUrl = config.getPictureSite()+dateFileName+"/"+fileName;
				content=content.replaceAll(itemImgUrl, replaceUrl);
				//设置缩略图
				String Thumbnail = config.getThumbnail()+dateFileName+"/"+fileName;
				if(itemImgUrl.equals(imgUrls.get(0))){
					newsModle.setThumImg(Thumbnail);
				}
				
				//线程池下载图片
			   taskExecutor.execute(new DownTask(itemImgUrl,filePath,fileName,new DownImgListener() {
					
					@Override
					public void onSuccess(String url) {
						if(itemImgUrl.equals(imgUrls.get(0))){
							newsModle.setThumImg(Thumbnail);
						}
					}
					
					@Override
					public void onFail(String url) {
						// TODO Auto-generated method stub
						System.out.println("失败下载"+url);
					}
				})); 
			}
		}
		newsModle.setContent(content);
	
	}
	
	
	public static String getUUid(){
		  String uuid = UUID.randomUUID().toString();
		  uuid= uuid.replaceAll("-", "");
		  return uuid;
	}

}
