package com.xwke.spider.huntsman.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.aspectj.util.FileUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.listener.DownImgListener;
import com.xwke.spider.huntsman.thread.DownTask;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PageOnterModle;

import net.coobird.thumbnailator.Thumbnails;

public class CommonUtil {

	public static PageOnterModle getPageOnter(Page page) {

		PageOnterModle pageOnterModle = new PageOnterModle();
		pageOnterModle.setPageNum(page.getPageNum());
		pageOnterModle.setPages(page.getPages());
		pageOnterModle.setTotal(page.getTotal());
		pageOnterModle.setDataList(page.getResult());

		return pageOnterModle;
	}

	public static String array2String(List<String> array) {
		return null;

	}

	/**
	 * 处理内容中的图片下载
	 * 
	 * @param newsModle
	 *            新闻
	 * @param imgUrls
	 *            图片集
	 * @param config
	 *            抓取的配置信息
	 * @param taskExecutor
	 *            线程池
	 */
	public static void handleImagesByContent(NewsModle newsModle, List<String> imgUrls, NewsConfiguration config,
			ThreadPoolTaskExecutor taskExecutor) {

		String content = newsModle.getContent();
		List<String> thumbnails  = new ArrayList();
		List<String> imagesList  = new ArrayList();
		String dateFileName = MyDataUtil.getNowDate2FileName();
		File group = new File(config.getThumbnail() + dateFileName);
		if (!group.exists()) {
			group.mkdirs();
		}
		
		if (null != imgUrls && imgUrls.size() > 0) {
			for (String itemImgUrl : imgUrls) {

				String endType = itemImgUrl.substring(itemImgUrl.lastIndexOf(".") + 1, itemImgUrl.length());
				String fileName = getUUid() + "." + endType;
				String filePath = config.getImgFolder() + dateFileName;
				String replaceUrl = config.getPictureSite() + dateFileName + "/" + fileName;
				imagesList.add(replaceUrl);
				content = content.replaceAll(itemImgUrl, replaceUrl);
				// 设置缩略图
				String thumbnail = config.getThumbnailSite() + dateFileName + "/" + fileName;
				thumbnails.add(thumbnail);

				// if (itemImgUrl.equals(imgUrls.get(0))) {
				// newsModle.setThumImg(Thumbnail);
				// }

				// 线程池下载图片

				taskExecutor.execute(new DownTask(itemImgUrl, filePath, fileName, new DownImgListener() {

					@Override
					public void onSuccess(String url) {
						// 生成压缩图片
						String postion = config.getThumbnail() + dateFileName + "/" + fileName;
						try {
							Thumbnails.of(new File(filePath + "/" + fileName)).size(240, 240).outputQuality(1.0f)
									.outputFormat(endType).toFile(postion);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						// if (itemImgUrl.equals(imgUrls.get(0))) {
						// newsModle.setThumImg(Thumbnail);
						// }
					}

					@Override
					public void onFail(String url) {
						// TODO Auto-generated method stub
						System.out.println("失败下载" + url);
					}
				}));
			}
		}
		// String imagesString = JSONArray.toJSONString(imgUrls);
		newsModle.setThumImgArray(JSONArray.toJSONString(thumbnails));
		newsModle.setImagesJsonStr(JSONArray.toJSONString(imagesList));
		newsModle.setContent(content);

	}

	public static String getUUid() {
		String uuid = UUID.randomUUID().toString();
		uuid = uuid.replaceAll("-", "");
		return uuid;
	}

}
