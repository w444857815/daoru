package com.wangzhixuan.controller;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.wangzhixuan.model.XsDicContent;
import com.wangzhixuan.service.XsDicContentFacade;
import com.wangzhixuan.util.BreakList;
import com.wangzhixuan.util.pachong.axdzs.AiXiaWang;


/**
 * XsDicContent
 * XsDicContent
 * <p>Title:XsDicContentController </p>
 * <p>Description:XsDicContent </p>
 * <p>Company: </p> 
 * @author wangruwei
 * 获取内容的controller
 * @date 2019-01-26 14:23:50
 */
@Controller
@RequestMapping(value = "/content")
public class XsDicContentController extends BaseController {
	
	@Autowired
	private XsDicContentFacade xsDicContentFacade;
	
	//一次取多少条
	private int unitNum = 5000;
	
	//循环次数
	private static int cycleNum = 5;
 
	private long start = 0;
	
	private int threadNum = 10;
	/**
	 * 跳转到列表页
	* @Title: index 
	* @return    设定文件 
	* @return modelAndView   返回类型 
	* @throws 
	* @author wangruwei
	* @date 2019-01-26 14:23:50
	 */
	@RequestMapping(value="/index")
	public ModelAndView index(){
		ModelAndView modelAndView = new ModelAndView("/admin/package/index");
		return modelAndView;
	}
	
	@RequestMapping(value="/getcontentbyurl")
	@ResponseBody
	public Map<String,Object> getContentByUrl(int num,int thisNum) throws InterruptedException{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("pageparam", 1);
		map.put("curRow", 0);
		map.put("limitSize", unitNum);
		map.put("isGet", 0);
		
		//取5000条没抓取的数据，进行分别抓取
		List<XsDicContent> list = xsDicContentFacade.selectByCondition(map);
		
		if(num==0){
			start = System.currentTimeMillis();
		}
		//这5000条分5个线程去抓,每个线程里抓1000条
		List<List<XsDicContent>> createListBySizeNum = BreakList.createListBySizeNum(list, threadNum);
		
		CountDownLatch count = new CountDownLatch(threadNum);
		for (int i = 0; i < createListBySizeNum.size(); i++) {
			XsGetDicContentThread thead = new XsGetDicContentThread(createListBySizeNum.get(i), thisNum, xsDicContentFacade,count);
			thead.start();
		}
		count.wait();
		
		/*for (int i = 0; i < list.size(); i++) {
			XsDicContent xsDicContent = list.get(i);
			String content;
			try {
				content = AiXiaWang.getContent(xsDicContent.getDicGeturl());
			} catch (Exception e) {
				e.printStackTrace();
				//只要有错，就跳过这个取下一个
				continue;
			}
			xsDicContent.setContent(content);
			xsDicContent.setIsGet(1);
			xsDicContentFacade.updateByPrimaryKeySelective(xsDicContent);
			thisNum++;
		}*/
		
		num++;
		if(num==cycleNum){
			long end = System.currentTimeMillis();
			System.out.println("时间是"+(end-start));
			return getSuccessRtn("", "本次执行"+cycleNum+"次成功,应该是获取"+cycleNum*unitNum+"条，实际获取"+thisNum+"条");
		}
		return getContentByUrl(num,thisNum);
	}
	
	
	
	
}

