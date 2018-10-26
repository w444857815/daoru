package com.wangzhixuan.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.util.FileUploadUtil;
import com.wangzhixuan.util.TableUtil;
import com.wangzhixuan.util.bigfile.ExplorBigExcel;

/**
 * @description：测试Controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/dbexport")
public class DbExportController extends BaseController {

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
    	
    	return "admin/dbpage/dbPage";
    }
    
   /**
    * 
    * @Title:DbExportController.java
    * @Package com.wangzhixuan.controller
    * @Description:TODO
    * @author wangruwei
    * @date 2018年10月26日下午2:28:34
    * @param sourcetype
    * @param dbtype			<option value="1">前者在后者中没有的</option>
            				<option value="2">后者在前者中没有的</option>
    * @return
    * @version V1.0
    */
    @PostMapping("/dbtablesdata")
    public ResponseEntity<byte[]> dbTablesData(String sourcetype,String dbtype,HttpServletRequest request) {
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	String[] arys = sourcetype.split("@");
    	String tableqian = TableUtil.tableName(acount.getLoginName(), arys[0]); //
    	String tablehou = TableUtil.tableName(acount.getLoginName(), arys[1]);
    	
    	//
    	String col_qian = "";
    	String col_hou = "";
    	if(arys[0].startsWith("one_")&&arys[1].startsWith("one_")){
    		col_qian = "col1";
    		col_hou = "col1";
    	}
    	else if(arys[0].startsWith("one_")&&arys[1].startsWith("two_")){
    		col_qian = "col1";
    		col_hou = "col8";
    	}
    	else if(arys[0].startsWith("two_")&&arys[1].startsWith("one_")){
    		col_qian = "col8";
    		col_hou = "col1";
    	}
    	else if(arys[0].startsWith("two_")&&arys[1].startsWith("two_")){
    		col_qian = "col8";
    		col_hou = "col8";
    	}
    	
    	
    	Map<String,Object> csmap = new HashMap<String,Object>();
    	csmap.put("tableqian", tableqian);
    	csmap.put("tablehou", tablehou);
    	csmap.put("col_qian", col_qian);
    	csmap.put("col_hou", col_hou);
    	if("2".equals(dbtype)){
    		csmap.put("tableqian", tablehou);
        	csmap.put("tablehou", tableqian);
        	csmap.put("col_qian", col_hou);
        	csmap.put("col_hou", col_qian);
    	}
    	
    	List<DbConfigTable> list = iDbConfigService.selectdbTablesData(csmap);
    	System.out.println(list);
    	String downFilePath = "";
    	try {
    		String realPath=request.getServletContext().getRealPath("/");
			downFilePath = ExplorBigExcel.exportExcel(list, "one",realPath);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	ResponseEntity<byte[]> responseEntity = null;
		try {
			byte[] bytes = FileUploadUtil.File2byte(downFilePath);
			HttpHeaders httpHeaders = new HttpHeaders();
			httpHeaders.setContentDispositionFormData("attachment", "对比结果.xlsx");
			httpHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			responseEntity = new ResponseEntity<byte[]>(bytes, httpHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return responseEntity;
    }
    
    
    
}
