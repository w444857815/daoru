package com.wangzhixuan.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	
	public static byte[] File2byte(String filePath)
    {
        byte[] buffer = null;
        try
        {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1)
            {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return buffer;
    }

    public static void byte2File(byte[] buf, String filePath, String fileName)
    {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try
        {
            File dir = new File(filePath);
            if (!dir.exists() && dir.isDirectory())
            {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (bos != null)
            {
                try
                {
                    bos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
	
}
