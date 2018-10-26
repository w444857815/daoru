package com.wangzhixuan.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangzhixuan.commons.ueditor.UeditorService;
  
/** 
 * 文件上传控制器 
 *  
 * @author Chris Mao(Zibing) 
 * 
 */  
@Controller  
@RequestMapping(value = "/codegeneration")  
public class CodeGeneration {
	
	@Autowired
    private UeditorService ueditorService;
	
	
	@GetMapping("/toconfigpage")
    public String addPage() {
        return "configpage";
    }
	
	
	/**
	 * 导入开发店铺明细
	 */
	
	
	
	public static boolean isInteger(String str){
		return str.matches("[0-9]+");
	}

}  