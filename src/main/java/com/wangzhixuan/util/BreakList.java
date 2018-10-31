package com.wangzhixuan.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.wangzhixuan.model.DbConfigTable;

public class BreakList {  
    public static void main(String[] args) {  
        List<DbConfigTable> tarArr = new ArrayList<DbConfigTable>();  
//        tarArr.add("a");  
//        tarArr.add("b");  
//        tarArr.add("c");  
//        tarArr.add("d");  
//        tarArr.add("e");  
//        tarArr.add("f");  
//        tarArr.add("g");  
//        tarArr.add("h");  
        
        List<List<DbConfigTable>> result = createListByUnitSize(tarArr, 4);  
        System.out.println("分成了"+result.size()+"个");
        for(List<DbConfigTable> subArr:result) {  
        	System.out.println("每个里面有"+subArr.size()+"个");
            for(DbConfigTable str:subArr) {  
                System.out.println(str);  
            }  
        } 
        
        System.out.println("分割线");
        result = createListBySizeNum(tarArr,4);
        System.out.println("分成了"+result.size()+"个");
        for(List<DbConfigTable> subArr:result) {  
        	System.out.println("每个里面有"+subArr.size()+"个");
            for(DbConfigTable str:subArr) {  
                System.out.println(str);  
            }  
        } 
    }  
      
    /**
     * 
     * @Title:BreakList.java
     * @Package com.wangzhixuan.util
     * @Description:每个里面放N个元素，最后生成的个数不知
     * @author wangruwei
     * @date 2018年10月23日上午11:57:44
     * @param sourceList	源数据
     * @param size		每个里面要放的个数
     * @return	
     * @version V1.0
     */
    public static List<List<DbConfigTable>>  createListByUnitSize(List<DbConfigTable> sourceList,int size) {  
        List<List<DbConfigTable>> listArr = new ArrayList<List<DbConfigTable>>();  
        //获取被拆分的数组个数  
        int arrSize = sourceList.size()%size==0?sourceList.size()/size:sourceList.size()/size+1;  
        for(int i=0;i<arrSize;i++) {  
            List<DbConfigTable>  sub = new ArrayList<DbConfigTable>();  
            //把指定索引数据放入到list中  
            for(int j=i*size;j<=size*(i+1)-1;j++) {
                if(j<=sourceList.size()-1) {  
                    sub.add(sourceList.get(j));  
                }  
            }  
            listArr.add(sub);  
        }  
        return listArr;  
    }  
    
    /**
     * 
     * @Title:BreakList.java
     * @Package com.wangzhixuan.util
     * @Description:每个里面放N个元素，最后生成的个数不知
     * @author wangruwei
     * @date 2018年10月31日下午2:16:22
     * @param sourceList	源数据
     * @param size			每个里面要放的个数
     * @return
     * @version V1.0
     */
    public static List<List<String[]>>  createListObjectByUnitSize(List<String[]> sourceList,int size) {
    	List<List<String[]>> listArr = new ArrayList<List<String[]>>();  
        //获取被拆分的数组个数  
        int arrSize = sourceList.size()%size==0?sourceList.size()/size:sourceList.size()/size+1;  
        
        for(int i=0;i<arrSize;i++) {  
        	List<String[]> sub = new ArrayList<String[]>();  
            //把指定索引数据放入到list中  
            for(int j=i*size;j<=size*(i+1)-1;j++) {
                if(j<=sourceList.size()-1) {
                    sub.add(sourceList.get(j));
                }
            }
            listArr.add(sub);
        }
        return listArr;
    }  
    
    
    
    
    /**
     * 
     * @Title:BreakList.java
     * @Package com.wangzhixuan.util
     * @Description:要分成几个list
     * @author wangruwei
     * @date 2018年10月23日下午2:47:23
     * @param sourceList	数据源
     * @param sizeNum	要分成几个list
     * @return
     * @version V1.0
     */
    public static List<List<DbConfigTable>>  createListBySizeNum(List<DbConfigTable> sourceList,int nums) {
    	
    	List<List<DbConfigTable>> result=new ArrayList<List<DbConfigTable>>();
		int remaider=sourceList.size()%nums;  //(先计算出余数)
		int number=sourceList.size()/nums;  //然后是商
		int offset=0;//偏移量
		for(int i=0;i<nums;i++){
			List<DbConfigTable> value=null;
			if(remaider>0){
				value=sourceList.subList(i*number+offset, (i+1)*number+offset+1);
				remaider--;
				offset++;
			}else{
				value=sourceList.subList(i*number+offset, (i+1)*number+offset);
			}
			result.add(value);
		}
		return result;
    }  
    
    
}