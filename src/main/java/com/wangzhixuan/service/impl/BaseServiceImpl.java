package com.wangzhixuan.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangzhixuan.constants.ServiceCodeConstants;
import com.wangzhixuan.mapper.BaseMapper;
import com.wangzhixuan.service.BaseService;





public abstract class BaseServiceImpl<T extends Object, D extends BaseMapper<T>> implements BaseService<T> {
	final static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);
	
	// 分页map中传的当前页的key
	final String grid_page = "page";
	// 分页map中传的size的key值
	final String grid_rows = "size";
	// xml中传入的当前页key
	final String curRow = "curRow";
	// xml中limit第二个参数key
	final String limitSize = "limitSize";
	// 默认当前页
	final int defaultPage = 1;
	// 默认每页size
	final String defaultRows = "20";

	public abstract D getMapper() ;
	
	
	public int insert(T obj)  {
		return this.getMapper().insert(obj);
	}

	public int insertSelective(T obj)  {
		return this.getMapper().insertSelective(obj);
	}
	
	public int deleteByPrimaryKey(int id)  {
		return this.getMapper().deleteByPrimaryKey(id);
	}
	
	/**
	 * 查询总数
	 */
	public Long queryCount(Map<String, Object> map)  {
		return this.getMapper().queryCount(map);
	}

	/**
	 * 分页查询
	 */
	public Map<String, Object> queryPageResult(Map<String, Object> map)  {
		// 判断当前页、每页size是否取到，若没取到则赋初始值
		int page =  Integer.parseInt((map.get(grid_page) != null ? map.get(grid_page) : defaultPage).toString());
		int size = Integer.parseInt((map.get(grid_rows) != null ? map.get(grid_rows) : defaultRows).toString());
		map.put(curRow, (page - 1) * size);
		map.put(limitSize, size);
		// 查询总数
		Long count = queryCount(map);
		// 计算总页数
		int totalPage = 1;
		if (null != count && count > 0) {
			if (count % size == 0) {
				totalPage = (int) (count / size);
			} else {
				totalPage = (int) ((count / size) + 1);
			}
		}
		// 查询数据
		List<T> datas = this.getMapper().queryPageResult(map);
		// 封装返回结果，结果总数+结果
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("curPage", page);
		result.put("total", count);
		result.put("data", datas);
		result.put("totalPage", totalPage);
		return result;
	}

	/**
	 * 查询全部
	 */
	public List<T> selectAll()  {
		return this.getMapper().selectAll();
	}

	/**
	 * 根据id查询
	 */
	public T selectById(Integer id)  {
		if (null != id) {
			return this.getMapper().selectByPrimaryKey(id);
		}
		return null;
	}

	/**
	 * 更新
	 */
	public int update(T obj)  {

		return this.getMapper().updateByPrimaryKeySelective(obj);
	}

	/**
	 * 批量删除
	 */
	public void batchDelete(List<Integer> ids)  {
		this.getMapper().batchDelete(ids);
	}

	/**
	 * 根据条件查询
	 */
	public List<T> selectByCondition(Map<String, Object> map)  {
		return this.getMapper().selectByCondition(map);
	}


	/**
	 * 获取失败的返回内容
	 * 
	 * @param msg
	 * @author chenrui
	 * @return
	 */
	protected Map<String, Object> getFailRtn(String msg) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.FAIL_RTN);
		rtn.put("msg", msg);
		rtn.put("data", null);
		return rtn;
	}
	
	/**
	 * 获取成功的返回内容
	 * 
	 * @param data
	 * @author chenrui
	 * @return
	 */
	protected Map<String, Object> getSussRtn(Object data) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", "");
		rtn.put("data", data);
		return rtn;
	}
	/**
	 * 获取成功的返回内容
	 * 
	 * @param data
	 * @author chenrui
	 * @return
	 */
	protected Map<String, Object> getSussRtn(Object data,String msg) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", msg);
		rtn.put("data", data);
		return rtn;
	}
	
	/**
	 * 具体业务状态码返回体封装方法
	 * @return
	 * @date 2016年12月2日
	 * @author tianyunyn
	 */
	protected Map<String, Object> getRtnCode(Object data,String msg,Integer code) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", code);
		rtn.put("msg", msg);
		rtn.put("data", data);
		return rtn;
	}
	/**
	 * 具体业务状态码返回体封装方法
	 * @return
	 * @date 2016年12月2日
	 * @author tianyunyn
	 */
	protected Map<String, Object> getRtnCode(String msg,Integer code) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", code);
		rtn.put("msg", msg);
		rtn.put("data", null);
		return rtn;
	}
	
	
    public String replaceKey(String key,String regex,String value){
    	return key.replaceAll(regex, value==null ? "" : value);
    }
    
    public int updateByPrimaryKeySelective(T obj) {
    	return this.getMapper().updateByPrimaryKeySelective(obj);
    }
 
}
