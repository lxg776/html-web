package com.xwke.spider.huntsman;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.webmagic.selector.HtmlNode;
import us.codecraft.xsoup.Xsoup;

public class DetailPipeLine extends FilePipeline {

	public static String PARM_HTML = "dhtml";

	@Override
	public void process(ResultItems resultItems, Task task) {

		Html html = resultItems.get(PARM_HTML);
		if (null != html) {
			String content = html.$(".container .show").get();

			Document document = Jsoup.parse(content);

			String title = Xsoup.compile("//h1/text()").evaluate(document).get().toString();
			String date = Xsoup.compile("//div[@class='info']/span").evaluate(document).get().toString();
			String content01 = Xsoup.compile("//div[@class='main']").evaluate(document).get().toString();

			System.out.println(title);
			System.out.println(date);
			System.out.println(content01);

		
			
		
			
		

		}

	}

}
