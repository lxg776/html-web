package com.xwke.spider.elasticsearch.service;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.admin.indices.mapping.put.PutMappingRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.Requests;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xwke.spider.elasticsearch.modle.BulidMode;
import com.xwke.spider.elasticsearch.modle.EsIndex;
import com.xwke.spider.elasticsearch.modle.EsNewsModle;
import com.xwke.spider.elasticsearch.modle.FileKeyModle;
import com.xwke.spider.huntsman.util.ObjectUtil;

@Service("esNewsService")
public class EsNewsServiceImpy {

	@Autowired
	private Client client;

	/**
	 * 用java的map构建document
	 */
	public void saveNews(EsNewsModle newsModle) {
		Map<String, Object> json = ObjectUtil.transBean2Map(newsModle);

		// 指定索引名称，type名称和documentId(documentId可选，不设置则系统自动生成)创建document
		// IndexResponse response = client.prepareIndex(indexName, typeName,
		// "2").setSource(json).execute().actionGet();
		// response中返回索引名称，type名称，doc的Id和版本信息
		client.prepareIndex(EsIndex.NEWS_INDEX, "test001").setSource(json).execute().actionGet();
	}

	public EsNewsModle getNewsById(String id) {

		EsNewsModle esNewsModle = null;

		GetResponse response = client.prepareGet(EsIndex.NEWS_INDEX, EsIndex.TYPE_NEWS, id).get();
		Map<String, Object> json = response.getSource();
		if (null != json) {
			esNewsModle = new EsNewsModle();
			ObjectUtil.transMap2Bean(json, esNewsModle);
		}
		esNewsModle.setNewsId(id);
		// client.prepareGet().set
		return esNewsModle;
	}

	public EsNewsModle updateEsNews(EsNewsModle newsModle) {

		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index(EsIndex.NEWS_INDEX);
		updateRequest.type(EsIndex.TYPE_NEWS);
		updateRequest.id(newsModle.getNewsId());
		Map<String, Object> json = ObjectUtil.transBean2Map(newsModle);
		updateRequest.doc(json);

		EsNewsModle esNewsModle = null;
		try {
			client.update(updateRequest).get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return esNewsModle;
	}

	public void createMapping(String indices, String mappingType, BulidMode buliderMolde) throws Exception {

		XContentBuilder builder = buliderByObject(buliderMolde, null);
		PutMappingRequest mapping = Requests.putMappingRequest(indices).type(mappingType).source(builder);
		//System.out.println(mapping.source());
		client.admin().indices().putMapping(mapping).actionGet();

	}
	
	
	

	private XContentBuilder buliderByObject(BulidMode buliderMolde, XContentBuilder builder) throws Exception {
		boolean endFla = false;
		if (builder == null) {
			builder = XContentFactory.jsonBuilder().startObject();
			endFla = true;
		}
		builder.startObject(buliderMolde.getObjectName());
		if (null != buliderMolde.getFileList() && buliderMolde.getFileList().size() > 0) {
			for (FileKeyModle item : buliderMolde.getFileList()) {
				builder.field(item.getKey(), item.getValue());
			}
		}
		if (buliderMolde.getChlidObject() != null && buliderMolde.getChlidObject().size() > 0) {
			for (BulidMode item : buliderMolde.getChlidObject()) {
				builder = buliderByObject(item, builder);
			}
		}
		builder.endObject();
		if (endFla) {
			builder.endObject();
		}
		return builder;
	}

	public void searchNews() {

		// QueryBuilders.matchQuery(name, text)
		MultiMatchQueryBuilder builder = QueryBuilders.multiMatchQuery("content99", "title").minimumShouldMatch("30%");

		SearchResponse response = client.prepareSearch("news").setTypes("newsInfo").setQuery(builder).get();
	}

}
