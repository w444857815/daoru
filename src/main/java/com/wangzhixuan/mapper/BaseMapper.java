package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T extends Object> {

	int insert(Object obj);
	int insertSelective(Object obj);
    List<T> selectAll();
    T selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Object obj);
    int deleteByPrimaryKey(int id);
    //查询翻页总数
    Long queryCount(Map<String,Object> map);
    //查询翻页结果
    List<T> queryPageResult(Map<String,Object> map);
    int batchDelete(List<Integer> ids);
    int batchInsert(List<T> objs);
    //根据条件查询
    List<T> selectByCondition(Map<String,Object> map) ;
}
