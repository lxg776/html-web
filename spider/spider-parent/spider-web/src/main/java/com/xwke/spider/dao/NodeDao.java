package com.xwke.spider.dao;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.vo.NodeVo;

@Repository
public class NodeDao extends DaoImpl<NodeModle, Serializable> {
	@Resource
	private SqlSessionTemplate sqlSessionTemplateASS;

	public long getCountNodeRel(Long fid, Long cid) {
		boolean fla = false;
		String sql = "select count(1) from s_node_relation where f_id = %d and c_id = %d ";
		sql = String.format(sql, fid, cid);
		return sqlSessionTemplateASS.selectOne("getCountNodeRel", sql);
	}

	public NodeVo getNodeVoByid(Long id) {
		String sql = String.format(
				"select node.id, node.node_name as nodeName,node.sort,rel.f_id as fid , rel.node_level from s_news_node as node  join  s_node_relation as rel on node.id = rel.c_id and node.id = %d;",
				id);

		List<Map<String, Object>> selectList = listBySql(sql);
		if (selectList.size() > 0) {
			Map<String, Object> map = selectList.get(0);
			NodeVo vo = CommonUtil.injectBean(NodeVo.class, map);
			return vo;
		}
		return null;
	}

	/*
	 * 添加子节点与父节点的关系
	 */
	public int addRel(Long fid, Long cid, int node_level) {
		String sql = String.format("insert into s_node_relation(f_id,c_id,node_level)values(%d,%d,%d)", fid, cid,
				node_level);
		return excuse(sql);
	}

	public List<NodeVo> getAllNode() {

		String sql = String.format(
				"select distinct  node.id, node.node_name as nodeName,node.sort,rel.f_id as fid ,rel.node_level from s_news_node as node  join  s_node_relation as rel on node.id = rel.c_id order by node.id desc");
		List<Map<String, Object>> selectList = listBySql(sql);
		List<NodeVo> list = new ArrayList<>();
		for (Map<String, Object> map : selectList) {
			NodeVo vo = CommonUtil.injectBean(NodeVo.class, map);
			list.add(vo);
		}

		Map<Long, List<NodeVo>> cacheMap = new HashMap();
		List<NodeVo> rootList = new ArrayList();

		for (NodeVo vo : list) {
			long fid = vo.getFid();

			// 找寻子节点
			long cid = vo.getId();
			if (cacheMap.containsKey(cid)) {
				vo.setChildList(cacheMap.get(cid));
			}
			// 属于父节点
			List<NodeVo> upList;
			if (cacheMap.containsKey(fid)) {
				upList = cacheMap.get(fid);
			} else {
				upList = new ArrayList();
			}
			if (vo.getId() != vo.getFid()) {
				upList.add(vo);
			}

			cacheMap.put(fid, upList);
			if (vo.getNode_level() == 0) {
				rootList.add(vo);
			}

		}
		return rootList;
	}

}
