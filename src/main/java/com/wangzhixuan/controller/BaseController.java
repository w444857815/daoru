package com.wangzhixuan.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wangzhixuan.constants.ServiceCodeConstants;
import com.wangzhixuan.service.BaseService;




public class BaseController {

	protected Logger logger = LoggerFactory.getLogger(BaseController.class);

	public Map<String, Object> getPageResult(BaseService ibs, Map<String, Object> map) throws Exception {
		return ibs.queryPageResult(map);
	}
	
	protected Map<String, Object> getFailRtn(String msg) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.FAIL_RTN);
		rtn.put("msg", msg);
		rtn.put("data", null);
		return rtn;
	}
	
	/**
	 * 获取成功返回
	 * @param data
	 * @return
	 * @author tianyunyun
	 * @date 2016年11月4日下午12:15:48
	 */
	protected Map<String, Object> getSuccessRtn(Object data) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", "");
		rtn.put("data", data);
		return rtn;
	}
	
	protected Map<String, Object> getSuccessRtn(Object data,String msg) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", ServiceCodeConstants.SUCCESS_RTN);
		rtn.put("msg", msg);
		rtn.put("data", data);
		return rtn;
	}
	/**
	 * 获取成功返回
	 * @param data
	 * @return
	 * @author tianyunyun
	 * @date 2016年11月4日下午12:15:48
	 */
	protected Map<String, Object> getRtnCode(String msg,Integer code) {
		Map<String, Object> rtn = new HashMap<String, Object>();
		rtn.put("code", code);
		rtn.put("msg", msg);
		rtn.put("data", "");
		return rtn;
	}
}
