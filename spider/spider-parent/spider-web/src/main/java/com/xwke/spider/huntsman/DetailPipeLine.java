package com.xwke.spider.huntsman;

import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONArray;
import com.xwke.spider.dao.ImageRecordDao;
import com.xwke.spider.dao.NewsDao;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.web.service.ImageRecordService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.xsoup.Xsoup;

@Component("detailPipeLine")
public class DetailPipeLine extends FilePipeline {

	public static String PARM_HTML = "dhtml";
	public static String CONFIG = "newsConfig";

	@Resource
	ThreadPoolTaskExecutor taskExecutor;

	@Resource
	NewsDao newDao;
	
	@Resource 
	ImageRecordService imageRecordService;

	@Override
	public void process(ResultItems resultItems, Task task) {

		NewsModle newsModle = new NewsModle();
		Page page = resultItems.get(PARM_HTML);
		NewsConfiguration config = resultItems.get(CONFIG);
		if (page == null) {
			return;
		}

		Html html = page.getHtml();

		String sourceUrl = page.getUrl().get();
		if (null != html && !newDao.isExistBySource(sourceUrl)) {
			String content = html.$(".container .show").get();
			Document document = Jsoup.parse(content);
			String title = Xsoup.compile("//h1/text()").evaluate(document).get().toString();
			// String date =
			// Xsoup.compile("//div[@class='info']/span").evaluate(document).get().toString();

			String text = Xsoup.compile("//div[@class='info']/span").evaluate(document).get().toString();
			text = text.replaceAll("&nbsp;", "");
			String date = text.substring(text.indexOf("<span>") + "<span>".length(), text.indexOf("来源："));
			String source = text.substring(text.indexOf("来源：") + "来源：".length(), text.indexOf("评论："));
			System.out.println(date);
			System.out.println(source);

			String htmlContent = Xsoup.compile("//div[@class='main']").evaluate(document).getElements().html()
					.toString();
			System.out.println(htmlContent);

			List<String> imgUrls = html.$(".container .show img").regex("<img(?:\\s+.+?)*?\\s+src=\"([^\"]*?)\".+>")
					.all();


			newsModle.setTitle(title);
			newsModle.setPubTime(date);
			
			newsModle.setSourceUrl(sourceUrl);
			newsModle.setSource(source);
			newsModle.setContent(htmlContent);
			
			CommonUtil.handleImagesByContent(newsModle, imgUrls, config, taskExecutor,imageRecordService);
			// 下载网上图片
			newDao.addNews(newsModle);

		}

	}

}
