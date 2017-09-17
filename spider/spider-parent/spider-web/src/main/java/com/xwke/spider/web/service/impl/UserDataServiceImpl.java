package com.xwke.spider.web.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.spider.dao.DataTableDao;
import com.xwke.spider.elasticsearch.service.EsNewsServiceImpy;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.modle.DataTableModle;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.DataTableVo;
import com.xwke.spider.web.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	final int pageSize = 20;
	@Resource
	DataTableDao dataTableDao;
	@Resource
	EsNewsServiceImpy esService;
	

	@Override
	public PageOnterModle getTableListByUserId(long userId, int pageNum) {

		Page<ExecutorModle> pageonter = PageHelper.startPage(pageNum, pageSize);
		List<DataTableModle> list = dataTableDao.getTableListByUserId(userId);
		PageOnterModle page = CommonUtil.getPageOnter(pageonter);
		return page;
		// TODO Auto-generated method stub
	}

	@Override
	public DataTableVo getDataTableById(long id) {

		DataTableModle modle = dataTableDao.get(id);
		DataTableVo vo = modle.getTargetObject(DataTableVo.class);
		// TODO Auto-generated method stub
		return vo;
	}

	@Override
	public int saveDataTableVo(DataTableVo vo) {

		if ("update".equals(vo.getKeyWord())) {
			DataTableModle modle = dataTableDao.get(vo.getId());
			modle.setTableShowName(vo.getTableShowName());
			return dataTableDao.update(modle);
		} else {
			DataTableModle modle = vo.getTargetObject(DataTableModle.class);

			return dataTableDao.addLocal(modle);
		}

		// TODO Auto-generated method stub

	}

}
