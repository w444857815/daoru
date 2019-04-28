package com.wangzhixuan.controller;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.service.IDbConfigService;

/**
 * @description：测试Controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {
	
	@Autowired
    private IDbConfigService iDbConfigService;
	
	
    /**
     * 图标测试
     * 
     * @RequiresRoles shiro 权限注解
     * 
     * @return
     */
    @RequiresRoles("test")
    @GetMapping("/dataGrid")
    public String dataGrid() {
        return "admin/test";
    }

    /**
     * 下载测试
     * @return
     */
    @GetMapping("/down")
    public ResponseEntity<Resource> down() {
        File file = new File("/Users/lcm/Downloads/归档.zip");
        return download(file);
    }
    
    
    @GetMapping("/alltable")
    public Map<String,Object> alltable() {
    	return null;
    	/*
    	String searStr = "";
    	List<String> result = new LinkedList<>();
    	// select table_name from information_schema.tables where table_schema='xxcx_develop'
    	List<String> list = iDbConfigService.selectAlltables();
    	for (int i = 0; i < list.size(); i++) {
    		//show full fields from `yy_car`;
//    		List<Map<String,Object>> collist = iDbConfigService.selectTableColms(list.get(i));
    		//查出字段来后查数据
//    		int size = iDbConfigService.selectCountFromTable(list.get(i),collist);
    		if(size>0){
    			result.add(list.get(i));
    		}
		}
    	
    	
    	
    	
    	
    	
    	
    */}
    
    
}
