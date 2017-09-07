package com.shiziqiu.configuration.console.service;

import java.util.Map;

import com.shiziqiu.configuration.console.model.ResultT;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfNode;

/**
 * @title : ShiZiQiuConfNodeService
 * @author : crazy
 * @date : 2017年9月6日 下午8:07:04
 * @Fun :
 */
public interface ShiZiQiuConfNodeService {

	public Map<String,Object> pageList(int offset, int pagesize, String nodeGroup, String nodeKey);

	public ResultT<String> deleteByKey(String nodeGroup, String nodeKey);

	public ResultT<String> add(ShiZiQiuConfNode node);

	public ResultT<String> update(ShiZiQiuConfNode node);

	public int pageListCount(int offset, int pagesize, String groupName, String nodeKey);
}
