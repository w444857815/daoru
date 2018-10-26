package com.wangzhixuan.mapper;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

	void truncateTable(@Param("tableName")String tableName);
	/**
	 * 查出四个表的各条数
	 * @Title:DbConfigMapper.java
	 * @Package com.wangzhixuan.mapper
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2018年10月26日上午11:13:42
	 * @param loginName
	 * @return
	 * @version V1.0
	 */
	List<Integer> selectEveryCount(@Param("loginName")String loginName);
	/**
	 * 获取对比后的数据 
	 * @Title:DbConfigMapper.java
	 * @Package com.wangzhixuan.mapper
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2018年10月26日下午2:38:43
	 * @param csmap
	 * @return
	 * @version V1.0
	 */
	List<DbConfigTable> selectdbTablesData(Map<String, Object> csmap);
	
	
	
}