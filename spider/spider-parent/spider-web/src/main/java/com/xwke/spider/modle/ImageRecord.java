package com.xwke.spider.modle;

public class ImageRecord extends BaseModle {
	/** 图片地址 */
	private String imageUrl;
	/** 存储地址 */
	private String savePath;
	/** 加载次数 */
	private int loadCount;
	/** 状态 */
	private String status;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public int getLoadCount() {
		return loadCount;
	}

	public void setLoadCount(int loadCount) {
		this.loadCount = loadCount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
