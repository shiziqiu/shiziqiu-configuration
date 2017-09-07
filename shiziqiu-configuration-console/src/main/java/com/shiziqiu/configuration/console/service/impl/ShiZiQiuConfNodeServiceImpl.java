package com.shiziqiu.configuration.console.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiziqiu.configuration.console.dao.ShiZiQiuConfGroupDao;
import com.shiziqiu.configuration.console.dao.ShiZiQiuConfNodeDao;
import com.shiziqiu.configuration.console.model.ResultT;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfNode;
import com.shiziqiu.configuration.console.service.ShiZiQiuConfNodeService;
import com.shiziqiu.configuration.core.zk.ShiZiQiuZkConfClient;
/**
 * @title : ShiZiQiuConfNodeServiceImpl
 * @author : crazy
 * @date : 2017年9月6日 下午8:13:59
 * @Fun :
 */
@Service("shiZiQiuConfNodeService")
public class ShiZiQiuConfNodeServiceImpl implements ShiZiQiuConfNodeService{

	@Autowired
	private ShiZiQiuConfNodeDao shiZiQiuConfNodeDao;
	
	@Autowired
	private ShiZiQiuConfGroupDao shiZiQiuConfGroupDao;
	
	@Override
	public Map<String, Object> pageList(int offset, int pagesize, String nodeGroup, String nodeKey) {
		
		List<ShiZiQiuConfNode> data = shiZiQiuConfNodeDao.pageList(offset, pagesize, nodeGroup, nodeKey);
		int count = shiZiQiuConfNodeDao.pageListCount(offset, pagesize, nodeGroup, nodeKey);

		if (null != data && data.size() > 0) {
			for (ShiZiQiuConfNode node: data) {
				String realNodeValue = ShiZiQiuZkConfClient.getPathDataByKey(node.getGroupKey());
				node.setNodeValueReal(realNodeValue);
			}
		}
		Map<String, Object> maps = new HashMap<String, Object>();
		maps.put("data", data);
		maps.put("recordsTotal", count);	// 总记录数
		maps.put("recordsFiltered", count);	// 过滤后的总记录数
		return maps;
	}

	@Override
	public ResultT<String> deleteByKey(String nodeGroup, String nodeKey) {
		if (StringUtils.isBlank(nodeGroup) && StringUtils.isBlank(nodeKey)) {
			return new ResultT<String>(500, "参数缺失");
		}

		shiZiQiuConfNodeDao.deleteByKey(nodeGroup, nodeKey);
		String groupKey = ShiZiQiuZkConfClient.generateGroupKey(nodeGroup, nodeKey);
		ShiZiQiuZkConfClient.deletePathByKey(groupKey);

		return ResultT.SUCCESS;
	}

	@Override
	public ResultT<String> add(ShiZiQiuConfNode node) {
		if (node == null) {
			return new ResultT<String>(500, "参数缺失");
		}
		if (StringUtils.isBlank(node.getNodeGroup())) {
			return new ResultT<String>(500, "配置分组不可为空");
		}
		ShiZiQiuConfGroup group = shiZiQiuConfGroupDao.load(node.getNodeGroup());
		if (group==null) {
			return new ResultT<String>(500, "配置分组不存在");
		}
		if (StringUtils.isBlank(node.getNodeKey())) {
			return new ResultT<String>(500, "配置Key不可为空");
		}
		ShiZiQiuConfNode existNode = shiZiQiuConfNodeDao.selectByKey(node.getNodeGroup(), node.getNodeKey());
		if (existNode!=null) {
			return new ResultT<String>(500, "Key对应的配置已经存在,不可重复添加");
		}
		shiZiQiuConfNodeDao.insert(node);

		String groupKey = ShiZiQiuZkConfClient.generateGroupKey(node.getNodeGroup(), node.getNodeKey());
		ShiZiQiuZkConfClient.setPathDataByKey(groupKey, node.getNodeValue());
		return ResultT.SUCCESS;
	}

	@Override
	public ResultT<String> update(ShiZiQiuConfNode node) {
		if (node == null || StringUtils.isBlank(node.getNodeKey())) {
			return new ResultT<String>(500, "Key不可为空");
		}
		int ret = shiZiQiuConfNodeDao.update(node);
		if (ret < 1) {
			return new ResultT<String>(500, "Key对应的配置不存在,请确认");
		}

		String groupKey = ShiZiQiuZkConfClient.generateGroupKey(node.getNodeGroup(), node.getNodeKey());
		ShiZiQiuZkConfClient.setPathDataByKey(groupKey, node.getNodeValue());

		return ResultT.SUCCESS;
	}

	@Override
	public int pageListCount(int offset, int pagesize, String groupName, String nodeKey) {
		return shiZiQiuConfNodeDao.pageListCount(offset, pagesize, groupName, nodeKey);
	}
}
