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
import org.springframework.stereotype.Repository;

import com.xwke.spider.huntsman.configuration.NewsConfiguration;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.huntsman.util.HtmlUtil;
import com.xwke.spider.modle.NewsModle;
import com.xwke.spider.quartz.model.ScheduleJob;
import com.xwke.spider.vo.DataOperationVo;
import com.xwke.spider.vo.ExectorVo;
import com.xwke.spider.web.service.ImageRecordService;
import com.xwke.spider.web.service.NewsService;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.selector.Html;

@Repository
public class SimpleNewsHandle extends BaseNewsHandle {
	@Resource
	ThreadPoolTaskExecutor taskExecutor;
	@Resource
	NewsService newsService;
	@Resource
	ImageRecordService imageRecordService;

	public static String OPERATION_LOCATION = "location";// 正则定位

	public static final String STATUS_PUB = "publish";

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
		Map<String, List<DataOperationVo>> map = executor.getOperationMap();
		if (null != html && null != map) {
			if (map.containsKey(ExectorVo.KEY_NEWSLIST)) {
				List<DataOperationVo> operationList = map.get(ExectorVo.KEY_NEWSLIST);
				// String result = (String) getResultByOperation(html,
				// operationList);

				DataOperationVo operation = null;
				if (null != operationList && operationList.size() > 0) {
					operation = operationList.get(operationList.size() - 1);
				}
				if (operation != null && ExectorVo.OPERATION_LOCATION.equals(operation.getType())) {
					List<String> urls = page.getHtml().$(operation.getParam1())
							.regex("<a(?:\\s+.+?)*?\\s+href=\"([^\"]*?)\".+>(.*?)</a>").all();

					return urls;

				}
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

	private Object getResultByOperation(Html html, List<DataOperationVo> operationList) {
		String result = null;
		if (html != null && operationList != null && operationList.size() > 0) {

			for (DataOperationVo operation : operationList) {
				if (ExectorVo.OPERATION_LOCATION.equals(operation.getType())) {
					result = getLocationOperation(html, operation);
				} else if (ExectorVo.OPERATION_CUT.equals(operation.getType())) {
					result = getValueByOperation(result, operation);
				} else if (ExectorVo.OPERATION_GET_TEXT.equals(operation.getType())) {
					result = getTextFromHtml(result);
				}

			}

		}

		if (null != result && "null".equals(result.toLowerCase())) {
			return null;
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
		result = HtmlUtil.delHTMLTag(str);
		return result;
	}

	private String clearHtmlTag(String str) {
		if (!StringUtil.isBlank(str)) {
			str = str.replaceAll("&nbsp;", "");
		}
		return str;
	}

	/*
	 * 正则定位
	 */
	private String getLocationOperation(Html html, DataOperationVo operation) {
		StringBuffer result = new StringBuffer();
		if (null != operation) {
			if (!StringUtil.isBlank(operation.getParam2()) && "list".equals(operation.getParam2())) {
				List<String> listString = html.$(operation.getParam1()).all();
				if (null != listString && listString.size() > 0) {
					for (int i = 0; i < listString.size(); i++) {
						result.append(listString.get(i));
					}
				}
			} else {
				result.append(html.$(operation.getParam1()).get());
			}

		}

		// HtmlUtils.htmlEscape(input)
		return result.toString();
	}

	/*
	 * 字符串截取
	 */
	private String getValueByOperation(String str, DataOperationVo operation) {
		String result = "";
		if (null != operation && !StringUtil.isBlank(str) && !StringUtil.isBlank(operation.getParam1())
				&& !StringUtil.isBlank(operation.getParam2())) {
			result = str.substring(str.indexOf(operation.getParam1()) + operation.getParam1().length(),
					str.indexOf(operation.getParam2()));
		}

		return result;
	}

	/*
	 * 处理图片地址
	 */
	private List<String> handleImgUrlBySource(List<String> urls, String sourceUrl) {
		List<String> reUrls = null;
		if (StringUtil.isBlank(sourceUrl)) {
			return urls;
		}

		if (null != urls && urls.size() > 0) {
			reUrls = new ArrayList();
		}

		String baseUrl = sourceUrl.substring(0, sourceUrl.lastIndexOf("/"));

		for (String itemUrl : urls) {
			if (itemUrl.startsWith("http")) {
				reUrls.add(itemUrl);
			} else if (itemUrl.startsWith("/")) {
				reUrls.add(baseUrl + itemUrl);
			} else {
				reUrls.add(baseUrl + "/" + itemUrl);
			}
		}
		return reUrls;
	}

	@Override
	public NewsModle getNewsByExeutor(ExectorVo executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		NewsModle newsModle = new NewsModle();
		// 操作集合
		Map<String, List<DataOperationVo>> map = executor.getOperationMap();
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
		String content = "";
		if (map.containsKey(ExectorVo.KEY_NEWSCONTENT)) {
			content = (String) getResultByOperation(html, map.get(ExectorVo.KEY_NEWSCONTENT));
		}

		String sourceUrl = page.getUrl().get();

		List<String> imgUrls = null;
		if (map.containsKey(ExectorVo.KEY_IMGURLS)) {
			List<DataOperationVo> operationList = map.get(ExectorVo.KEY_IMGURLS);
			if (operationList != null && operationList.size() > 0) {
				DataOperationVo operationModle = operationList.get(operationList.size() - 1);
				if (!StringUtil.isBlank(operationModle.getParam1())) {

					imgUrls = html.$(operationModle.getParam1()).regex("<img(?:\\s+.+?)*?\\s+src=\"([^\"]*?)\".+>")
							.all();
					// imgUrls =
					// html.$(operationModle.getParam1()).regex("<img[^<>]*?\\ssrc=['\"]?(.*?)['\"].*?>").all();
				}

			}
		}
		newsModle.setTitle(title);
		newsModle.setPubTime(date);
		newsModle.setAuthor(author);
		newsModle.setSource(source);
		newsModle.setSourceUrl(sourceUrl);
		newsModle.setContent(content);

		NewsConfiguration config = new NewsConfiguration(executor.getConfigJsonText());

		if (imgUrls != null && imgUrls.size() > 0) {
			CommonUtil.handleImagesByContent(newsModle, imgUrls, config, taskExecutor, imageRecordService);
		}
		return newsModle;
	}

	@Override
	public boolean isNewsDetailPage(ExectorVo executor, Page page) {
		// TODO Auto-generated method stub
		Html html = page.getHtml();
		Document content = getDocument(executor, page);
		// TODO Auto-generated method stub
		if (null == content) {
			return false;
		} else {
			return true;
		}

	}

	@Override
	public void saveNews(NewsModle newsModle, ExectorVo exectorVo, ScheduleJob scheduleJob) {
		// TODO Auto-generated method stub
		String[] nodeIds = null;
		if (!StringUtil.isBlank(scheduleJob.getNodeIds())) {
			nodeIds = scheduleJob.getNodeIds().split(",");

		}
		newsService.addNewsAndRel(newsModle, nodeIds, STATUS_PUB);
	}

	@Override
	public Document getDocument(ExectorVo executor, Page page) {
		Html html = page.getHtml();
		// 操作集合
		String content = null;
		Map<String, List<DataOperationVo>> map = executor.getOperationMap();
		if (map != null) {
			if (map.containsKey(ExectorVo.KEY_NEWSDETAIL)) {
				content = (String) getResultByOperation(html, map.get(ExectorVo.KEY_NEWSDETAIL));
			}
		}
		Document document = null;
		if (!StringUtil.isBlank(content)) {
			document = Jsoup.parse(content);
		} else {
			return null;
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
		Map<String, List<DataOperationVo>> map = executor.getOperationMap();
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

}