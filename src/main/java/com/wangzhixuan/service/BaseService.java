package com.wangzhixuan.service;

import java.util.List;
import java.util.Map;

public interface BaseService <T extends Object>{
	//翻页查询总数
	public Long queryCount(Map<String,Object> map);
	//翻页查询结果
	public Map<String, Object> queryPageResult(Map<String,Object> map);
	//查询所有列表
	public List<T> selectAll();
	//根据id查询
	public T selectById(Integer id);
	
	public int insert(T obj);
	
	public int insertSelective(T obj);
	//修改
	public int update(T obj);
	
	public int deleteByPrimaryKey(int id);
	//批量删除
	void batchDelete(List<Integer> ids);
	//根据条件查询
	public List<T> selectByCondition(Map<String, Object> map);
	
	public int updateByPrimaryKeySelective(T obj);
}
