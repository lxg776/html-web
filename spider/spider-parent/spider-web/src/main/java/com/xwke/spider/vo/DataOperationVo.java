package com.xwke.spider.vo;

import com.xwke.spider.modle.DataOperationModle;

public class DataOperationVo extends DataOperationModle {

	public static String FILE_NEWSIMGS = "newsImages";

	private String keyWord;
	// 编辑的字段
	private String editFile;

	private int executorId;

	public int getExecutorId() {
		return executorId;
	}

	public void setExecutorId(int executorId) {
		this.executorId = executorId;
	}

	// 编辑的字段显示
	private String editFileShow;

	public String getEditFileShow() {
		return editFileShow;
	}

	public void setEditFileShow(String editFileShow) {
		this.editFileShow = editFileShow;
	}

	public String getEditFile() {
		return editFile;
	}

	public void setEditFile(String editFile) {
		this.editFile = editFile;
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

}
