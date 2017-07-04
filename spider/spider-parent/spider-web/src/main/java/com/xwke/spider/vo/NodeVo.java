package com.xwke.spider.vo;

import java.util.List;
import com.xwke.spider.modle.NodeModle;

public class NodeVo extends NodeModle {
	// 等级
	private int node_level;

	public int getNode_level() {
		return node_level;
	}

	public void setNode_level(int node_level) {
		this.node_level = node_level;
	}

	/**
	 * 父id
	 */
	private NodeModle fatherNode;

	/**
	 * 子列表
	 */
	private List<NodeModle> childList;

	public NodeModle getFatherNode() {
		return fatherNode;
	}

	public void setFatherNode(NodeModle fatherNode) {
		this.fatherNode = fatherNode;
	}

	public List<NodeModle> getChildList() {
		return childList;
	}

	public void setChildList(List<NodeModle> childList) {
		this.childList = childList;
	}

}
