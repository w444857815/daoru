package com.wangzhixuan.mapper;

import java.util.List;
import java.util.Map;

import com.wangzhixuan.model.XsType;



public interface XsTypeMapper extends BaseMapper<XsType>{
	
	/**
	 * 根据实体类条件查询
	* @Title: selectByModel 
	* @param model 实体类
	* @return    设定文件 
	* @return List<XsType>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-24 16:58:46
	 */
	List<XsType> selectByModel(XsType model);
	
	
	/**
	 * 根据map条件修改
	* @Title: updateByMap 
	* @param map 查询条件
	* @return    设定文件 
	* @return Integer    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-24 16:58:46
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
	* @date 2019-01-24 16:58:46
	 */
	Integer deleteByMap(Map<String,Object> map);


	List<XsType> selectAll();


	Integer updateByPrimaryKeySelective(XsType xsType);


	List<XsType> selectBatchIds(List<String> list);
}
