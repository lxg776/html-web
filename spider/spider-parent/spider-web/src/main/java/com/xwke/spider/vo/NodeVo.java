package com.xwke.spider.vo;

import java.util.List;

import com.xwke.base.core.annotation.po.FieldName;
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
	@FieldName(name = "f_id")
	private long fid;

	/**
	 * 子列表
	 */
	private List<NodeVo> childList;

	public long getFid() {
		return fid;
	}

	public void setFid(long fid) {
		this.fid = fid;
	}

	public List<NodeVo> getChildList() {
		return childList;
	}

	public void setChildList(List<NodeVo> childList) {
		this.childList = childList;
	}

	

}
