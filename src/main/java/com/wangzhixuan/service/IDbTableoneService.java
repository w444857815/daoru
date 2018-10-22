package com.wangzhixuan.service;

import com.wangzhixuan.model.DbTableone;

import java.util.List;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
public interface IDbTableoneService extends IService<DbTableone> {
	/**
	 * 查找所有条数
	 * @return
	 */
	int selectAllCount();
	/**
	 * 执行sql查询，查出来里面有多少条重复
	 * @param keylist
	 * @return
	 */
	List<String> selectExistNums(List<String> keylist);
	/**
	 * 更新数据
	 * @param dbTableone
	 */
	int updateByIdCard(DbTableone dbTableone);
	/**
	 * 批量更新
	 * @param updateList
	 * @return
	 */
	int updateBatchByIdCard(List<DbTableone> updateList);
	
}
