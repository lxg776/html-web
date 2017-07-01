package com.xwke.spider.web.service;

import java.util.List;

import javax.xml.soap.Node;

import com.xwke.spider.modle.NodeModle;

public interface NodeService {

	/**
	 * 新增节点
	 */
	public void addNode(NodeModle nodeModle);

	/**
	 * 编辑节点
	 */
	public void editNode(NodeModle nodeModle);

	/**
	 * 删除节点
	 */
	public void deleteNode(NodeModle nodeModle);

	/**
	 * 新增子点
	 */
	public void addChildNode(long fid, List<Node> childList);

	/**
	 * 新增标签
	 */
	public void addChildNode(long fid, int tagId);

}
