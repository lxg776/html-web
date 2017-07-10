package com.xwke.spider.web.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.soap.Node;

import org.springframework.stereotype.Service;

import com.xwke.spider.dao.NodeDao;
import com.xwke.spider.modle.ContentTagModle;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.vo.ContentTagVo;
import com.xwke.spider.vo.NodeVo;
import com.xwke.spider.web.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

	@Resource
	NodeDao nodeDao;

	/**
	 * 添加根节点
	 */
	@Override
	public void addRootNode(NodeVo nodeVo) {
		// TODO Auto-generated method stub
		NodeModle modle = nodeVo.getTargetObject(NodeModle.class);
		nodeDao.addLocal(modle);
		if (nodeVo.getNode_level() == 0) {
			addRelation(modle.getId(), modle.getId(), 0);
		}
	}

	@Override
	public void editNode(NodeModle nodeModle) {
		// TODO Auto-generated method stub
		nodeDao.update(nodeModle);

	}

	@Override
	public void addChildNode(long fid, int node_level, List<NodeModle> childList) {
		// TODO Auto-generated method stub
		NodeModle nodle = nodeDao.get(fid);
		// nodle.setChildList(childList);
		for (NodeModle childNode : childList) {
			nodeDao.addLocal(childNode);
			addRelation(fid, childNode.getId(), node_level);
		}
	}

	/**
	 * 添加关系
	 * 
	 * @param fid
	 * @param cid
	 */
	private void addRelation(long fid, long cid, int node_level) {
		if (nodeDao.getCountNodeRel(fid, cid) <= 0) {
			nodeDao.addRel(fid, cid, node_level);
		}
	}

	@Override
	public void deleteNode(NodeModle nodeModle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildNode(long fid, int tagId) {
		// TODO Auto-generated method stub

	}

	/**
	 * 获取所以节点
	 * 
	 * @param fid
	 * @param cid
	 * 
	 *            获取所以节点 在进行组装
	 */
	@Override
	public List<NodeVo> getAllNode() {
		// TODO Auto-generated method stub
		return nodeDao.getAllNode();
	}

	@Override
	public NodeVo getNodeByid(Long id) {
		// TODO Auto-generated method stub

		return nodeDao.getNodeVoByid(id);
	}

	@Override
	public int updateNode(NodeModle model) {
		// TODO Auto-generated method stub

		return nodeDao.update(model);
	}

	@Override
	public int addTag(Long tagId, Long nodeId) {
		// TODO Auto-generated method stub
		return nodeDao.addTag(tagId, nodeId, ContentTagVo.STATUS_WORK);
	}

}
