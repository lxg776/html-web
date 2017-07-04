package com.xwke.spider.dao;

import java.io.Serializable;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.xwke.base.core.dao.DaoImpl;
import com.xwke.spider.modle.NodeModle;

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

	/*
	 * 添加子节点与父节点的关系 
	 */
	public int addRel(Long fid, Long cid, int node_level) {
		String sql = String.format("insert into s_node_relation(f_id,c_id,node_level)values(%d,%d,%d)", fid, cid,
				node_level);
		return excuse(sql);
	}
	
	
}
