package com.xwke.base.core.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xwke.base.core.beans.Po;
import com.xwke.base.core.beans.WherePrams;
import com.xwke.base.core.util.Formatter;


public interface BaseDao<T extends Po, PK extends Serializable> {

	public int addLocal(T po);
	
	
	public int add(T po);
	
	
//	public int add(List<T> pos);
	
	
	public T get(PK id);
	
	
	public Serializable getField(PK id, String fileName);

	
	public T get(WherePrams where);
	
	public Serializable getFile(WherePrams where, String fileName);
	
	
	public List<T> list(WherePrams where);
	
	public Serializable[] listFile(WherePrams where, String fileName);
	
	public List<Map<String, Serializable>> listFiles(WherePrams where, String[] files);
	
	
	public int updateLocal(T po);
	
	public int update(T po);
	
	public int updateLocal(T po, WherePrams where);
	
	
	public int update(T po, WherePrams where);
	
	
	public int del(PK id);
	
	public int del(WherePrams where);
	
	
	public List<Map<String, Object>> listBySql(String sql);
	
	public int excuse(String sql);
	
	public long count(WherePrams where);
	
	public long size();
	
	public boolean isExist(T po);
	
	
	public boolean isExist(WherePrams where);
	
	
	public List<T> in(String fileName, Serializable[] values);
	
	
	public long nextId();
	
	
	public List<T> listFormat(WherePrams where, Formatter fmt);


	public List<T> getlistBySql(String sql);
}
