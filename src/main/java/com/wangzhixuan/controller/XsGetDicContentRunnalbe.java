package com.wangzhixuan.controller;

import java.util.List;

import com.wangzhixuan.model.XsDicContent;
import com.wangzhixuan.service.XsDicContentFacade;
import com.wangzhixuan.util.pachong.axdzs.AiXiaWang;

public class XsGetDicContentRunnalbe implements Runnable{
	
	private List<XsDicContent> list;
	
	private int thisNum;
	
	private XsDicContentFacade xsDicContentFacade;
	
	public XsGetDicContentRunnalbe(List<XsDicContent> list,int thisNum,XsDicContentFacade xsDicContentFacade) {
		this.list = list;
		this.thisNum = thisNum;
		this.xsDicContentFacade =  xsDicContentFacade;
	}

	public static void main(String[] args) {
		
	}

	@Override
	public synchronized void run() {

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
