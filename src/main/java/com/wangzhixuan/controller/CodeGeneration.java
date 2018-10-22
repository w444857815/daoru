package com.wangzhixuan.controller;

import java.io.File;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wangzhixuan.commons.ueditor.UeditorService;
import com.wangzhixuan.commons.utils.StringUtils;
import com.wangzhixuan.model.DrKfdMx;

import jxl.Sheet;
import jxl.Workbook;
import net.sf.json.JSONObject;
  
/** 
 * 文件上传控制器 
 *  
 * @author Chris Mao(Zibing) 
 * 
 */  
@Controller  
@RequestMapping(value = "/codegeneration")  
public class CodeGeneration {
	
	@Autowired
    private UeditorService ueditorService;
	
	
	@GetMapping("/toconfigpage")
    public String addPage() {
        return "configpage";
    }
	
	
	/**
	 * 导入开发店铺明细
	 */
	
	
	@RequestMapping("daoruKfdMx")
    public ResponseEntity<String> daoruKfdMx(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_HTML);
        String json = ueditorService.exec(request);
        
        JSONObject json_obj = JSONObject.fromObject(json);
        if("SUCCESS".equals(json_obj.get("state"))){
        	
        	File file = new File(request.getSession().getServletContext().getRealPath("/")+json_obj.get("url")+"");
        	try {
                Workbook rwb=Workbook.getWorkbook(file);
                Sheet rs=rwb.getSheet("开发店铺明细");//或者rwb.getSheet(0)
                int clos=rs.getColumns();//得到所有的列
                int rows=rs.getRows();//得到所有的行
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                System.out.println(clos+" rows:"+rows);
                //获取行
                for (int i = 2; i < rows; i++) {
                	//获取列
                    for (int j = 0; j < clos; j++) {
                    	System.out.println("第"+(i+1)+"行");
                    	
                    	DrKfdMx drkfdmx = new DrKfdMx();
                    	
                        //第一个是列数，第二个是行数
                        String jishu=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
                        drkfdmx.setJishu(jishu);
                        //时间5月22日
                        String shijian=rs.getCell(j++, i).getContents();
                        
                        shijian = shijian.replace("月", "-").replace("日", "-");
                        //时间 2017年
                        j++;
                        String nian=rs.getCell(j++, i).getContents();
                        nian = nian.replace("年", "-");
                        String zongsj = nian+shijian;
                       
                        if(!StringUtils.isBlank(shijian)&&!StringUtils.isBlank(nian)){
                        	drkfdmx.setJlTime(sdf.parse(zongsj));
                        }
                        
                        
                        
                        
                        //goods_name
                        String goods_name=rs.getCell(j++, i).getContents();
                        drkfdmx.setGoodsName(goods_name);
                        //goods_guige
                        String goods_guige=rs.getCell(j++, i).getContents();
                        drkfdmx.setGoodsGuige(goods_guige);
                      //goods_unit
                        String goods_unit=rs.getCell(j++, i).getContents();
                        drkfdmx.setGoodsUnit(goods_unit);
                      //goods_num
                        String goods_num=rs.getCell(j++, i).getContents();
                        if(isInteger(goods_num)){
                        	drkfdmx.setGoodsNum(Integer.parseInt(goods_num));
                        }else{
                        	drkfdmx.setGoodsNum(0);
                        }
                        drkfdmx.setGoodsNum(Integer.parseInt(goods_num));
                      //shop_name
                        String shop_name=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopName(shop_name);
                      //shop_address
                        String shop_address=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopAddress(shop_address);
                      //shop_lxr
                        String shop_lxr=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopLxr(shop_lxr);
                        //shop_lxrmobile
                        String shop_lxrmobile=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopLxrmobile(shop_lxrmobile);
                        //if_pay
                        String if_pay=rs.getCell(j++, i).getContents();
                        drkfdmx.setIfPay(if_pay);
                        //if_bills_sh
                        String if_bills_sh=rs.getCell(j++, i).getContents();
                        drkfdmx.setIfBillsSh(if_bills_sh);
                        //shop_property
                        String shop_property=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopProperty(shop_property);
                        //shop_disappear
                        String shop_disappear=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopDisappear(shop_disappear);
                        //shop_zrr
                        String shop_zrr=rs.getCell(j++, i).getContents();
                        drkfdmx.setShopZrr(shop_zrr);
                        //remark
                        String remark=rs.getCell(j++, i).getContents();
                        drkfdmx.setRemark(remark);
                        //zpff
                        String zpff=rs.getCell(j++, i).getContents();
                        drkfdmx.setZpff(zpff);
                        
                        
                        
                        
//                    	drKfdMxService.insert(drkfdmx);
                    	
                        
//                        System.out.println("id:"+id+" name:"+name+" sex:"+sex+" num:"+num);
//                        list.add(new StuEntity(Integer.parseInt(id), name, sex, Integer.parseInt(num)));
                    }
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } 
        }
        	
        
        
        return new ResponseEntity<String>(json, headers, HttpStatus.OK);
    }
	
	
	public static boolean isInteger(String str){
		return str.matches("[0-9]+");
	}

}  