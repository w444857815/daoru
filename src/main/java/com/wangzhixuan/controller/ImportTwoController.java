package com.wangzhixuan.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.wangzhixuan.commons.base.BaseController;
import com.wangzhixuan.commons.csrf.CsrfToken;
import com.wangzhixuan.commons.shiro.ShiroUser;
import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.IDbConfigService;
import com.wangzhixuan.service.IDbTableoneService;
import com.wangzhixuan.service.IUserService;
import com.wangzhixuan.util.FileUploadUtil;
import com.wangzhixuan.util.TableUtil;
import com.wangzhixuan.util.XLSXCovertCSVReader;
import com.wangzhixuan.util.reflectUtil;

import jxl.Sheet;
import jxl.Workbook;

/**
 * @description：登录退出
 * @author：zhixuan.wang
 * @date：2015/10/1 14:51
 */
@Controller
@RequestMapping("/importtwo")
public class ImportTwoController extends BaseController {
    
    @Autowired
    private IDbTableoneService dbTableoneService;
    
    @Autowired
    private IDbConfigService iDbConfigService;
    
    @Autowired
    private IUserService iUserService;
    
    
    /**
     * 首页
     *
     * @return
     */
    @GetMapping("/")
    public String index() {
        return "redirect:/index";
    }

    
    /**
     * 资源管理页
     *
     * @return
     */
    @GetMapping("/indexonea")
    public String indexonea(Model model) {
    	//这里查询当前表中一共有多少条数据
//    	int alltableone = dbTableoneService.selectAllCount();
//    	model.addAttribute("allsize", alltableone);
        return "admin/importtwo/tableonea";
    }
    
    @GetMapping("/indexoneb")
    public String indexoneb(Model model) {
    	//这里查询当前表中一共有多少条数据
//    	int alltableone = dbTableoneService.selectAllCount();
//    	model.addAttribute("allsize", alltableone);
        return "admin/importtwo/tableoneb";
    }
    
    
    @GetMapping("/indextwoa")
    public String indextwoa(Model model) {
    	//这里查询当前表中一共有多少条数据
//    	int alltableone = dbTableoneService.selectAllCount();
//    	model.addAttribute("allsize", alltableone);
        return "admin/importtwo/tabletwoa";
    }
    
    
    @GetMapping("/indextwob")
    public String indextwob(Model model) {
    	//这里查询当前表中一共有多少条数据
//    	int alltableone = dbTableoneService.selectAllCount();
//    	model.addAttribute("allsize", alltableone);
        return "admin/importtwo/tabletwob";
    }
    
    
    
