package com.shiziqiu.configuration.console.model;

import java.io.Serializable;

import com.shiziqiu.configuration.core.zk.ShiZiQiuZkConfClient;
/**
 * @title : ShiZiQiuConfNode
 * @author : crazy
 * @date : 2017年9月6日 下午7:42:02
 * @Fun :
 */
public class ShiZiQiuConfNode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 132434523L;
	
	private String nodeGroup;
	private String nodeKey;
	private String nodeValue;
	private String nodeDesc;

	private String groupKey;
	private String nodeValueReal;
	
	public ShiZiQiuConfNode() {
		super();
	}
	
	public ShiZiQiuConfNode(String nodeGroup, String nodeKey, String nodeValue,
			String nodeDesc, String groupKey, String nodeValueReal) {
		super();
		this.nodeGroup = nodeGroup;
		this.nodeKey = nodeKey;
		this.nodeValue = nodeValue;
		this.nodeDesc = nodeDesc;
		this.groupKey = groupKey;
		this.nodeValueReal = nodeValueReal;
	}

	public String getNodeGroup() {
		return nodeGroup;
	}
	public void setNodeGroup(String nodeGroup) {
		this.nodeGroup = nodeGroup;
	}
	public String getNodeKey() {
		return nodeKey;
	}
	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}
	public String getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(String nodeValue) {
		this.nodeValue = nodeValue;
	}
	public String getNodeDesc() {
		return nodeDesc;
	}
	public void setNodeDesc(String nodeDesc) {
		this.nodeDesc = nodeDesc;
	}
	public String getGroupKey() {
		return ShiZiQiuZkConfClient.generateGroupKey(nodeGroup, nodeKey);
	}
	public void setGroupKey(String groupKey) {
		this.groupKey = groupKey;
	}
	public String getNodeValueReal() {
		return nodeValueReal;
	}
	public void setNodeValueReal(String nodeValueReal) {
		this.nodeValueReal = nodeValueReal;
	}
	
}
