package com.wangzhixuan.model;

import com.baomidou.mybatisplus.enums.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ruwei.wang
 * @since 2017-06-12
 */
@TableName("dr_kfd_mx")
public class DrKfdMx extends Model<DrKfdMx> {

    private static final long serialVersionUID = 1L;

	@TableId(value="id", type= IdType.AUTO)
	private Long id;
    /**
     * 计数
     */
	private String jishu;
    /**
     * 时间
     */
	@TableField("jl_time")
	private Date jlTime;
    /**
     * 品名
     */
	@TableField("goods_name")
	private String goodsName;
    /**
     * 规格
     */
	@TableField("goods_guige")
	private String goodsGuige;
    /**
     * 单位
     */
	@TableField("goods_unit")
	private String goodsUnit;
    /**
     * 数量
     */
	@TableField("goods_num")
	private Integer goodsNum;
    /**
     * 店名
     */
	@TableField("shop_name")
	private String shopName;
    /**
     * 放货地址
     */
	@TableField("shop_address")
	private String shopAddress;
    /**
     * 联系人
     */
	@TableField("shop_lxr")
	private String shopLxr;
    /**
     * 联系电话
     */
	@TableField("shop_lxrmobile")
	private String shopLxrmobile;
    /**
     * 是否付款
     */
	@TableField("if_pay")
	private String ifPay;
    /**
     * 单据是否收回
     */
	@TableField("if_bills_sh")
	private String ifBillsSh;
    /**
     * 店铺属性
     */
	@TableField("shop_property")
	private String shopProperty;
    /**
     * 是否撤店
     */
	@TableField("shop_disappear")
	private String shopDisappear;
    /**
     * 责任人
     */
	@TableField("shop_zrr")
	private String shopZrr;
    /**
     * 备注
     */
	private String remark;
    /**
     * 赠品发放（打火机）
     */
	private String zpff;
	
	/**
     * 状态
     */
	private Integer status;
	
    /**
     * 创建时间
     */
	@TableField("create_time")
	private Date createTime;
	@TableField("update_time")
	private Date updateTime;
	@TableField("delete_flag")
	private Integer deleteFlag;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJishu() {
		return jishu;
	}

	public void setJishu(String jishu) {
		this.jishu = jishu;
	}

	public Date getJlTime() {
		return jlTime;
	}

	public void setJlTime(Date jlTime) {
		this.jlTime = jlTime;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsGuige() {
		return goodsGuige;
	}

	public void setGoodsGuige(String goodsGuige) {
		this.goodsGuige = goodsGuige;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public Integer getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(Integer goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getShopAddress() {
		return shopAddress;
	}

	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}

	public String getShopLxr() {
		return shopLxr;
	}

	public void setShopLxr(String shopLxr) {
		this.shopLxr = shopLxr;
	}

	public String getShopLxrmobile() {
		return shopLxrmobile;
	}

	public void setShopLxrmobile(String shopLxrmobile) {
		this.shopLxrmobile = shopLxrmobile;
	}

	public String getIfPay() {
		return ifPay;
	}

	public void setIfPay(String ifPay) {
		this.ifPay = ifPay;
	}

	public String getIfBillsSh() {
		return ifBillsSh;
	}

	public void setIfBillsSh(String ifBillsSh) {
		this.ifBillsSh = ifBillsSh;
	}

	public String getShopProperty() {
		return shopProperty;
	}

	public void setShopProperty(String shopProperty) {
		this.shopProperty = shopProperty;
	}

	public String getShopDisappear() {
		return shopDisappear;
	}

	public void setShopDisappear(String shopDisappear) {
		this.shopDisappear = shopDisappear;
	}

	public String getShopZrr() {
		return shopZrr;
	}

	public void setShopZrr(String shopZrr) {
		this.shopZrr = shopZrr;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getZpff() {
		return zpff;
	}

	public void setZpff(String zpff) {
		this.zpff = zpff;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	protected Serializable pkVal() {
		return this.id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	

}
