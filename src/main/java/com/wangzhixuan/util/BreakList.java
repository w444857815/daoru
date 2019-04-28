package com.wangzhixuan.util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.wangzhixuan.model.XsDicContent;

public class BreakList {  
    public static void main(String[] args) {  
        List<XsDicContent> tarArr = new ArrayList<XsDicContent>();  
//        tarArr.add("a");  
//        tarArr.add("b");  
//        tarArr.add("c");  
//        tarArr.add("d");  
//        tarArr.add("e");  
//        tarArr.add("f");  
//        tarArr.add("g");  
//        tarArr.add("h");  
        
        List<List<XsDicContent>> result = createListByUnitSize(tarArr, 4);  
        System.out.println("分成了"+result.size()+"个");
        for(List<XsDicContent> subArr:result) {  
        	System.out.println("每个里面有"+subArr.size()+"个");
            for(XsDicContent str:subArr) {  
                System.out.println(str);  
            }  
        } 
        
        System.out.println("分割线");
        result = createListBySizeNum(tarArr,4);
        System.out.println("分成了"+result.size()+"个");
        for(List<XsDicContent> subArr:result) {  
        	System.out.println("每个里面有"+subArr.size()+"个");
            for(XsDicContent str:subArr) {  
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
    public static List<List<XsDicContent>>  createListByUnitSize(List<XsDicContent> sourceList,int size) {  
        List<List<XsDicContent>> listArr = new ArrayList<List<XsDicContent>>();  
        //获取被拆分的数组个数  
        int arrSize = sourceList.size()%size==0?sourceList.size()/size:sourceList.size()/size+1;  
        for(int i=0;i<arrSize;i++) {  
            List<XsDicContent>  sub = new ArrayList<XsDicContent>();  
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
    public static List<List<XsDicContent>>  createListBySizeNum(List<XsDicContent> sourceList,int nums) {
    	
    	List<List<XsDicContent>> result=new ArrayList<List<XsDicContent>>();
		int remaider=sourceList.size()%nums;  //(先计算出余数)
		int number=sourceList.size()/nums;  //然后是商
		int offset=0;//偏移量
		for(int i=0;i<nums;i++){
			List<XsDicContent> value=null;
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