package com.campraynotice.dto;

/**
 * @author silvasong E-mail:silvasong@campray.com
 * @version 2015年3月9日 下午2:50:07
 * 
 */
public class Info {
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
    
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	private int id;
	
	private String title;
	
	private int sort;
	
	private String content;
	
	private long createTime;
	
	
	
	

}
