package com.wangzhixuan.model;

public class XsType extends BaseModel{
	
	private static final long serialVersionUID = 5454155825314635342L;
	
	

	//columns START
    /**
     * id  
     */ 	
	private Integer id;
    /**
     * 名称  
     */ 	
	private String typeName;
    /**
     * 要爬取的地址(要直接能打开)  
     */ 	
	private String getUrl;
	/**
     * 是否已获取，默认0  ，0没有，1已获取  
     */ 	
	private String status;
	//columns END

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public String getTypeName() {
		return this.typeName;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getGetUrl() {
		return this.getUrl;
	}
	
	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}

