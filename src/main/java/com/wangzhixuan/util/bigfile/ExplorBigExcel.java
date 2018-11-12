package com.wangzhixuan.util.bigfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.wangzhixuan.model.DbConfigTable;
import com.wangzhixuan.model.DbUserHeadertitle;
import com.wangzhixuan.util.reflectUtil;

public class ExplorBigExcel {
	public static void main(String[] args) throws IOException {
		Excel2007AboveOperate();
	}
	
	
	
	public static void Excel2007AboveOperate() throws IOException {
		
        XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File("exportExcel/daochTemp.xlsx")));
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
//            Workbook workbook = WorkbookFactory.create(new FileInputStream(new File(filePath)));
        Sheet first = sxssfWorkbook.getSheetAt(0);
//        for (int i = 0; i < 100000; i++) {
        for (int i = 0; i < 10; i++) {
            Row row = first.createRow(i);
            for (int j = 0; j < 11; j++) {
                if(i == 0) {
                    // 首行
                    row.createCell(j).setCellValue("column" + j);
                } else {
                    // 数据
                    if (j == 0) {
                        CellUtil.createCell(row, j, String.valueOf(i));
                    } else{
                    	CellUtil.createCell(row, j, String.valueOf(Math.random()));
                    }
                }
            }
        }
        FileOutputStream out = new FileOutputStream("exportExcel/exportExcel.xlsx");
        sxssfWorkbook.write(out);
        out.close();
    }
	
	public static String exportExcel(List<DbConfigTable> list,String tableEnd,String realPath, List<DbUserHeadertitle>... source) throws IOException {
        XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File(realPath+"/exportExcelTemplent/daochTemp.xlsx")));
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
        Sheet first = sxssfWorkbook.getSheetAt(0);
        int size = list.size();
        int col ;
        boolean sourceOneBoo = false;
        boolean sourceTwoBoo = false;
        if(tableEnd.contains("one_")){
        	if(source.length==0){
        		col = 11;
        	}else{
        		col = source[0].size();
        		sourceOneBoo = true;
        	}
        }else{
        	if(source.length==0){
        		col = 28;
        	}else{
        		col = source[1].size();
        		sourceTwoBoo = true;
        	}
        }
        
        int startNum = 0;
        if(source.length==0){
        	startNum = 0;
        }else{
        	startNum = 1;
        	Row firstrow = first.createRow(0);
        	if(sourceOneBoo){
        		for (int i = 0; i < source[0].size(); i++) {
        			firstrow.createCell(i).setCellValue(source[0].get(i).getHeaderTitle());
				}
        	}
        	if(sourceTwoBoo){
        		for (int i = 0; i < source[1].size(); i++) {
        			firstrow.createCell(i).setCellValue(source[1].get(i).getHeaderTitle());
				}
        	}
            
        }
        System.out.println("开始");
        for (int i = 0; i < size; i++) {
        	System.out.println("写入第"+i+"行");
            Row row = first.createRow(i+startNum);
            DbConfigTable dbConfigTable = list.get(i);
            for (int j = 0; j < col; j++) {
            	// 数据
            	CellUtil.createCell(row, j, reflectUtil.reflectget(dbConfigTable, "col"+j).toString());
                /*if(i == 0) { 
                    // 首行
                    row.createCell(j).setCellValue("column" + j);
                } else {
                    
                }*/
            }
        }
        System.out.println("结束");
//        FileOutputStream out = new FileOutputStream("D://daochudata.xlsx");
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String qianPath = realPath+File.separator+"downloadExcels";
        File pathFile = new File(qianPath);

		if (!pathFile.exists() && !pathFile.isDirectory()) {
			pathFile.mkdirs();
		}
		
        String shengchengFilePath = qianPath+File.separator+uuid+".xlsx";
        FileOutputStream out = new FileOutputStream(shengchengFilePath);
        sxssfWorkbook.write(out);
        //关闭，原先这一句没有
        sxssfWorkbook.close();
        out.close();
        return shengchengFilePath;
    }
	
}
