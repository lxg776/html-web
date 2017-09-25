package com.xwke.spider.web.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.xwke.spider.dao.DataFieldDao;
import com.xwke.spider.dao.DataTableDao;
import com.xwke.spider.elasticsearch.modle.BulidMode;
import com.xwke.spider.elasticsearch.modle.FileKeyModle;
import com.xwke.spider.elasticsearch.service.EsNewsServiceImpy;
import com.xwke.spider.huntsman.util.CommonUtil;
import com.xwke.spider.huntsman.util.RandomUtils;
import com.xwke.spider.modle.DataFieldModle;
import com.xwke.spider.modle.DataTableModle;
import com.xwke.spider.modle.ExecutorModle;
import com.xwke.spider.modle.PageOnterModle;
import com.xwke.spider.vo.DataFieldVo;
import com.xwke.spider.vo.DataTableVo;
import com.xwke.spider.web.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	final int pageSize = 20;
	@Resource
	DataTableDao dataTableDao;
	@Resource
	EsNewsServiceImpy esService;
	@Resource
	DataFieldDao dataFieldDao;

	@Override
	public PageOnterModle getTableListByUserId(long userId, int pageNum) {

		Page<ExecutorModle> pageonter = PageHelper.startPage(pageNum, pageSize);
		List<DataTableModle> list = dataTableDao.getTableListByUserId(userId);
		PageOnterModle page = CommonUtil.getPageOnter(pageonter);
		return page;
		// TODO Auto-generated method stub
	}

	public int createDataField(DataFieldModle fieldModle) {

		return dataFieldDao.addLocal(fieldModle);

	}

	@Override
	public DataTableVo getDataTableById(long id) {

		DataTableModle modle = dataTableDao.getDataById(id);

		DataTableVo vo = modle.getTargetObject(DataTableVo.class);
		// TODO Auto-generated method stub
		return vo;
	}

	@Override
	public int saveDataTableVo(DataTableVo vo) {

		if ("update".equals(vo.getKeyWord())) {
			DataTableModle modle = dataTableDao.getDataById(vo.getId());
			modle.setTableShowName(vo.getTableShowName());
			return dataTableDao.update(modle);
		} else {
			DataTableModle modle = vo.getTargetObject(DataTableModle.class);

			int resultFla = dataTableDao.addLocal(modle);

			BulidMode bulidModle = new BulidMode();
			bulidModle.setObjectName("properties");

			List<BulidMode> chilidList = new ArrayList<>();
			BulidMode item1 = new BulidMode();
			item1.setObjectName("_remarks");
			FileKeyModle keyModle = new FileKeyModle();
			keyModle.setKey("type");
			keyModle.setValue("string");

			List<FileKeyModle> fileList = new ArrayList();
			fileList.add(keyModle);
			item1.setFileList(fileList);
			chilidList.add(item1);
			bulidModle.setChlidObject(chilidList);
			try {
				esService.createMapping("news", modle.getTableName(), bulidModle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultFla;
		}

		// TODO Auto-generated method stub

	}

	@Override
	public List<DataFieldModle> getFieldListByTid(long tid) {

		return dataFieldDao.getFieldsByTid(tid);
		// TODO Auto-generated method stub
	}

	@Override
	public DataFieldVo getDataFieldById(long id) {
		// TODO Auto-generated method stub

		return dataFieldDao.get(id).getTargetObject(DataFieldVo.class);
	}

	@Override
	public int saveDataFieldVo(DataFieldVo vo) {

		if ("update".equals(vo.getKeyWord())) {
			DataFieldModle modle = dataFieldDao.get(vo.getId());
			modle.setShow_field_name(vo.getShow_field_name());
			return dataFieldDao.update(modle);
		} else {

			DataTableModle tableModle = dataTableDao.getDataById(vo.getT_id());
			DataFieldModle modle = vo.getTargetObject(DataFieldModle.class);
			modle.setField_type("string");
			int resultFla = dataFieldDao.addLocal(modle);

			BulidMode bulidModle = new BulidMode();
			bulidModle.setObjectName("properties");

			List<BulidMode> chilidList = new ArrayList<>();
			BulidMode item1 = new BulidMode();
			item1.setObjectName(modle.getField_name());
			FileKeyModle keyModle = new FileKeyModle();
			keyModle.setKey("type");
			keyModle.setValue("string");
			List<FileKeyModle> fileList = new ArrayList();
			fileList.add(keyModle);
			item1.setFileList(fileList);
			chilidList.add(item1);
			bulidModle.setChlidObject(chilidList);
			try {
				esService.createMapping("news", tableModle.getTableName(), bulidModle);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return resultFla;
		}

		// TODO Auto-generated method stub

	}

}
