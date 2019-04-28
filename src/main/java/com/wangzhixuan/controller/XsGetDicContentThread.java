package com.wangzhixuan.controller;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.wangzhixuan.model.XsDicContent;
import com.wangzhixuan.service.XsDicContentFacade;
import com.wangzhixuan.util.pachong.axdzs.AiXiaWang;

public class XsGetDicContentThread extends Thread{
	
	private List<XsDicContent> list;
	
	private int thisNum;
	
	private XsDicContentFacade xsDicContentFacade;
	
	CountDownLatch count;
	
	public XsGetDicContentThread(List<XsDicContent> list,int thisNum,XsDicContentFacade xsDicContentFacade, CountDownLatch count) {
		this.list = list;
		this.thisNum = thisNum;
		this.xsDicContentFacade =  xsDicContentFacade;
	}

	public static void main(String[] args) {
		
	}

	@Override
	public void run() {

		for (int i = 0; i < list.size(); i++) {
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
		}
		count.countDown();
		/*for (int i = 0; i < list.size(); i++) {
			XsDicContent xsDicContent = list.get(i);
			String content;
			try {
//				content = AiXiaWang.getContent(xsDicContent.getDicGeturl());
			} catch (Exception e) {
				e.printStackTrace();
				//只要有错，就跳过这个取下一个
				continue;
			}
//			xsDicContent.setContent(content);
//			xsDicContent.setIsGet(1);
//			xsDicContentFacade.updateByPrimaryKeySelective(xsDicContent);
			thisNum++;
		}*/
		
	}

}
