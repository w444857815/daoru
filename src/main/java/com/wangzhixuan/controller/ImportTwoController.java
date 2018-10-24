package com.wangzhixuan.controller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
import com.wangzhixuan.model.DbTableone;
import com.wangzhixuan.model.User;
import com.wangzhixuan.service.IDbTableoneService;
import com.wangzhixuan.service.IUserService;
import com.wangzhixuan.util.DateUtil;
import com.wangzhixuan.util.FileUploadUtil;
import com.wangzhixuan.util.ReadExcel;
import com.wangzhixuan.util.XLSXCovertCSVReader;
import com.wangzhixuan.util.bigfile.XLSX2CSV;

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
    @GetMapping("/index")
    public String manager(Model model) {
    	//这里查询当前表中一共有多少条数据
    	int alltableone = dbTableoneService.selectAllCount();
    	model.addAttribute("allsize", alltableone);
        return "admin/importtwo/tableoneindex";
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
    public Map<String,Object> upfileimport(@RequestParam(value = "file", required = false) MultipartFile[] files) {
    	//上传前先判断此用户的文件状态
    	ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
    	User user = iUserService.selectById(acount.getId());
    	if(user.getFileType()!=0){
    		return getFailRtn("您还有未上传完或未导入的文件，请等待上传或导入文件");
    	}
    	FileUploadUtil upUtil = new FileUploadUtil();
    	Map<String,Object> upmap = new HashMap<String,Object>();
    	try {
    		upmap = upUtil.fileUpload(files);
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
    	User upuser = new User();
    	upuser.setId(user.getId());
    	upuser.setFileType(1);
    	iUserService.updateById(upuser);
    	return getSuccessRtn("上传成功");
    }
    
    /**
     * 导入数据，首先先获取到刚才传上去的文件，然后点击把里面的数据导入进去
     * @return
     */
    @PostMapping("/importdataold")
    @ResponseBody
    public Map<String,Object> importdataold() {
    	Map<String,Object> resultmap = new HashMap<String,Object>();
    	File file = new File("D:\\test1.xls");
        try {
            Workbook rwb=Workbook.getWorkbook(file);
            Sheet rs=rwb.getSheet("Sheet1");//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            /**
             * 这里定义一些要返回界面的参数
             */
            List<String> keylist = new LinkedList<>();
            //要插入库中的列表
            List<DbTableone> insertList = new LinkedList<>();
            //要执行更新的列表
            List<DbTableone> updateList = new LinkedList<>();
            //有多少条数据
            int xlsDataNums = rows>0?rows-1:rows;
            //这里放的是 身份证号(关键字)， 实体数据
            Map<String,DbTableone> allmapData = new HashMap<String,DbTableone>();
            
            /*for (int i = 1; i < rows; i++) {
            	DbTableone dbTableone = new DbTableone();
            	
            	  这是原先jxr的代码
            	 * 
            	 
                //第一个是列数，第二个是行数
                String cardNum = rs.getCell(0, i).getContents();//默认最左边编号也算一列 所以这里得1++
                dbTableone.setCardNum(cardNum);
                String idCard = rs.getCell(1, i).getContents();
                dbTableone.setIdCard(idCard);
                String name = rs.getCell(2, i).getContents();
                dbTableone.setName(name);
                String birthday = rs.getCell(3, i).getContents();
                dbTableone.setBirthday(birthday);
                String address = rs.getCell(4, i).getContents();
                dbTableone.setAddress(address);
                String sex = rs.getCell(5, i).getContents();
                dbTableone.setSex(sex);
                String minzu = rs.getCell(6, i).getContents();
                dbTableone.setMinzu(minzu);
                String rysx = rs.getCell(7, i).getContents();
                dbTableone.setRysx(rysx);
                String country = rs.getCell(8, i).getContents();
                dbTableone.setCountry(country);
                String country_cun = rs.getCell(9, i).getContents();
                dbTableone.setCountryCun(country_cun);
                String cbzt = rs.getCell(10, i).getContents();
                dbTableone.setCbzt(cbzt);
                dbTableone.setCreateTime(new Date());
            	
                allmapData.put(idCard, dbTableone);
                keylist.add(idCard);
//                insertList.add(dbTableone);
                
            }
            */
            Long begin1 = new Date().getTime();
            List<String[]> list = XLSXCovertCSVReader
    				.readerExcel(
    						"D:\\test2.xlsx",
    						"Sheet1", 11);
            Long end1 = new Date().getTime();
            System.out.println("处理excel用了 " + (end1 - begin1) / 1000 + " s" + "");
            //定义总条数
    		int totalSize = list.size();
    		//定义线程数
    		int threadNum = 20;
    		//定义单元大小
    		int unitSize = totalSize/threadNum ;
    		//执行线程方法
    		for (int i = 0; i <threadNum; i++) {
    			List<DbTableone> unintList = new LinkedList<>();
    			//这个优化
    			int unitStart = unitSize*i;
    			int unitEnd = unitSize*(i+1);
    			System.out.println("处理第"+i+"个list开始");
    			for (int k = unitStart; k < unitEnd; k++) {
    				//System.out.println("分开所传的是"+k);
    				
    				String[] record = list.get(k);
        			//System.out.print(k+"    ");
        			
        			DbTableone dbTableone = new DbTableone();
        			String cardNum = record[0];//默认最左边编号也算一列 所以这里得1++
                    dbTableone.setCardNum(cardNum);
                    String idCard = record[1];
                    dbTableone.setIdCard(idCard);
                    String name = record[2];
                    dbTableone.setName(name);
                    String birthday = record[3];
                    dbTableone.setBirthday(birthday);
                    String address = record[4];
                    dbTableone.setAddress(address);
                    String sex = record[5];
                    dbTableone.setSex(sex);
                    String minzu = record[6];
                    dbTableone.setMinzu(minzu);
                    String rysx = record[7];
                    dbTableone.setRysx(rysx);
                    String country = record[8];
                    dbTableone.setCountry(country);
                    String country_cun = record[9];
                    dbTableone.setCountryCun(country_cun);
                    String cbzt = record[10];
                    dbTableone.setCbzt(cbzt);
                    dbTableone.setCreateTime(new Date());
                	
                    /*allmapData.put(idCard, dbTableone);
                    keylist.add(idCard);
                    
                    //
                    insertList.add(dbTableone);*/
                    unintList.add(dbTableone);
    			}
    			//这个就走1次，不用管
    			if(i==(threadNum-1)){
    				// 这个优化一下
    				int unitStart_in = unitSize*(i+1);
    				for (int k = unitStart_in; k < totalSize; k++) {
    					//System.out.println("分开所传的是"+k);
    					
    					String[] record = list.get(k);
            			//System.out.print(k+"    ");
            			
            			DbTableone dbTableone = new DbTableone();
            			String cardNum = record[0];//默认最左边编号也算一列 所以这里得1++
                        dbTableone.setCardNum(cardNum);
                        String idCard = record[1];
                        dbTableone.setIdCard(idCard);
                        String name = record[2];
                        dbTableone.setName(name);
                        String birthday = record[3];
                        dbTableone.setBirthday(birthday);
                        String address = record[4];
                        dbTableone.setAddress(address);
                        String sex = record[5];
                        dbTableone.setSex(sex);
                        String minzu = record[6];
                        dbTableone.setMinzu(minzu);
                        String rysx = record[7];
                        dbTableone.setRysx(rysx);
                        String country = record[8];
                        dbTableone.setCountry(country);
                        String country_cun = record[9];
                        dbTableone.setCountryCun(country_cun);
                        String cbzt = record[10];
                        dbTableone.setCbzt(cbzt);
                        dbTableone.setCreateTime(new Date());
                    	
                        /*allmapData.put(idCard, dbTableone);
                        keylist.add(idCard);
                        
                        //
                        insertList.add(dbTableone);*/
                        unintList.add(dbTableone);
    				}
    			}
    			System.out.println("处理第"+i+"个list结束");
    			//上面的是处理数据的代码，下面是执行线程的代码
//        		new MyThread(i+"").start();
    			
//    			dbTableoneService.insertBatch(insertList);
    			new SpringThreadBeachInsertDbOne(dbTableoneService,unintList).start();
    			
    		}
    		if(true){
            	return getSuccessRtn("成功");
            }
//    		for (String[] record : list) {
            Long begin2 = new Date().getTime();
    		for (int i=0;i<list.size();i++) {
    			String[] record = list.get(i);
    			System.out.print(i+"    ");
    			
    			DbTableone dbTableone = new DbTableone();
    			String cardNum = record[0];//默认最左边编号也算一列 所以这里得1++
                dbTableone.setCardNum(cardNum);
                String idCard = record[1];
                dbTableone.setIdCard(idCard);
                String name = record[2];
                dbTableone.setName(name);
                String birthday = record[3];
                dbTableone.setBirthday(birthday);
                String address = record[4];
                dbTableone.setAddress(address);
                String sex = record[5];
                dbTableone.setSex(sex);
                String minzu = record[6];
                dbTableone.setMinzu(minzu);
                String rysx = record[7];
                dbTableone.setRysx(rysx);
                String country = record[8];
                dbTableone.setCountry(country);
                String country_cun = record[9];
                dbTableone.setCountryCun(country_cun);
                String cbzt = record[10];
                dbTableone.setCbzt(cbzt);
                dbTableone.setCreateTime(new Date());
            	
                allmapData.put(idCard, dbTableone);
                keylist.add(idCard);
    			System.out.println();
    		}
    		Long end2 = new Date().getTime();
    		System.out.println("放到list里用了 " + (end2 - begin2) / 1000 + " s" + "");
    		
            System.out.println("开始查数据库中存在的");
            
            for (int i = 0; i < keylist.size(); i++) {
            	System.out.println(i);
            	insertList.add(allmapData.get(keylist.get(i)));
			}
            
            //执行sql查询，查出来里面有多少条重复,这里是查出来库中已经存在的身份证号列表
            /*List<String> existNums = dbTableoneService.selectExistNums(keylist);
            System.out.println("查完了");
            keylist.removeAll(existNums);
            //现在的keylist，就是excel中不存在库中的信息
            for (int i = 0; i < keylist.size(); i++) {
            	insertList.add(allmapData.get(keylist.get(i)));
			}
            //现在是要放要更新的集合,直接在for里面更新
            int updateSize = 0;
            
            System.err.println("开始批量更新"+DateUtil.DateToStrYMD(new Date()));
            for (int i = 0; i < existNums.size(); i++) {
				updateList.add(allmapData.get(existNums.get(i)));
				System.out.println(i);
				int j = dbTableoneService.updateByIdCard(allmapData.get(existNums.get(i)));
				if(j>0){
					updateSize++;
				}
				//updateSize = dbTableoneService.updateBatchByIdCard(updateList);
			}
            System.err.println("updateSize更新结束"+DateUtil.DateToStrYMD(new Date()));
            //先执行更新，然后执行插入
            */
            //插入
            System.err.println("开始批量插入"+DateUtil.DateToStrYMD(new Date()));
            if(insertList.size()>0){
            	dbTableoneService.insertBatch(insertList);
            }
            System.err.println("结束批量插入"+DateUtil.DateToStrYMD(new Date()));
            
            resultmap.put("xlsDataNums", xlsDataNums);
//            resultmap.put("updateSize", updateSize);
            resultmap.put("updateSize", 0);
            resultmap.put("insertSize", insertList.size());
            resultmap.put("nowTableSize", dbTableoneService.selectAllCount());
            
            
            //然后通过keylist来查找当前数据库中是否存在这个key，如果存在的话，就执行更新操作，不存在的话就放到插入列表中
            //直接查
            /**
             * -- 这个就找出里面存在的是谁
select * from test where name in (1,5)
-- 然后获取到存在的，其他的就是不存在的。
             */
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    	
    	return getSuccessRtn(resultmap);
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
    public Map<String,Object> importdata() {
    	Map<String,Object> resultmap = new HashMap<String,Object>();
        try {
            /**
             * 这里定义一些要返回界面的参数
             */
            List<String> keylist = new LinkedList<>();
            //要插入库中的列表
            List<DbTableone> insertList = new LinkedList<>();
            //要执行更新的列表
            List<DbTableone> updateList = new LinkedList<>();
            //这里放的是 身份证号(关键字)， 实体数据
            Map<String,DbTableone> allmapData = new HashMap<String,DbTableone>();
            
            //导入前先判断是否有导入资格
            ShiroUser acount = (ShiroUser)SecurityUtils.getSubject().getPrincipal();
        	User user = iUserService.selectById(acount.getId());
        	if(user.getFileType()!=1){
        		return getFailRtn("还有未导入的文件，请先导入");
        	}
            
            Long begin1 = new Date().getTime();
            List<String[]> list = XLSX2CSV.getRecords("D:\\zhuanmen.xlsx", 11);
/*            List<String[]> list = XLSXCovertCSVReader
            		.readerExcel(
            				"D:\\test2.xlsx",
            				"Sheet1", 11);
*/            
            Long end1 = new Date().getTime();
            System.out.println("处理excel用了 " + (end1 - begin1) / 1000 + " s" + "");
            //定义总条数
    		int totalSize = list.size();
    		//定义线程数
    		int threadNum = 20;
    		//定义单元大小
    		int unitSize = totalSize/threadNum ;
    		//执行线程方法
    		for (int i = 0; i <threadNum; i++) {
    			List<DbTableone> unintList = new LinkedList<>();
    			//这个优化
    			int unitStart = unitSize*i;
    			int unitEnd = unitSize*(i+1);
    			System.out.println("处理第"+i+"个list开始");
    			for (int k = unitStart; k < unitEnd; k++) {
    				//System.out.println("分开所传的是"+k);
    				
    				String[] record = list.get(k);
        			//System.out.print(k+"    ");
        			
        			DbTableone dbTableone = new DbTableone();
        			String cardNum = record[0];//默认最左边编号也算一列 所以这里得1++
                    dbTableone.setCardNum(cardNum);
                    String idCard = record[1];
                    dbTableone.setIdCard(idCard);
                    String name = record[2];
                    dbTableone.setName(name);
                    String birthday = record[3];
                    dbTableone.setBirthday(birthday);
                    String address = record[4];
                    dbTableone.setAddress(address);
                    String sex = record[5];
                    dbTableone.setSex(sex);
                    String minzu = record[6];
                    dbTableone.setMinzu(minzu);
                    String rysx = record[7];
                    dbTableone.setRysx(rysx);
                    String country = record[8];
                    dbTableone.setCountry(country);
                    String country_cun = record[9];
                    dbTableone.setCountryCun(country_cun);
                    String cbzt = record[10];
                    dbTableone.setCbzt(cbzt);
                    dbTableone.setCreateTime(new Date());
                	
                    /*allmapData.put(idCard, dbTableone);
                    keylist.add(idCard);
                    
                    //
                    insertList.add(dbTableone);*/
                    unintList.add(dbTableone);
    			}
    			//这个就走1次，不用管
    			if(i==(threadNum-1)){
    				// 这个优化一下
    				int unitStart_in = unitSize*(i+1);
    				for (int k = unitStart_in; k < totalSize; k++) {
    					//System.out.println("分开所传的是"+k);
    					
    					String[] record = list.get(k);
            			//System.out.print(k+"    ");
            			
            			DbTableone dbTableone = new DbTableone();
            			String cardNum = record[0];//默认最左边编号也算一列 所以这里得1++
                        dbTableone.setCardNum(cardNum);
                        String idCard = record[1];
                        dbTableone.setIdCard(idCard);
                        String name = record[2];
                        dbTableone.setName(name);
                        String birthday = record[3];
                        dbTableone.setBirthday(birthday);
                        String address = record[4];
                        dbTableone.setAddress(address);
                        String sex = record[5];
                        dbTableone.setSex(sex);
                        String minzu = record[6];
                        dbTableone.setMinzu(minzu);
                        String rysx = record[7];
                        dbTableone.setRysx(rysx);
                        String country = record[8];
                        dbTableone.setCountry(country);
                        String country_cun = record[9];
                        dbTableone.setCountryCun(country_cun);
                        String cbzt = record[10];
                        dbTableone.setCbzt(cbzt);
                        dbTableone.setCreateTime(new Date());
                    	
                        /*allmapData.put(idCard, dbTableone);
                        keylist.add(idCard);
                        
                        //
                        insertList.add(dbTableone);*/
                        unintList.add(dbTableone);
    				}
    			}
    			System.out.println("处理第"+i+"个list结束");
    			//上面的是处理数据的代码，下面是执行线程的代码
//        		new MyThread(i+"").start();
    			
//    			dbTableoneService.insertBatch(insertList);
    			new SpringThreadBeachInsertDbOne(dbTableoneService,unintList).start();
    			
    		}
            System.err.println("是最后执行吗？？？？？？？？？？？？？？？？？？？？？？？");
            resultmap.put("xlsDataNums", list.size());
//            resultmap.put("updateSize", updateSize);
            resultmap.put("updateSize", 0);
            resultmap.put("insertSize", insertList.size());
            resultmap.put("nowTableSize", dbTableoneService.selectAllCount());
            
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } 
    	
    	return getSuccessRtn(resultmap);
    }
}
