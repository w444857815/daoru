package com.wangzhixuan.util;

import java.io.File;

import jxl.Sheet;
import jxl.Workbook;

public class daoruexcel {
	public static void main(String[] args) {
		daoru();
	}

	private static void daoru() {
//		List<StuEntity> list=new ArrayList<StuEntity>();
		
		File file = new File("D:\\test.xls");
        try {
            Workbook rwb=Workbook.getWorkbook(file);
            Sheet rs=rwb.getSheet("Sheet1");//或者rwb.getSheet(0)
            int clos=rs.getColumns();//得到所有的列
            int rows=rs.getRows();//得到所有的行
            
            System.out.println(clos+" rows:"+rows);
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < clos; j++) {
                    //第一个是列数，第二个是行数
                    String id=rs.getCell(j, i).getContents();//默认最左边编号也算一列 所以这里得j++
                    String name=rs.getCell(j, i).getContents();
                    String sex=rs.getCell(j, i).getContents();
                    String num=rs.getCell(j, i).getContents();
                    
                    System.out.println("id:"+id+" name:"+name+" sex:"+sex+" num:"+num);
//                    list.add(new StuEntity(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
	}
}
