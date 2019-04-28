package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.XsBook;



public interface XsBookMapper extends BaseMapper<XsBook>{
	
	/**
	 * 根据实体类条件查询
	* @Title: selectByModel 
	* @param model 实体类
	* @return    设定文件 
	* @return List<XsBook>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-25 16:00:40
	 */
	List<XsBook> selectByModel(XsBook model);
	
	
	/**
	 * 根据map条件修改
	* @Title: updateByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-25 16:00:40
	 */
	Integer updateByMap(Map<String,Object> map);
	
	/**
	 * 根据map条件删除
	* @Title: deleteByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-25 16:00:40
	 */
	Integer deleteByMap(Map<String,Object> map);


	void insertBatchList(List<XsBook> list);


	List<XsBook> selectBatchIds(List<String> list);
}
