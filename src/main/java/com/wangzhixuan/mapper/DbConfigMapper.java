package com.wangzhixuan.mapper;

import java.util.LinkedList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.wangzhixuan.model.DbConfigTable;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
public interface DbConfigMapper extends BaseMapper<DbConfigTable> {
	/**
	 * 批量插入动态表
	 * @param colNumsList 
	 */
	void insertBatch(@Param("list")List<DbConfigTable> entityList,@Param("tableName") String tableName,@Param("colNumsList")  LinkedList<Integer> colNumsList);
	
	
	
}