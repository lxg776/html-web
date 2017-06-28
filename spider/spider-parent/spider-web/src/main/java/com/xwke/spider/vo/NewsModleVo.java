package com.xwke.spider.vo;

import java.util.List;

import com.xwke.spider.modle.NewsModle;

public class NewsModleVo extends NewsModle  {
	
	private List<String> imgList;
	
	private List<String> thumbnailList;

	public List<String> getImgList() {
		return imgList;
	}

	public void setImgList(List<String> imgList) {
		this.imgList = imgList;
	}

	public List<String> getThumbnailList() {
		return thumbnailList;
	}

	public void setThumbnailList(List<String> thumbnailList) {
		this.thumbnailList = thumbnailList;
	}
	
	
	
	

	

}
