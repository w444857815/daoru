package com.wangzhixuan.mapper;

import com.wangzhixuan.model.DbTableone;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
  *  Mapper 接口
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
public interface DbTableoneMapper extends BaseMapper<DbTableone> {
	/**
	 * 查找所有条数
	 * @return
	 */
	int selectAllCount();
//	执行sql查询，查出来里面有多少条重复
	List<String> selectExistNums(@Param("list")List<String> keylist);
	/*
	 *更新数据 
	 */
	int updateByIdCard(DbTableone dbTableone);
	/**
	 * 批量更新
	 * @param updateList
	 * @return
	 */
	int updateBatchByIdCard(List<DbTableone> updateList);

}