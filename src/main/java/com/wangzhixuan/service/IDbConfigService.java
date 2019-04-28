package com.wangzhixuan.service;

import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.model.DbTableone;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ruwei.wang
 * @since 2018-10-18
 */
public interface IDbConfigService extends IService<DbConfigTable> {
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
	
	/**
	 * 
	 * @Title批量插入动态表
	 * @Package com.wangzhixuan.service
	 * @author wangruwei
	 * @date 2018年10月25日下午5:08:19
	 * @param entityList
	 * @param tableName
	 * @version V1.0
	 * @param colNumsList 
	 */
	void insertBatch(List<DbConfigTable> entityList, String tableName, LinkedList<Integer> colNumsList);
	/**
	 * 删表信息
	 * @Title:IDbConfigService.java
	 * @Package com.wangzhixuan.service
	 * @author wangruwei
	 * @date 2018年10月26日上午10:56:43
	 * @version V1.0
	 * @param tableEnd 
	 */
	void truncateTable(String tableEnd);
	List<Integer> selectEveryCount(String loginName);
	/**
	 * 查询对比出来的数据
	 * @Title:IDbConfigService.java
	 * @Package com.wangzhixuan.service
	 * @author wangruwei
	 * @date 2018年10月26日下午2:38:02
	 * @param csmap
	 * @return
	 * @version V1.0
	 */
	List<DbConfigTable> selectdbTablesData(Map<String, Object> csmap);
	
	/**
	 * 按表头有多少个来查找多少列
	 * @Title:IDbConfigService.java
	 * @Package com.wangzhixuan.service
	 * @author wangruwei
	 * @date 2018年12月4日下午3:43:27
	 * @param csmap
	 * @return
	 * @version V1.0
	 */
	List<DbConfigTable> selectExampleLimitData(Map<String, Object> csmap);
	
}
