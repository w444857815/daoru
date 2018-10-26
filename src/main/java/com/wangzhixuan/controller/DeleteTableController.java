package com.wangzhixuan.controller;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.util.TableUtil;

/**
 * @description：测试Controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/deltable")
public class DeleteTableController extends BaseController {

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
     * 资源管理页
     *
     * @return
     */
    @GetMapping("/index")
    public String manager(Model model) {
    	//查出四张表里对应的数据总条数
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	List<Integer> list =  iDbConfigService.selectEveryCount(acount.getLoginName());
    	model.addAttribute("one_a_nums", list.get(0));
    	model.addAttribute("one_b_nums", list.get(1));
    	model.addAttribute("two_a_nums", list.get(2));
    	model.addAttribute("two_b_nums", list.get(3));
    	
    	return "admin/deletePage/deletePage";
    }
    
    /**
     * 
     * @Title:DeleteTableController.java
     * @Package com.wangzhixuan.controller
     * @Description:删除表里的数据
     * @author wangruwei
     * @date 2018年10月26日上午10:52:52
     * @return
     * @version V1.0
     */
    @PostMapping("/deldata")
    @ResponseBody
    public Map<String, Object> delData(String tableEnd) {
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	String tableName = TableUtil.tableName(acount.getLoginName(), tableEnd);
    	
    	iDbConfigService.truncateTable(tableName);
    	
    	return getSuccessRtn(tableEnd,"删除成功");
    }
    
    
}
