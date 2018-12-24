package com.wangzhixuan.controller;

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

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.model.DbUserHeadertitle;
import com.wangzhixuan.service.DbUserHeadertitleService;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.util.FileUploadUtil;
import com.wangzhixuan.util.TableUtil;
import com.wangzhixuan.util.reflectUtil;
import com.wangzhixuan.util.bigfile.ExplorBigExcel;

/**
 * @description：测试Controller
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/dbexportclean")
public class DbExportXDataCleanController extends BaseController {

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
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal(); 
    	Map<String,Object> map = new HashMap<String,Object>();
    	map.put("userId", acount.getId());
    	map.put("tableType", "1");
    	List<DbUserHeadertitle> sourceone = dbUserHeadertitleService.selectByMap(map);
    	map.put("tableType", "2");
    	List<DbUserHeadertitle> sourcetwo = dbUserHeadertitleService.selectByMap(map);
    	if(sourceone.size()==0||sourcetwo.size()==0){
    		return "admin/dbpageClean/dbPageNoData";
    	}
    	model.addAttribute("sourceone", sourceone);
    	model.addAttribute("sourcetwo", sourcetwo);
    	//把数据源1和数据源2的数据都查出来10条
    	String tableqian = TableUtil.tableName(acount.getLoginName(), "one_a"); 
    	String tablehou = TableUtil.tableName(acount.getLoginName(), "two_a");
    	Map<String,Object> csmap = new HashMap<String,Object>();
    	csmap.put("tableName", tableqian);
    	csmap.put("list", sourceone);
    	List<DbConfigTable> sourceDataOne = iDbConfigService.selectExampleLimitData(csmap);
    	/*DbConfigTable sourceOneTitle = new DbConfigTable();
    	for (int i = 0; i < sourceone.size(); i++) {
    		reflectUtil.reflectset(sourceOneTitle, "col"+i, sourceone.get(i).getHeaderTitle());
		}
    	sourceDataOne.add(0,sourceOneTitle);*/
    	model.addAttribute("sourceDataOne", sourceDataOne);
    	StringBuffer sourOneStr = new StringBuffer();
    	for (int i = 0; i < sourceDataOne.size(); i++) {
    		sourOneStr.append("<tr>");
    		for (int j = 0; j < sourceone.size(); j++) {
    			sourOneStr.append("<td width=\"auto;\">").append(reflectUtil.reflectget(sourceDataOne.get(i), "col"+j)).append("</td>");
			}
    		sourOneStr.append("</tr>");
		}
    	model.addAttribute("sourOneStr", sourOneStr);
    	csmap.clear();
    	csmap.put("tableName", tablehou);
    	csmap.put("list", sourcetwo);
    	List<DbConfigTable> sourceDataTwo = iDbConfigService.selectExampleLimitData(csmap);
    	/*DbConfigTable sourceTwoTitle = new DbConfigTable();
    	for (int i = 0; i < sourcetwo.size(); i++) {
    		reflectUtil.reflectset(sourceTwoTitle, "col"+i, sourcetwo.get(i).getHeaderTitle());
		}
    	sourceDataTwo.add(0,sourceTwoTitle);*/
    	model.addAttribute("sourceDataTwo", sourceDataTwo);
    	
    	StringBuffer sourTwoStr = new StringBuffer();
    	for (int i = 0; i < sourceDataTwo.size(); i++) {
    		sourTwoStr.append("<tr>");
    		for (int j = 0; j < sourcetwo.size(); j++) {
    			sourTwoStr.append("<td>").append(reflectUtil.reflectget(sourceDataTwo.get(i), "col"+j)).append("</td>");
			}
    		sourTwoStr.append("</tr>");
		}
    	model.addAttribute("sourTwoStr", sourTwoStr);
    	return "admin/dbpageClean/dbPage";
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
    public ResponseEntity<byte[]> dbTablesData(String sourceone,String sourcetwo,String dbtype,HttpServletRequest request) {
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	String[] arys = {"one_a","two_a"};
    	String tableqian = TableUtil.tableName(acount.getLoginName(), arys[0]); //
    	String tablehou = TableUtil.tableName(acount.getLoginName(), arys[1]);
    	
    	//
    	String col_qian = "";
    	String col_hou = "";
    	if(arys[0].startsWith("one_")&&arys[1].startsWith("one_")){
    		col_qian = sourceone;
    		col_hou = sourceone;
    	}
    	else if(arys[0].startsWith("one_")&&arys[1].startsWith("two_")){
    		col_qian = sourceone;
    		col_hou = sourcetwo;
    	}
    	else if(arys[0].startsWith("two_")&&arys[1].startsWith("one_")){
    		col_qian = sourcetwo;
    		col_hou = sourceone;
    	}
    	else if(arys[0].startsWith("two_")&&arys[1].startsWith("two_")){
    		col_qian = sourcetwo;
    		col_hou = sourcetwo;
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
    	//System.out.println(list);
    	String downFilePath = "";
    	try {
    		String realPath=request.getServletContext().getRealPath("/");
    		
        	Map<String,Object> map = new HashMap<String,Object>();
        	map.put("userId", acount.getId());
        	map.put("tableType", "1");
    		List<DbUserHeadertitle> sourcceOne = dbUserHeadertitleService.selectByMap(map);
    		map.put("tableType", "2");
    		List<DbUserHeadertitle> sourcceTwo = dbUserHeadertitleService.selectByMap(map);
    		
    		
			downFilePath = ExplorBigExcel.exportExcel(list, csmap.get("tableqian").toString(),realPath,sourcceOne,sourcceTwo);
			
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
