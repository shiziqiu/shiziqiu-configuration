package com.shiziqiu.configuration.console.dao;

import java.util.List;

import com.shiziqiu.configuration.console.model.ShiZiQiuConfNode;

/**
 * @title : ShiZiQiuConfNodeDao
 * @author : crazy
 * @date : 2017年9月6日 下午7:45:23
 * @Fun :
 */
public interface ShiZiQiuConfNodeDao {

	public List<ShiZiQiuConfNode> pageList(int offset, int pagesize, String nodeGroup, String nodeKey);
	
	public int pageListCount(int offset, int pagesize, String nodeGroup, String nodeKey);

	public int deleteByKey(String nodeGroup, String nodeKey);

	public void insert(ShiZiQiuConfNode xxlConfNode);

	public ShiZiQiuConfNode selectByKey(String nodeGroup, String nodeKey);

	public int update(ShiZiQiuConfNode xxlConfNode);
}
