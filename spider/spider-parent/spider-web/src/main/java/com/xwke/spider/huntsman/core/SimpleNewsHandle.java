package com.xwke.spider.huntsman.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.huntsman.util.HtmlUtil;
import com.xwke.spider.modle.DataOperationModle;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ImageRecordService;
import com.xwke.spider.web.service.NewsService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;
import us.codecraft.xsoup.Xsoup;

@Component
public class SimpleNewsHandle extends BaseNewsHandle {

	@Resource
	ThreadPoolTaskExecutor taskExecutor;

	@Resource
	NewsService newsService;

	@Resource
	ImageRecordService imageRecordService;

	NewsConfiguration config;

	public static String OPERATION_LOCATION = "location";// 正则定位

	@Override
	public boolean isNewsListPage(ExectorVo executor, Page page) {
		Html html = page.getHtml();
		Document content = getListDocument(executor, page);
		// TODO Auto-generated method stub
		if (null == content) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public List<String> getLinksUrl(ExectorVo executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Map<String, List<DataOperationModle>> map = executor.getOperationMap();
		if (null != html && null != map) {
			if (map.containsKey(ExectorVo.KEY_NEWSLIST)) {
				List<DataOperationModle> operationList = map.get(ExectorVo.KEY_NEWSLIST);
				String result = (String) getResultByOperation(html, operationList);
				Html resultHtml = new Html(result);
				List<String> urls = resultHtml.regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>").all();
				return urls;

			}
		}
		// // 把"amp"转化成空格
		// List<String> urls = page.getHtml().$(executor.getLinksUrlSelector())
		// .regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>").all();
		return null;
	}

	@Override
	public List<String> handleLinkUrl(List<String> urls) {
		// TODO Auto-generated method stub
		List<String> linkUrls = new ArrayList();
		if (null != urls && urls.size() > 0) {
			for (String url : urls) {
				url = url.replace("amp;", "");
				linkUrls.add(url);
			}

		}
		return linkUrls;
	}

	private Object getResultByOperation(Html html, List<DataOperationModle> operationList) {
		String result = null;
		if (html != null && operationList != null && operationList.size() > 0) {

			for (DataOperationModle operation : operationList) {
				if (ExectorVo.OPERATION_LOCATION.equals(operation.getType())) {
					result = getLocationOperation(html, operation);
				} else if (ExectorVo.OPERATION_CUT.equals(operation.getType())) {
					result = getValueByOperation(result, operation);
				} else if (ExectorVo.OPERATION_GET_TEXT.equals(operation.getType())) {
					result = getTextFromHtml(result);
				}

			}

		}

		return result;
	}

	/**
	 * 获取text
	 * 
	 * @param str
	 * @return
	 */
	private String getTextFromHtml(String str) {
		String result = "";
		result = HtmlUtil.getTextFromHtml(str);
		return result;
	}

	/*
	 * 正则定位
	 */
	private String getLocationOperation(Html html, DataOperationModle operation) {
		String result = "";
		if (null != operation) {
			result = html.$(operation.getParam1()).get();
		}

		// HtmlUtils.htmlEscape(input)
		return result;
	}

	/*
	 * 字符串截取
	 */
	private String getValueByOperation(String str, DataOperationModle operation) {
		String result = "";
		if (null != operation && !StringUtil.isBlank(str) && !StringUtil.isBlank(operation.getParam1())
				&& !StringUtil.isBlank(operation.getParam2())) {
			result = str.substring(str.indexOf(operation.getParam1()) + operation.getParam1().length(),
					str.indexOf(operation.getParam2()));
		}

		return result;
	}

	@Override
	public NewsModle getNewsByExeutor(ExectorVo executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		NewsModle newsModle = new NewsModle();
		// 操作集合
		Map<String, List<DataOperationModle>> map = executor.getOperationMap();
		// 标题
		String title = "";
		if (map.containsKey(ExectorVo.KEY_TITLEL)) {
			title = (String) getResultByOperation(html, map.get(ExectorVo.KEY_TITLEL));
		}
		// 发布时间
		String date = "";
		if (map.containsKey(ExectorVo.KEY_PUB_DATE)) {
			date = (String) getResultByOperation(html, map.get(ExectorVo.KEY_PUB_DATE));
		}
		// 作者
		String author = "";
		if (map.containsKey(ExectorVo.KEY_AUTHOR)) {
			author = (String) getResultByOperation(html, map.get(ExectorVo.KEY_AUTHOR));
		}
		String source = "";
		if (map.containsKey(ExectorVo.KEY_SOURCE)) {
			source = (String) getResultByOperation(html, map.get(ExectorVo.KEY_SOURCE));
		}

		String sourceUrl = page.getUrl().get();

		List<String> imgUrls = null;
		if (map.containsKey(ExectorVo.KEY_IMGURLS)) {
			List<DataOperationModle> operationList = map.get(ExectorVo.KEY_SOURCE);
			if (operationList != null && operationList.size() > 0) {
				DataOperationModle operationModle = operationList.get(operationList.size() - 1);
				imgUrls = html.$(operationModle.getParam1()).regex("<img(?:\\s+.+?)*?\\s+src=\"([^\"]*?)\".+>").all();
			}
		}

		// if (!StringUtil.isBlank(executor.getSourceSelector())) {
		//
		// source =
		// Xsoup.compile(executor.getSourceSelector()).evaluate(mDocument).get().toString();
		// }

		newsModle.setTitle(title);
		newsModle.setPubTime(date);
		newsModle.setAuthor(author);
		newsModle.setSource(source);
		newsModle.setSourceUrl(sourceUrl);
		if (imgUrls != null && imgUrls.size() > 0) {
			CommonUtil.handleImagesByContent(newsModle, imgUrls, config, taskExecutor, imageRecordService);
		}
		return newsModle;
	}

	@Override
	public boolean isNewsDetailPage(ExectorVo executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Document content = getListDocument(executor, page);
		// TODO Auto-generated method stub
		if (null == content) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void saveNews(NewsModle newsModle) {
		// TODO Auto-generated method stub
		newsService.addNews(newsModle);
	}

	@Override
	public Document getDocument(ExectorVo executor, Page page) {
		Html html = page.getHtml();
		// 操作集合
		String content = null;
		Map<String, List<DataOperationModle>> map = executor.getOperationMap();
		if (map != null) {
			if (map.containsKey(ExectorVo.KEY_NEWSDETAIL)) {
				content = (String) getResultByOperation(html, map.get(ExectorVo.KEY_NEWSDETAIL));
			}
		}
		Document document = null;
		if (!StringUtil.isBlank(content)) {
			document = Jsoup.parse(content);
		}
		// TODO Auto-generated method stub
		return document;
	}

	@Override
	public Document getListDocument(ExectorVo executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		// 操作集合
		String content = null;
		Map<String, List<DataOperationModle>> map = executor.getOperationMap();
		if (map != null) {
			if (map.containsKey(ExectorVo.KEY_NEWSLIST)) {
				content = (String) getResultByOperation(html, map.get(ExectorVo.KEY_NEWSLIST));
			}
		}

		Document document = null;
		if (!StringUtil.isBlank(content)) {
			document = Jsoup.parse(content);
		}

		return document;
	}

	@Override
	public ExectorVo geExectorVo(NewsConfiguration config) {
		// TODO Auto-generated method stub
		ExectorVo executor = new ExectorVo();
		// executor.setListDocmentSelector(".container .list");
		// executor.setLinksUrlSelector(".container .list li");
		// executor.setExecutorDescribe("靖西政府网");
		// executor.setTitleSelector(".show h1");
		// executor.setDateSelector(".show .info span");
		return executor;
	}

}
