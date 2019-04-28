package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * 约约2.5框架结构试用版
 */
public interface IBaseMapper<T> {

    int save(T t);

    int saveBatch(List<T> list);

    int updateByPrimaryKey(T record);

    int update(@Param("valueMap") Map<String, Object> valueMap, @Param("conditionMap") Map<String, Object> conditionMap);

    int removeByPrimaryKey(String uuid);

    int remove(Map<String, Object> map);

    int count(Map<String, Object> map);

    T selectByPrimaryKey(String uuid);

    List<T> select(Map<String, Object> map);

}
