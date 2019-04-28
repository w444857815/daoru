package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.XsDicContent;



public interface XsDicContentMapper extends BaseMapper<XsDicContent>{
	
	/**
	 * 根据实体类条件查询
	* @Title: selectByModel 
	* @param model 实体类
	* @return    设定文件 
	* @return List<XsDicContent>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-26 14:23:50
	 */
	List<XsDicContent> selectByModel(XsDicContent model);
	
	/**
	 * 根据map条件查询
	* @Title: selectByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-26 14:23:50
	 */
	List<Map<String,Object>> selectByMap(Map<String,Object> map);
	
	/**
	 * 根据map条件修改
	* @Title: updateByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-26 14:23:50
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
	* @date 2019-01-26 14:23:50
	 */
	Integer deleteByMap(Map<String,Object> map);

	void insertBatchList(List<XsDicContent> list);
}
