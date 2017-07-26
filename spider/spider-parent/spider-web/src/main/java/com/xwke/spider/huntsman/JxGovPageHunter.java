package com.xwke.spider.huntsman;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.xwke.base.core.beans.WherePrams;
import com.xwke.spider.dao.SiteConfigDao;
import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.modle.SiteConfigModle;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class JxGovPageHunter implements PageProcessor {

	public void crawl() {
		Spider.create(this).addUrl(getSite().getDomain()).addPipeline(detailPipeLine).thread(20).run();
	}

	public JxGovPageHunter() {

	}

	/**
	 * 抓取多urls
	 * 
	 * @param urls
	 */
	public void crawl(String[] urls) {

		// jxGovConfig.setConfig("123123");
		Spider.create(this).addUrl(urls).addPipeline(detailPipeLine).thread(20).run();
	}

	NewsConfiguration jxGovConfig;

	@Resource(name = "detailPipeLine")
	DetailPipeLine detailPipeLine;

	@Resource
	SiteConfigDao siteConfigDao;

	public void process(Page page) {

		if (page.getUrl().toString().contains("a=show")) {
			page.putField(DetailPipeLine.PARM_HTML, page);
			page.putField(DetailPipeLine.CONFIG, jxGovConfig);
		}

		// 把"amp"转化成空格
		List<String> urls = page.getHtml().$(".box .list li").regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>")
				.all();
		for (int i = 0; i < urls.size(); i++) {
			String url = urls.get(i);
			url = url.replace("amp;", "");
			page.addTargetRequest(url);

		}
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		if (jxGovConfig == null) {
			jxGovConfig = new NewsConfiguration();
			List<SiteConfigModle> list = siteConfigDao.list(new WherePrams("c_alias", " = ", "jxGov"));
			if (list != null && list.size() > 0) {
				jxGovConfig.setConfig(list.get(0).getConfigJsonText());
			}
		}
		return jxGovConfig.getSite();
	}

}
