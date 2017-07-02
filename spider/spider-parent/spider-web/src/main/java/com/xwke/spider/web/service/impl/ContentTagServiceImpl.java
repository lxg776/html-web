package com.xwke.spider.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dexcoder.dal.JdbcDao;
import com.dexcoder.dal.build.Criteria;
import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.web.service.ContentTagService;

@Service
public class ContentTagServiceImpl implements ContentTagService {

	@Resource
	JdbcDao jdbcDao;

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
	public ContentTagModle get(Long id) {
		return jdbcDao.get(ContentTagModle.class, id);
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

}
