package com.xwke.spider.web.service.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.xml.soap.Node;

import org.springframework.stereotype.Service;

import com.xwke.spider.dao.NodeDao;
import com.xwke.spider.modle.NodeModle;
import com.xwke.spider.web.service.NodeService;

@Service
public class NodeServiceImpl implements NodeService {

	@Resource
	NodeDao nodeDao;

	@Override
	public void addNode(NodeModle nodeModle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void editNode(NodeModle nodeModle) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addChildNode(long fid, List<Node> childList) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteNode(NodeModle nodeModle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addChildNode(long fid, int tagId) {
		// TODO Auto-generated method stub
		
	}

	

}
