package com.xwke.spider.huntsman;

import java.util.List;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.HttpClientDownloader;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Html;

public class JxGovPageHuner implements PageProcessor {

	private Site site = new NewsConfiguration().getSite();

	public void process(Page page) {
		// TODO Auto-generated method stub
		// System.out.println("-json-"+page.getJson());
		// System.out.println("----"+page.getHtml().toString());

		// System.out.println(page.getHtml().$(".box .list").toString());

		if (page.getUrl().toString().contains("a=show")) {
			page.putField(DetailPipeLine.PARM_HTML, page.getHtml());
		}

		HttpClientDownloader ss;

		List<String> urls = page.getHtml().$(".box .list li").regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>")
				.all();
		for (int i = 0; i < urls.size(); i++) {
			String url = urls.get(i);
			url = url.replace("amp;", "");
			page.addTargetRequest(url);
		}

		List<String> imgUrls = page.getHtml().$(".container .show img")
				.regex("<img(?:\\s+.+?)*?\\s+src=\"([^\"]*?)\".+>").all();

		System.out.println("00---" + page.getUrl());
		System.out.println("11---" + page.getRawText());

		for (String item : imgUrls) {

			page.addTargetRequest(item);
			// System.out.println("images" + item);
		}

	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}

	public static void main(String[] args) {
		JxGovPageHuner processor = new JxGovPageHuner();

		int crawlSize = 100_0000;
		Spider.create(new JxGovPageHuner())
				.addUrl("http://www.jingxi.gov.cn/index.php?m=content&c=index&a=show&catid=22&id=41528")
				.addPipeline(new DetailPipeLine()).thread(20).run();

		// System.out.println(processor.getSite().getDomain());

	}

	class News {
		String title;
		String content;
		String time;
		String links;

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public String getLinks() {
			return links;
		}

		public void setLinks(String links) {
			this.links = links;
		}

	}

}
