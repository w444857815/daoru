package com.wangzhixuan.model;

import java.util.Date;

public class XsBook extends BaseModel{
	
	private static final long serialVersionUID = 5454155825314635342L;
	
	

	//columns START
    /**
     * id  
     */ 	
	private Integer id;
    /**
     * 标题  
     */ 	
	private String title;
    /**
     * 副标题  
     */ 	
	private String titleSub;
    /**
     * 获取地址  
     */ 	
	private String getUrl;
	/**
     * 获取目录状态,0未获取 ， 1已获取  
     */ 	
	private String getStatus;
    /**
     * 类型所属id，这个后期可能放成多个，一个书可能属于多种类型  
     */ 	
	private String typeId;
    /**
     * 书所属类型(这里更像标签)  
     */ 	
	private String titleTag;
    /**
     * 作者  
     */ 	
	private String author;
    /**
     * 作者简介  
     */ 	
	private String authorSub;
    /**
     * 字数  
     */ 	
	private String wordsNum;
    /**
     * 书的状态，已完结,未完结  
     */ 	
	private String status;
    /**
     * 书简介  
     */ 	
	private String bookIntro;
    /**
     * 最新章节目录  
     */ 	
	private String lastNewChapter;
    /**
     * 最新章节获取地址  
     */ 	
	private String lastNewChapterGetUrl;
    /**
     * 最新章节目录更新时间  
     */ 	
	private String lastNewChapterTime;
    /**
     * ctime  
     */ 	
	private Date ctime;
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

	public String getTitle() {
		return this.title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitleSub() {
		return this.titleSub;
	}
	
	public void setTitleSub(String titleSub) {
		this.titleSub = titleSub;
	}
	public String getGetUrl() {
		return this.getUrl;
	}
	
	public void setGetUrl(String getUrl) {
		this.getUrl = getUrl;
	}
	
	
	public String getGetStatus() {
		return getStatus;
	}

	public void setGetStatus(String getStatus) {
		this.getStatus = getStatus;
	}

	public String getTypeId() {
		return this.typeId;
	}
	
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	public String getTitleTag() {
		return this.titleTag;
	}
	
	public void setTitleTag(String titleTag) {
		this.titleTag = titleTag;
	}
	public String getAuthor() {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getAuthorSub() {
		return this.authorSub;
	}
	
	public void setAuthorSub(String authorSub) {
		this.authorSub = authorSub;
	}
	public String getWordsNum() {
		return this.wordsNum;
	}
	
	public void setWordsNum(String wordsNum) {
		this.wordsNum = wordsNum;
	}
	public String getStatus() {
		return this.status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBookIntro() {
		return this.bookIntro;
	}
	
	public void setBookIntro(String bookIntro) {
		this.bookIntro = bookIntro;
	}
	public String getLastNewChapter() {
		return this.lastNewChapter;
	}
	
	public void setLastNewChapter(String lastNewChapter) {
		this.lastNewChapter = lastNewChapter;
	}
	public String getLastNewChapterGetUrl() {
		return this.lastNewChapterGetUrl;
	}
	
	public void setLastNewChapterGetUrl(String lastNewChapterGetUrl) {
		this.lastNewChapterGetUrl = lastNewChapterGetUrl;
	}
	public String getLastNewChapterTime() {
		return this.lastNewChapterTime;
	}
	
	public void setLastNewChapterTime(String lastNewChapterTime) {
		this.lastNewChapterTime = lastNewChapterTime;
	}
	public Date getCtime() {
		return this.ctime;
	}
	
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getCuser() {
		return this.cuser;
	}
	
	public void setCuser(String cuser) {
		this.cuser = cuser;
	}

}

