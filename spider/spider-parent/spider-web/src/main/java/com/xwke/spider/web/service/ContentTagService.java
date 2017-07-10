package com.xwke.spider.web.service;

import java.util.List;

import com.xwke.spider.modle.ContentTagModle;

public interface ContentTagService {

	public void addTag(ContentTagModle modle);

	public List<ContentTagModle> getTagList();

	public void updateTag(ContentTagModle modle);

	public ContentTagModle get(Integer id);

	public void deleteTag(ContentTagModle modle);

	public List<ContentTagModle> getTagListByNodeId(Long nodeId);

	public List<ContentTagModle> getUnAddTagListByNodeId(Long nodeId);

	public int delTagnodeRel(Long nodeId, Long tagId);

}
