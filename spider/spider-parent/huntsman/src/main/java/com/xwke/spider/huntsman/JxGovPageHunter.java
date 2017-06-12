package com.xwke.spider.huntsman;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.xwke.base.core.dao.UserDao;
import com.xwke.modle.UserModle;
import com.xwke.spider.huntsman.configuration.BaseConfiguration;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.modle.dao.NewsDao;


import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

@Component
public class JxGovPageHunter implements PageProcessor {

	public void crawl() {
		Spider.create(this).addUrl(getSite().getDomain())
				.addPipeline(detailPipeLine).thread(20).run();
	}

	public JxGovPageHunter() {
		
	}

	@Resource(name = "jxGovConfig")
	NewsConfiguration jxGovConfig;
	
	@Resource(name="detailPipeLine")
	DetailPipeLine detailPipeLine;

	public void process(Page page) {

		if (page.getUrl().toString().contains("a=show")) {
			page.putField(DetailPipeLine.PARM_HTML, page);
			page.putField(DetailPipeLine.CONFIG, jxGovConfig);
		}

		// 把"amp"转化成空格
		List<String> urls = page.getHtml().$(".box .list li")
				.regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>")
				.all();
		for (int i = 0; i < urls.size(); i++) {
			String url = urls.get(i);
			url = url.replace("amp;", "");
			page.addTargetRequest(url);
		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return jxGovConfig.getSite();
	}

	public static void main(String[] args) {
		ApplicationContext ctx02 = new ClassPathXmlApplicationContext(
				"classpath:/spring/spring-mybatis.xml");

		JxGovPageHunter hunter = ctx02.getBean(JxGovPageHunter.class);

		hunter.crawl();

		// ApplicationContext ctx=new
		// ClassPathXmlApplicationContext("classpath:/spring/application-huntsman.xml");

	}

}
