package com.wangzhixuan.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.service.DbUserHeadertitleService;
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
	
	@Autowired
    private DbUserHeadertitleService dbUserHeadertitleService;
    
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
    
    @GetMapping("/indexclean")
    public String managerClean(Model model) {
    	//查出四张表里对应的数据总条数
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	List<Integer> list =  iDbConfigService.selectEveryCount(acount.getLoginName());
    	model.addAttribute("one_a_nums", list.get(0));
    	model.addAttribute("one_b_nums", list.get(1));
    	model.addAttribute("two_a_nums", list.get(2));
    	model.addAttribute("two_b_nums", list.get(3));
    	
    	return "admin/deletePageClean/deletePage";
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
    	//one_a   two_a
    	String tableType = "";
    	if("one_a".equals(tableEnd)){
    		tableType = "1";
    	}
    	else if("two_a".equals(tableEnd)){
    		tableType = "2";
    	}
    	else{
    		return getSuccessRtn(tableEnd,"删除失败，请选择数据源一或二删除");
    	}
    	
    	
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	Map<String,Object> headerTitlemap = new HashMap<String,Object>();
        headerTitlemap.put("userid", acount.getId());
        headerTitlemap.put("tableType", tableType);
        //先删了，然后插入
        dbUserHeadertitleService.deleteHeaderTitle(headerTitlemap);
    	
    	String tableName = TableUtil.tableName(acount.getLoginName(), tableEnd);
    	
    	iDbConfigService.truncateTable(tableName);
    	
    	return getSuccessRtn(tableEnd,"删除成功");
    }
    
    
}
