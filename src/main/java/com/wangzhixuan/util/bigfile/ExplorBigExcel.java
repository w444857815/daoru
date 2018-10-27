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
	
	public static String exportExcel(List<DbConfigTable> list,String tableEnd,String realPath) throws IOException {
        XSSFWorkbook workbook1 = new XSSFWorkbook(new FileInputStream(new File(realPath+"/exportExcelTemplent/daochTemp.xlsx")));
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(workbook1, 100);
        Sheet first = sxssfWorkbook.getSheetAt(0);
        int size = list.size();
        int col ;
        if(tableEnd.startsWith("one")){
        	col = 11;
        }else{
        	col = 28;
        }
        /*Row firstrow = first.createRow(0);
        firstrow.createCell(0).setCellValue("column" + 0);
        firstrow.createCell(1).setCellValue("column" + 1);
        firstrow.createCell(2).setCellValue("column" + 2);
        firstrow.createCell(3).setCellValue("column" + 3);
        firstrow.createCell(4).setCellValue("column" + 4);
        firstrow.createCell(5).setCellValue("column" + 5);
        firstrow.createCell(6).setCellValue("column" + 6);*/
        for (int i = 0; i < size; i++) {
            Row row = first.createRow(i);
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
        System.out.println("1");
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
        out.close();
        return shengchengFilePath;
    }
	
}
