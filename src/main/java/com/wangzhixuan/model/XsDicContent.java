package com.wangzhixuan.model;

import java.util.Date;

public class XsDicContent extends BaseModel{
	
	private static final long serialVersionUID = 5454155825314635342L;
	
	

	//columns START
    /**
     * id  
     */ 	
	private Integer id;
	/**
     * 所属类型id  
     */ 	
	private Integer typeId;
    /**
     * 所属类型名称  
     */ 	
	private String typeName;
    /**
     * 所属书id  
     */ 	
	private Integer bookId;
	/**
     * 书名字  
     */ 	
	private String bookName;
    /**
     * 章节名称  
     */ 	
	private String dicName;
    /**
     * 抽取链接地址  
     */ 	
	private String dicGeturl;
    /**
     * 是否获取（1获取了，0未获取），默认0  
     */ 	
	private Integer isGet;
    /**
     * 点击数  
     */ 	
	private Integer voteNum;
    /**
     * 是否生成静态 ，0未生成，1生成  
     */ 	
	private String isCreatefile;
    /**
     * 内容  
     */ 	
	private String content;
    /**
     * createTime  
     */ 	
	private Date createTime;
    /**
     * cuser  
     */ 	
	private String cuser;
	//columns END

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return this.id;
	}

	public Integer getBookId() {
		return this.bookId;
	}
	
	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}
	public String getDicName() {
		return this.dicName;
	}
	
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getDicGeturl() {
		return this.dicGeturl;
	}
	
	public void setDicGeturl(String dicGeturl) {
		this.dicGeturl = dicGeturl;
	}
	public Integer getIsGet() {
		return this.isGet;
	}
	
	public void setIsGet(Integer isGet) {
		this.isGet = isGet;
	}
	public Integer getVoteNum() {
		return this.voteNum;
	}
	
	public void setVoteNum(Integer voteNum) {
		this.voteNum = voteNum;
	}
	public String getIsCreatefile() {
		return this.isCreatefile;
	}
	
	public void setIsCreatefile(String isCreatefile) {
		this.isCreatefile = isCreatefile;
	}
	public String getContent() {
		return this.content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreateTime() {
		return this.createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCuser() {
		return this.cuser;
	}
	
	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

}

