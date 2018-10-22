package com.wangzhixuan.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;


public class daoruXlsxExcel {
	public static void main(String[] args) {
		try {
			daoru();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void daoru() throws IOException {
        //创建处理EXCEL的类  
        ReadExcel readExcel = new ReadExcel();  
        
        File file = new File("D://test2.xlsx");
        FileInputStream fileInputStream = new FileInputStream(file);
        MultipartFile multipartFile = new MockMultipartFile(file.getName(), file.getName(),
        		"text/plain", fileInputStream);
        //解析excel，获取上传的事件单  
        List<Map<String, Object>> userList = readExcel.getExcelInfo(null);  
        //至此已经将excel中的数据转换到list里面了,接下来就可以操作list,可以进行保存到数据库,或者其他操作,  
        for(Map<String, Object> user:userList){
            /*int ret = userDao.insertUser(user.get("name").toString(), user.get("sex").toString(), Integer.parseInt(user.get("age").toString()));
            if(ret == 0){
                result = "插入数据库失败";
            }*/
        }
        /*if(userList != null && !userList.isEmpty()){  
            result = "上传成功";  
        }else{  
            result = "上传失败";  
        }  */
		
	}
}
