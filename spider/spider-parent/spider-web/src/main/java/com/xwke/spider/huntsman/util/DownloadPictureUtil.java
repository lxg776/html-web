package com.xwke.spider.huntsman.util;



import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.xwke.spider.huntsman.listener.DownImgListener;

public class DownloadPictureUtil {

	/**
	 * 从网络上下载图片
	 */
	public static void downloadPicture(String url, String dirPath,
			String filePath, DownImgListener listener) {

		DefaultHttpClient httpclient = new DefaultHttpClient();

		HttpGet httpget = new HttpGet(url);

		httpget.setHeader(
				"User-Agent",
				"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.79 Safari/537.1");
		httpget.setHeader("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

		try {
			HttpResponse resp = httpclient.execute(httpget);
			if (HttpStatus.SC_OK == resp.getStatusLine().getStatusCode()) {
				HttpEntity entity = resp.getEntity();

				InputStream in = entity.getContent();

				savePicToDisk(in, dirPath, filePath);

			}

		} catch (Exception e) {
			if (listener != null) {
				listener.onFail(url);
			}
			e.printStackTrace();
		} finally {
			httpclient.getConnectionManager().shutdown();
		}

		if (listener != null) {
			listener.onSuccess(url);
		}

	}

	/**
	 * 将图片写到 硬盘指定目录下
	 * 
	 * @param in
	 * @param dirPath
	 * @param filePath
	 */
	private static void savePicToDisk(InputStream in, String dirPath,
			String filePath) {

		try {
			File dir = new File(dirPath);
			if (dir == null || !dir.exists()) {
				dir.mkdirs();
			}

			// 文件真实路径
			String realPath = dirPath.concat("/"+filePath);
			File file = new File(realPath);
			if (file == null || !file.exists()) {
				file.createNewFile();
			}

			FileOutputStream fos = new FileOutputStream(file);
			byte[] buf = new byte[1024];
			int len = 0;
			while ((len = in.read(buf)) != -1) {
				fos.write(buf, 0, len);
			}
			fos.flush();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public interface DownListener {

		public void onSuccess(String url);

		public void onFail(String url);

	}

}
