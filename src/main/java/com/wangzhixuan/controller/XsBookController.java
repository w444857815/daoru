package com.wangzhixuan.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.wangzhixuan.model.XsBook;
import com.wangzhixuan.model.XsDicContent;
import com.wangzhixuan.model.XsType;
import com.wangzhixuan.service.XsBookFacade;
import com.wangzhixuan.service.XsDicContentFacade;
import com.wangzhixuan.service.XsTypeFacade;
import com.wangzhixuan.util.BreakList;
import com.wangzhixuan.util.pachong.axdzs.AiXiaWang;


/**
 * XsBook
 * XsBook
 * <p>Title:XsBookController </p>
 * <p>Description:XsBook </p>
 * <p>Company: </p> 
 * @author wangruwei
 * @date 2019-01-25 16:00:40
 */
@Controller
@RequestMapping(value = "/xsbooks")
public class XsBookController extends BaseController {
	
	@Autowired
	private XsDicContentFacade xsDicContentFacade;
	@Autowired
	private XsBookFacade xsBookFacade;
	
	@Autowired
	private XsTypeFacade xsTypeFacade;
	
	
	/**
	 * 跳转到列表页
	* @Title: index 
	* @return    设定文件 
	* @return modelAndView   返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-25 16:00:40
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/admin/xs/typestobookindex2");
		Map<String,Object> map = new HashMap<String,Object>();
		List<XsType> list = xsTypeFacade.selectByCondition(map);
		modelAndView.addObject("typeList",list);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title:XsBookController.java
	 * @Package com.wangzhixuan.controller
	 * @Description:到书的列表页
	 * @author wangruwei
	 * @date 2019年1月26日上午11:29:31
	 * @param typeId
	 * @return
	 * @version V1.0
	 */
	@RequestMapping(value="/bookslistpage")
	public ModelAndView booksListPage(String typeId){
		ModelAndView modelAndView = new ModelAndView("/admin/xs/bookslistpage3");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("typeId", typeId);
		map.put("getStatus", "0");
		List<XsBook> selectByCondition = xsBookFacade.selectByCondition(map);
		modelAndView.addObject("bookList", selectByCondition);
		modelAndView.addObject("typeId", typeId);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/getbookslistdata")
	@ResponseBody
	public Map<String,Object> getbookslistdata(String typeId,String status){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("typeId", typeId);
		if(!"".equals(status)){
			map.put("getStatus", status);
		}
		
		List<XsBook> list = xsBookFacade.selectByCondition(map);
		return getSuccessRtn(list);
	}
	
	
	/**
	 * 
	 * @Title:XsBookController.java
	 * @Package com.wangzhixuan.controller
	 * @Description:获取书下面的目录
	 * @author wangruwei
	 * @date 2019年1月26日下午2:15:32
	 * @param typsIds
	 * @return
	 * @throws FailingHttpStatusCodeException
	 * @throws MalformedURLException
	 * @throws IOException
	 * @throws SQLException
	 * @throws InterruptedException
	 * @version V1.0
	 */
	@RequestMapping(value="/getbooksdiclist")
	@ResponseBody
	public Map<String,Object> getBooksDicList(String bookIds) throws FailingHttpStatusCodeException, MalformedURLException, IOException, SQLException, InterruptedException{
		//获取到类型集合
		List<XsBook> typesList = xsBookFacade.selectBatchIds(Arrays.asList(bookIds.split(",")));
		int totalSize = 0;
		//循环取到每一个类型的实体
		for (int i = 0; i < typesList.size(); i++) {
			XsBook unitBook = typesList.get(i);
			/**
			 * 删除这个书下面的目录重新爬
			 */
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("bookId", unitBook.getId());
			xsDicContentFacade.deleteByMap(map);
			
			//然后开始获取下面的书籍
			//在这里就处理完url，方法里只是用这里的url解析而已
			String typeUrl = unitBook.getGetUrl();
			//已完结的url
			typeUrl = typeUrl.replace("www", "read").replace("/d", "");
			String bookId = unitBook.getId()+"";
			String bookName = unitBook.getTitle();
			String typeId = unitBook.getTypeId();
			String typeName = unitBook.getTitleTag();
			try {
				List<XsDicContent> mululist = AiXiaWang.getMulu(typeUrl,typeId,typeName,bookId,bookName);
				List<List<XsDicContent>> createListByUnitSize = BreakList.createListByUnitSize(mululist, 3000);
				for (int j = 0; j < createListByUnitSize.size(); j++) {
					xsDicContentFacade.insertBatchList(createListByUnitSize.get(j));
					totalSize+=createListByUnitSize.get(j).size();
				}
			} catch (Exception e) {
				e.printStackTrace();
				//如果出问题，就把出问题的这个数的类别id传出去，让知道是哪出现问题了
				
				return getFailRtn("获取错误，请重新爬取。出错的书是："+unitBook.getTitle()+" ，\n请重新从这个类开始获取"+e.getMessage());
			}
			unitBook.setGetStatus("1");
			xsBookFacade.updateByPrimaryKeySelective(unitBook);
		}
		
		
		return getSuccessRtn("","获取成功，本次获取"+totalSize);
	}
	
}

