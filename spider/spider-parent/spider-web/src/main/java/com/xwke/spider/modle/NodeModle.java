package com.xwke.spider.modle;

import java.util.List;

import com.xwke.base.core.annotation.po.FieldName;
import com.xwke.base.core.annotation.po.TableName;

/**
 * 节点实体
 * 
 * @author Administrator
 *
 */
@TableName(name = "s_news_node")
public class NodeModle extends BaseModle {

	private long id;

	
	/**
	 * 节点名称
	 */
	@FieldName(name = "node_name")
	private String nodeName;

	
	/**
	 * 节点名称
	 */
	private int sort;

	/**
	 * 父id
	 */
	private NodeModle fatherNode;

	/**
	 * 子列表
	 */
	private List<NodeModle> childList;

	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

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
