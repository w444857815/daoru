package com.wangzhixuan.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.wangzhixuan.model.XsBook;
import com.wangzhixuan.model.XsType;
import com.wangzhixuan.service.XsBookFacade;
import com.wangzhixuan.service.XsTypeFacade;
import com.wangzhixuan.util.pachong.axdzs.AiXiaWang;


/**
 * XsType
 * XsType
 * <p>Title:XsTypeController </p>
 * <p>Description:XsType </p>
 * <p>Company: </p> 
 * @author wangruwei
 * @date 2019-01-24 16:58:46
 */
@Controller
@RequestMapping(value = "/xstypecon")
public class XsTypeController extends BaseController {
	
	@Autowired
	private XsTypeFacade xsTypeFacade;
	
	@Autowired
	private XsBookFacade xsBookFacade;
	/**
	 * 跳转到列表页
	* @Title: index 
	* @return    设定文件 
	* @return modelAndView   返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-24 16:58:46
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/admin/xs/typesindex1");
		List<XsType> list = xsTypeFacade.selectAll();
		modelAndView.addObject("typeList", list);
		return modelAndView;
	}
	
	/**
	 * @Title:XsTypeController.java
	 * @Package com.wangzhixuan.controller
	 * @Description:获取类型列表
	 * @author wangruwei
	 * @date 2019年1月24日下午5:11:58
	 * @return
	 * @version V1.0
	 * @throws Exception 
	 */
	@RequestMapping(value="/gettypeslist")
	@ResponseBody
	public Map<String,Object> getTypesList(String status) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		if(!"".equals(status)){
			map.put("status", status);
		}
		List<XsType> list = xsTypeFacade.selectByCondition(map);
		return getSuccessRtn(list, "查询成功");
	}
	
	
	
	/**
	 * 
	 * @Title:XsTypeController.java
	 * @Package com.wangzhixuan.controller
	 * @Description:获取书的列表,这个时间比较久,注意，只是获取书的信息，目录信息下一步接着来，不能这一步搞
	 * @author wangruwei
	 * @date 2019年1月24日下午5:52:33
	 * @return
	 * @version V1.0
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws MalformedURLException 
	 * @throws FailingHttpStatusCodeException 
	 * @throws InterruptedException 
	 */
	@RequestMapping(value="/getbookslist")
	@ResponseBody
	public Map<String,Object> getBooksList(String typsIds) throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException, InterruptedException{
		//获取到类型集合
		List<XsType> typesList = xsTypeFacade.selectBatchIds(Arrays.asList(typsIds.split(",")));
		
		//循环取到每一个类型的实体
		for (int i = 0; i < typesList.size(); i++) {
			XsType unitType = typesList.get(i);
			/**
			 * 删除这个类别下面的书重新爬
			 */
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("typeId", unitType.getId());
			xsBookFacade.deleteByMap(map);
			
			//然后开始获取下面的书籍
			//在这里就处理完url，方法里只是用这里的url解析而已
			String typeUrl = unitType.getGetUrl();
			//已完结的url
			String endUrl = typeUrl.replace("index.html", "index_0_2_0_");
			//连载中的url
			String ingUrl = typeUrl.replace("index.html", "index_0_1_0_");
			String typeId = unitType.getId()+"";
			String typeName = unitType.getTypeName();
			
			try {
				for (int j = 1; j < 8; j++) {
					String getendUrl = endUrl + j + ".html";
					List<XsBook> endbooks = AiXiaWang.getBooks(getendUrl,typeId,typeName);
					if(endbooks.size()>0){
						xsBookFacade.insertBatchList(endbooks);
					}
					String getingUrl = ingUrl + j + ".html";
					List<XsBook> ingbooks = AiXiaWang.getBooks(getingUrl,typeId,typeName);
					if(ingbooks.size()>0){
						xsBookFacade.insertBatchList(ingbooks);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				//如果出问题，就把出问题的这个数的类别id传出去，让知道是哪出现问题了
				
				return getFailRtn("获取错误，请重新爬取。出错的是："+typeName+" 类，\n请重新从这个类开始获取"+e.getMessage());
			}
			unitType.setStatus("1");
			xsTypeFacade.updateByPrimaryKeySelective(unitType);
		}
		
		
		return getSuccessRtn("","获取成功，本次获取"+typsIds.split(",").length*2*7*30);
	}
	
	
	/*
	*//**
	 * 加载分页数据
	* @Title: ajaxDataList 
	* @param model 实体类
	* @return    设定文件 
	* @return List<XsType>    返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-24 16:58:46
	 *//*
	@RequestMapping(value="/ajaxdataList")
	@ResponseBody
	public Map<String,Object> ajaxDataList(HttpServletRequest request){
//		Map param = HttpUtil.getRequestPararms(request);
//		return getPageResult(appointmentFacade, param);
	}
	
	*//**
	 * 跳转到添加或修改页
	* @Title: index 
	* @Param id 主键id
	* @return    设定文件 
	* @return modelAndView   返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-24 16:58:46
	 *//*
	@RequestMapping(value = "/getinfo")
	public ModelAndView getInfo(Integer id,ModelAndView mav) {
		ModelAndView mav = new ModelAndView("/admin/package/" + url);
		if(!CheckUtil.isEmpty(id)&&id > 0){//修改，查询model
			Map<String,Object> param = new HashMap<>();
			param.put("id",id);
			XsType model = xsTypeFacade.selectByCondition(param);
			mav.addObject("model", model);
			mav.setViewName("/admin/package/update");
		}else{
			mav.setViewName("/admin/package/save");
		}
		return mav;
	} 
	
	
	
	@RequestMapping(value = "/saveorupdate")
	public ModelAndView saveOrUpdate(@ModelAttribute("model") XsType model,ModelAndView mav) {
		xsTypeFacade.saveOrUpdate(model);
		mav.setViewName("redirect:/package/index");
		return mav;
	}
	
	
	*//**
	 * 添加或修改
	* @Title: saveOrUpdate 
	* @Param model 实体
	* @return    设定文件 
	* @return modelAndView   返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-24 16:58:46
	 *//*
	@RequestMapping(value="/ajaxdelete",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> ajaxDelete(String[] ids){
		xsTypeFacade.batchDelete(ids);
		return getSuccessRtn("","删除成功");
	}*/
}

