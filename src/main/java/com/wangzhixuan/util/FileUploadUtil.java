package com.wangzhixuan.util;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.wangzhixuan.commons.base.BaseController;

public class FileUploadUtil extends BaseController{
	
	public Map<String, Object> fileUpload(MultipartFile[] files, String realPath
			) throws IOException {

		long startTime = System.currentTimeMillis();
		String fileSavePath = "a";
//		File pathFile = new File("E:\\" + fileSavePath);
		File pathFile = new File(realPath);
		String filePath = "";

		if (!pathFile.exists() && !pathFile.isDirectory()) {
			pathFile.mkdirs();
		}
		
		
		try {
			if (files != null && files.length > 0) {
				// 循环获取file数组中得文件
				for (int i = 0; i < files.length; i++) {
					MultipartFile file = files[i];
					
					UUID.randomUUID().toString().replace("-", "");

					System.out.println(file.getContentType() + "*" + file.getName() + "*" + file.getSize()+"*");

					// 这个方法最快
					//filePath = "E:\\" + fileSavePath + "\\" + file.getOriginalFilename();
					String filename = file.getOriginalFilename();
					int dot = filename.lastIndexOf('.'); 
					String houzhui = "";
		            if ((dot >-1) && (dot < (filename.length() - 1))) { 
		            	houzhui = filename.substring(dot);
		            }
					
					filePath = realPath +File.separator+ UUID.randomUUID().toString().replace("-", "")+houzhui;
					file.transferTo(new File(filePath));

				}
			}
		} catch (IllegalStateException e) {
			e.printStackTrace();
			return getFailRtn("上传文件失败");
		}
		
		long endTime = System.currentTimeMillis();
		System.out.println("方法四的运行时间：" + String.valueOf(endTime - startTime) + "ms");
		return getSuccessRtn(filePath,"上传成功");
	}

}
