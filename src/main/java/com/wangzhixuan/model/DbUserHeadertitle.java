package com.wangzhixuan.model;

import java.io.Serializable;

import com.baomidou.mybatisplus.activerecord.Model;

public class DbUserHeadertitle extends Model<DbTableone> {
	
	private static final long serialVersionUID = 5454155825314635342L;
	
	

	//columns START
    /**
     * id  
     */ 	
	private Integer id;
    /**
     * 用户id  
     */ 	
	private Integer userId;
    /**
     * 数据源类型，源1是1  和 源2 是2   
     */ 	
	private Integer tableType;
    /**
     * 第几列，用col1表示  
     */ 	
	private String headerCol;
    /**
     * 表头名称  
     */ 	
	private String headerTitle;
	//columns END

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getTableType() {
		return this.tableType;
	}
	
	public void setTableType(Integer tableType) {
		this.tableType = tableType;
	}
	public String getHeaderCol() {
		return this.headerCol;
	}
	
	public void setHeaderCol(String headerCol) {
		this.headerCol = headerCol;
	}
	public String getHeaderTitle() {
		return this.headerTitle;
	}
	
	public void setHeaderTitle(String headerTitle) {
		this.headerTitle = headerTitle;
	}

	@Override
	protected Serializable pkVal() {
		// TODO Auto-generated method stub
		return null;
	}

}

