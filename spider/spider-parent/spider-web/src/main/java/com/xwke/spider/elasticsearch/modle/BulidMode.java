package com.xwke.spider.elasticsearch.modle;

import java.util.List;

public class BulidMode {
	String objectName;
	List<FileKeyModle> fileList;
	List<BulidMode> chlidObject;

	public List<BulidMode> getChlidObject() {
		return chlidObject;
	}

	public void setChlidObject(List<BulidMode> chlidObject) {
		this.chlidObject = chlidObject;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public List<FileKeyModle> getFileList() {
		return fileList;
	}

	public void setFileList(List<FileKeyModle> fileList) {
		this.fileList = fileList;
	}

}