    /**
     * GET 登录
     * @return {String}
     */
    @GetMapping("/login")
    @CsrfToken(create = true)
    public String login() {
        logger.info("GET请求登录");
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return "redirect:/index";
        }
        return "login";
    }

    
    /**
     * 上传文件并导入数据
     * @return
     */
    @PostMapping("/upfileimport")
    @ResponseBody
    public Map<String,Object> upfileimport(@RequestParam(value = "file", required = false) MultipartFile[] files,HttpServletRequest request) {
    	//上传前先判断此用户的文件状态
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	/*User user = iUserService.selectById(acount.getId());
    	if(user.getFileType()!=0){
    		return getFailRtn("您还有未上传完或未导入的文件，请等待上传或导入文件");
    	}*/
    	FileUploadUtil upUtil = new FileUploadUtil();
    	Map<String,Object> upmap = new HashMap<String,Object>();
    	String realPath=request.getServletContext().getRealPath("/uploadExcels");
    	try {
    		upmap = upUtil.fileUpload(files,realPath);
		} catch (IOException e) {
			e.printStackTrace();
			return getFailRtn("上传文件失败");
		}
    	String filePath = "";
    	if("-1".equals(upmap.get("code")+"")){
    		return upmap;
    	}
    	else{
    		filePath = upmap.get("data").toString();
    	}
    	
        System.out.println(acount.getName()+"**********");
    	Map<String,Object> resultmap = new HashMap<String,Object>();
    	resultmap.put("resultmap", filePath);
    	//在这里把状态改一下，标记为已上传，未导入
    	/*User upuser = new User();
    	upuser.setId(user.getId());
    	upuser.setFileType(1);
    	iUserService.updateById(upuser);*/
    	return getSuccessRtn(filePath,"上传成功");
    }
    
    
    /**
     * 未授权
     * @return {String}
     */
    @GetMapping("/unauth")
    public String unauth() {
        if (SecurityUtils.getSubject().isAuthenticated() == false) {
            return "redirect:/login";
        }
        return "unauth";
    }

    /**
     * 退出
     * @return {Result}
     */
    @PostMapping("/logout")
    @ResponseBody
    public Object logout() {
        logger.info("登出");
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return renderSuccess();
    }

    
    /**
     * 导入数据，首先先获取到刚才传上去的文件，然后点击把里面的数据导入进去
     * @return
     */
    @PostMapping("/importdata")
    @ResponseBody
    public Map<String,Object> importdata(String filepath,int rowstart,int colsNum,String tableEnd) {
    	Map<String,Object> resultmap = new HashMap<String,Object>();
        try {
            /**
             * 这里定义一些要返回界面的参数
             */
            
            /*ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        	User user = iUserService.selectById(acount.getId());
        	if(user.getFileType()!=1){
        		return getFailRtn("还有未导入的文件，请先导入");
        	}*/
        	List<String[]> list = new LinkedList<>();
        	//如果是xls的，就用jxl的，如果不是，走下面的
        	
            if(filepath.endsWith(".xls")){
            	Workbook rwb=Workbook.getWorkbook(new File(filepath));
                Sheet rs=rwb.getSheet(0);//或者rwb.getSheet(0)
                int clos=rs.getColumns();//得到所有的列
                int rows=rs.getRows();//得到所有的行
                for (int i = 0; i < rows; i++) {
//                	String shuzuStr = "";
                	LinkedList<String> listxlsshuju=new LinkedList<String>();
                	for (int j = 0; j < clos; j++) {
//                		shuzuStr+=rs.getCell(j, i).getContents()+",";
                		listxlsshuju.add(rs.getCell(j, i).getContents());
					}
                	String[] strings = new String[listxlsshuju.size()];
                	listxlsshuju.toArray(strings);
//                	String[] args = {shuzuStr};
                	list.add(strings);
				}
            }
            
          //列数
    		int colNums = colsNum;
    		if(tableEnd.startsWith("one")){
    			colNums = 11;
    		}else{
    			colNums = 28;
    		}
            
            Long begin1 = new Date().getTime();
            list = XLSXCovertCSVReader.readerExcel(
            		filepath,
					"随便", colNums);
            Long end1 = new Date().getTime();
            System.out.println("处理excel用了 " + (end1 - begin1) / 1000 + " s" + "");
            //直接定义第一行不是数据
            list.remove(0);
            //定义总条数
    		int totalSize = list.size();
    		//定义线程数
    		int threadNum = 1;
    		CountDownLatch count = new CountDownLatch(threadNum);
    		//定义表名
    		ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    		String tableName = TableUtil.tableName(acount.getLoginName(), tableEnd);
    		
    		
    		LinkedList<Integer> colNumsList = new LinkedList<>();
    		for (int i = 0; i < colNums; i++) {
    			colNumsList.add(i);
			}
    		//定义单元大小
    		int unitSize = totalSize/threadNum ;
    		//执行线程方法
    		for (int i = 0; i <threadNum; i++) {
    			List<DbConfigTable> unintList = new LinkedList<>();
    			//这个优化
    			int unitStart = unitSize*i;
    			int unitEnd = unitSize*(i+1);
    			System.out.println("处理第"+i+"个list开始");
    			for (int k = unitStart; k < unitEnd; k++) {
    				//System.out.println("分开所传的是"+k);
    				
    				String[] record = list.get(k);
        			//System.out.print(k+"    ");
        			
    				DbConfigTable dbConfigTable = new DbConfigTable();
    				
    				int colsSize = record.length;
    				for (int j = 0; j < colsSize; j++) {
    					reflectUtil.reflectset(dbConfigTable, "col"+j, record[j]);
					}
    				
    				
                    unintList.add(dbConfigTable);
    			}
    			//这个就走1次，不用管
    			if(i==(threadNum-1)){
    				// 这个优化一下
    				int unitStart_in = unitSize*(i+1);
    				for (int k = unitStart_in; k < totalSize; k++) {
    					//System.out.println("分开所传的是"+k);
    					
    					String[] record = list.get(k);
            			//System.out.print(k+"    ");
            			
    					DbConfigTable dbConfigTable = new DbConfigTable();
    					
    					int colsSize = record.length;
        				for (int j = 0; j < colsSize; j++) {
        					reflectUtil.reflectset(dbConfigTable, "col"+j, record[j]);
    					}
    					
                        unintList.add(dbConfigTable);
    				}
    			}
    			System.out.println("处理第"+i+"个list结束");
    			
    			//iDbConfigService.insertBatch(unintList,tableName,colNumsList);
    			new SpringThreadBeachInsertDbOne(iDbConfigService,unintList,tableName,colNumsList).start();
    			count.countDown();
    		}
    		try {
    			count.await();
    		} catch (InterruptedException e) {
    			e.printStackTrace();
    		}
    		//执行完成以后，filetype变成0
    		/*User upuser = new User();
        	upuser.setId(user.getId());
        	upuser.setFileType(0);
        	iUserService.updateById(upuser);*/
        	
            System.err.println("是最后执行吗？？？？？？？？？？？？？？？？？？？？？？？");
//            resultmap.put("xlsDataNums", list.size());
////            resultmap.put("updateSize", updateSize);
//            resultmap.put("updateSize", 0);
//            resultmap.put("insertSize", insertList.size());
            resultmap.put("nowTableSize", totalSize);
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    	
    	return getSuccessRtn(resultmap);
    }
}
