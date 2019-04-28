package com.wangzhixuan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.wangzhixuan.model.XsBook;
import com.wangzhixuan.model.XsDicContent;
import com.wangzhixuan.model.XsType;
import com.wangzhixuan.service.XsBookFacade;
import com.wangzhixuan.service.XsDicContentFacade;
import com.wangzhixuan.service.XsTypeFacade;
import com.wangzhixuan.util.freemarker.FreeMarkerUtil;

import freemarker.template.TemplateException;

@Controller
@RequestMapping(value = "/xscreatehtml")
public class XsCreateHtmlController extends BaseController  {
	
	@Autowired
	private XsDicContentFacade xsDicContentFacade;
	@Autowired
	private XsBookFacade xsBookFacade;
	@Autowired
	private XsTypeFacade xsTypeFacade;
	
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/admin/xs/createhtml/index");
		Map<String,Object> map = new HashMap<String,Object>();
		List<XsType> list = xsTypeFacade.selectByCondition(map);
		modelAndView.addObject("typeList",list);
		return modelAndView;
	}
	/**
	 * 
	 * @Title:XsCreateHtmlController.java
	 * @Package com.wangzhixuan.controller
	 * @Description:创建类型下面的书列表
	 * @author wangruwei
	 * @date 2019年3月19日上午11:38:46
	 * @return
	 * @version V1.0
	 * @throws TemplateException 
	 * @throws IOException 
	 */
	@RequestMapping(value="/createbooklist")
	public ModelAndView createBookList(String typeId) throws IOException, TemplateException{
		
		List<XsType> typeList = xsTypeFacade.selectAll();
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("typeId", typeId);
		map.put("status", "连载中");
		//连载中是1  全部是0  已完结是2
		String searchType = "1";
		//这是查到这个类型下所有的书。		连载中
		List<XsBook> lianzailist = xsBookFacade.selectByCondition(map);
		
		
		
		//每页20条
		int lianzaiSize = lianzailist.size();
		
		//算出能生成多少页
		int pageSize = lianzaiSize%20==0?lianzaiSize/20:(lianzaiSize/20+1);
		//在for里生成文件
		for (int i = 0; i < pageSize; i++) {
			List<XsBook> unitPage = new LinkedList<>();
			for (int j = 20*i; j < 20*(i+1); j++) {
				if(j<lianzailist.size()){
					unitPage.add(lianzailist.get(j));
				}else{
					continue;
				}
			}
			Map<String,Object> parammap = new HashMap<String,Object>();
			//书列表
			parammap.put("bookList", unitPage);
			//所属类别名称
			parammap.put("titleTypeName", unitPage.get(0).getTitleTag());
			parammap.put("titleTypeId", typeId);
			//文章状态，连载，完结，全部
			parammap.put("searchType", searchType);
			//分页功能
			List<Integer> pageUtil = new LinkedList<>();
			for (int j = 0; j < pageSize; j++) {
				pageUtil.add(j+1);
			}
			parammap.put("pageUtil", pageUtil);
			parammap.put("nowPage", i+1);
			parammap.put("maxPage", pageSize);
			//所有的类别
			parammap.put("typeList", typeList);
			
			
			FreeMarkerUtil.createFile(parammap, "book_list.ftl", "D:/download/page/sort/"+typeId+"/index_0_"+searchType+"_0_"+(i+1)+".html");
		}
		
		
		map.put("status", "完结");
		List<XsBook> wanjielist = xsBookFacade.selectByCondition(map);
		
		
		
		ModelAndView modelAndView = new ModelAndView("/admin/xs/typestobookindex2");
		List<XsType> list = xsTypeFacade.selectByCondition(map);
		modelAndView.addObject("typeList",list);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Title:XsCreateHtmlController.java
	 * @Package com.wangzhixuan.controller
	 * @Description:TODO
	 * @author wangruwei
	 * @date 2019年3月20日下午3:48:13
	 * @param bookId
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 * @version V1.0
	 */
	@RequestMapping(value="/creatediclist")
	@ResponseBody
	public Map<String,Object> createDicList(Integer bookId) throws IOException, TemplateException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("bookId", bookId);
		XsBook book = xsBookFacade.selectById(bookId);
		List<XsDicContent> dicList = xsDicContentFacade.selectByCondition(map);
		int dicSize = dicList.size();
		Map<String,Object> parammap = new HashMap<String,Object>();
		parammap.put("dicList", dicList);
		parammap.put("book", book);
		System.out.println("开始创建");
		FreeMarkerUtil.createFile(parammap, "dic_content.ftl", "D:/download/page/d/"+bookId+".html");
		System.out.println("创建成功");
		
		return getSuccessRtn("","创建成功");
	}
	
}
