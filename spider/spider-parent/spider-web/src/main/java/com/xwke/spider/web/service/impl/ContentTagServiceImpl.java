package com.xwke.spider.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dexcoder.dal.JdbcDao;
import com.dexcoder.dal.build.Criteria;
import com.xwke.spider.dao.ContentTagDao;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.web.service.ContentTagService;

@Service
public class ContentTagServiceImpl implements ContentTagService {

	@Resource
	JdbcDao jdbcDao;
	@Resource
	ContentTagDao contentTagDao;

	@Override
	public void addTag(ContentTagModle modle) {
		// TODO Auto-generated method stub
		// jdbcDao.insert(modle);
		// jdbcDao.insert(Criteria.insert(modle.getClass()).tableAlias("s_content_tag"));
		Criteria criteria = Criteria.insert(ContentTagModle.class).into("tag_name", modle.getTagName()).into("remarks",
				modle.getRemarks());
		jdbcDao.insert(criteria);

		// Criteria.insert(ContentTagModle.class).ta
		// Criteria.select(Table.class).tableAlias("t")
	}

	@Override
	public void updateTag(ContentTagModle modle) {
		jdbcDao.update(modle);
	}

	@Override
	public ContentTagModle get(Integer id) {
		return jdbcDao.get(ContentTagModle.class, new Long(id));
	}

	@Override
	public List<ContentTagModle> getTagListByNodeId(Long nodeId) {
		String refSql = String.format(
				"select tag.id,tag.tag_name as tagName ,tag.remarks from s_content_tag as tag join  (select id, tag_id,node_id,status from s_node_tag_relation where node_id =%d) as rel on rel.tag_id= tag.id",
				nodeId);
		List<Map<String, Object>> resultList = contentTagDao.listBySql(refSql);
		List<ContentTagModle> tagList = new ArrayList();
		if (null != resultList && resultList.size() > 0) {
			for (Map<String, Object> map : resultList) {
				ContentTagModle modle = CommonUtil.injectBean(ContentTagModle.class, map);
				tagList.add(modle);
			}
		}
		return tagList;
	}

	@Override
	public int delTagnodeRel(Long nodeId, Long tagId) {
		String refSql = String.format("delete from s_node_tag_relation where tag_id = %d and node_id = %d", tagId,
				nodeId);
		return contentTagDao.excuse(refSql);

	}

	@Override
	public List<ContentTagModle> getUnAddTagListByNodeId(Long nodeId) {
		String refSql = String.format(
				"select tag.id,tag.tag_name as tagName ,tag.remarks from s_content_tag as tag where tag.id not in (select tag_id from s_node_tag_relation where node_id = %d)",
				nodeId);
		List<Map<String, Object>> resultList = contentTagDao.listBySql(refSql);
		List<ContentTagModle> tagList = new ArrayList();
		if (null != resultList && resultList.size() > 0) {
			for (Map<String, Object> map : resultList) {
				ContentTagModle modle = CommonUtil.injectBean(ContentTagModle.class, map);
				tagList.add(modle);
			}
		}
		return tagList;
	}

	@Override
	public List<ContentTagModle> getTagList() {
		// TODO Auto-generated method stub
		// jdbcDao.insert(modle);
		// jdbcDao.insert(Criteria.insert(modle.getClass()).tableAlias("s_content_tag"));
		String[] fileName = { "id", "tagName", "remarks" };
		Criteria criteria = Criteria.select(ContentTagModle.class).include(fileName);
		// jdbcDao(criteria);
		List<ContentTagModle> tags = jdbcDao.queryList(criteria);
		return tags;
	}

	@Override
	public void deleteTag(ContentTagModle modle) {
		// TODO Auto-generated method stub
		jdbcDao.delete(ContentTagModle.class, new Long(modle.getId()));

	}

}
