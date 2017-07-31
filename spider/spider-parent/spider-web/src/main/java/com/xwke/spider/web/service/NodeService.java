package com.xwke.spider.web.service;

import java.util.List;

import javax.xml.soap.Node;

import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.vo.NodeVo;

public interface NodeService {

	/**
	 * 新增节点
	 */
	public void addRootNode(NodeVo nodeModle);

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
	public void addChildNode(long fid, int node_level, List<NodeModle> childList);

	/**
	 * 新增标签
	 */
	public void addChildNode(long fid, int tagId);

	/**
	 * 获取全部节点
	 */
	public List<NodeVo> getAllNode();

	public NodeVo getNodeByid(Long id);

	/**
	 * 更新节点信息
	 */
	public int updateNode(NodeModle model);
	
	/**
	 * 添加标签
	 * @param tagId
	 * @return
	 */
	public int addTag(Long tagId, Long nodeId);

	public List<NodeVo> getNodeList();

}
