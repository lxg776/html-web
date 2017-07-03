package com.xwke.spider.web.service;

import java.util.List;

import com.xwke.spider.modle.ContentTagModle;

public interface ContentTagService {

	public void addTag(ContentTagModle modle);

	List<ContentTagModle> getTagList();

	void updateTag(ContentTagModle modle);

	ContentTagModle get(Long id);

	void deleteTag(ContentTagModle modle);

}
