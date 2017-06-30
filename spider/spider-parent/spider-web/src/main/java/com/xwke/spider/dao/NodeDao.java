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


}
