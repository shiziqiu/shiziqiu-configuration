package com.shiziqiu.configuration.console.model;

import java.io.Serializable;

/**
 * @title : ShiZiQiuConfGroup
 * @author : crazy
 * @date : 2017年9月6日 下午7:39:49
 * @Fun :
 */
public class ShiZiQiuConfGroup implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 112412412412412L;
	
	private String groupName;
	private String groupTitle;
	 
	public ShiZiQiuConfGroup() {
		super();
	}

	public ShiZiQiuConfGroup(String groupName, String groupTitle) {
		super();
		this.groupName = groupName;
		this.groupTitle = groupTitle;
	}
	
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getGroupTitle() {
		return groupTitle;
	}
	public void setGroupTitle(String groupTitle) {
		this.groupTitle = groupTitle;
	}
	 
}
