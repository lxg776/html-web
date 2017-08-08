package com.xwke.spider.huntsman.util;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.aspectj.util.FileUtil;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.Page;
import com.xwke.base.core.sql.where.SqlUtil;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.listener.DownImgListener;
import com.xwke.spider.huntsman.thread.DownTask;
import com.xwke.spider.modle.ImageRecordModle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.web.service.ImageRecordService;

import net.coobird.thumbnailator.Thumbnails;

public class CommonUtil {

	public static PageOnterModle getPageOnter(Page page) {

		PageOnterModle pageOnterModle = new PageOnterModle();
		pageOnterModle.setPageNum(page.getPageNum());
		pageOnterModle.setPages(page.getPages());
		pageOnterModle.setTotal(page.getTotal());
		if (null != page.getResult() && page.getResult().size() > 0) {
			pageOnterModle.setDataList(page.getResult());
		}

		if (null != pageOnterModle.getDataList()) {
			pageOnterModle.setDataFla(true);
		}

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
			ThreadPoolTaskExecutor taskExecutor, ImageRecordService imageRecordService) {

		String content = newsModle.getContent();
		List<String> thumbnails = new ArrayList();
		List<String> imagesList = new ArrayList();
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
				String bUrl = newsModle.getSourceUrl().substring(0, newsModle.getSourceUrl().lastIndexOf("/"));
				String downImgUrl = "";

				if (itemImgUrl.startsWith("http")) {
					downImgUrl = itemImgUrl;
				} else if (itemImgUrl.startsWith("/")) {
					downImgUrl = bUrl + itemImgUrl;
				} else {
					downImgUrl = bUrl + "/" + itemImgUrl;
				}

				taskExecutor.execute(new DownTask(downImgUrl, filePath, fileName, new DownImgListener() {

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
						ImageRecordModle modle = new ImageRecordModle();
						modle.setImageUrl(url);
						modle.setSavePath(filePath + "/" + fileName);
						modle.setStatus("fail");
						modle.setLoadCount(1);
						imageRecordService.addImageRecord(modle);
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

	// 使用泛型
	public static final <T> T injectBean(Class<T> beanClass, Map parasMap) {
		T bean = null;
		try {
			// 通过反射生成对象
			bean = beanClass.newInstance();
			// 还可以用Class.forName生成对象
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		// 获取类的方法
		Method[] methods = beanClass.getMethods();
		int len = methods.length;
		for (int i = 0; i < len; ++i) {
			Method method = methods[i];
			String methodName = method.getName();
			// 如果方法名是set开头的且名字长度大于3的
			if (methodName.startsWith("set") && methodName.length() > 3) {
				// 获取方法的参数类型
				Class[] types = method.getParameterTypes();
				// 只有一个参数的方法才继续执行
				if (types.length == 1) {
					// 取字段名且让其首字母小写
					String attrName = firstCharToLowerCase(methodName.substring(3));
					// map中是否有属性名
					if (parasMap.containsKey(attrName)) {
						Object value = parasMap.get(attrName);
						try {
							// 通过反射的方式执行bean的mothod方法，在这里相当于执行set方法赋值
							method.invoke(bean, new Object[] { value });
						} catch (IllegalAccessException e) {
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							e.printStackTrace();
						}
					}

				}

			}
		}

		return bean;
	}

	// 取字段名且让其首字母小写
	public static String firstCharToLowerCase(String substring) {
		if (substring != null && substring.charAt(0) >= 'A' && substring.charAt(0) <= 'Z') {
			char[] arr = substring.toCharArray();
			arr[0] = (char) (arr[0] + 32);
			return new String(arr);
		} else {
			return substring;
		}
	}

}
