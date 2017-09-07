package com.shiziqiu.configuration.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.shiziqiu.configuration.console.dao.ShiZiQiuConfNodeDao;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfNode;

/**
 * @title : ShiZiQiuConfNodeDaoImpl
 * @author : crazy
 * @date : 2017年9月6日 下午7:46:24
 * @Fun :
 */
@Repository
public class ShiZiQiuConfNodeDaoImpl implements ShiZiQiuConfNodeDao{

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	@Override
	public List<ShiZiQiuConfNode> pageList(int offset, int pagesize, String nodeGroup, String nodeKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("nodeGroup", nodeGroup);
		params.put("nodeKey", nodeKey);
		return sqlSessionTemplate.selectList("ShiZiQiuConfNodeMapper.pageList", params);
	}

	@Override
	public int pageListCount(int offset, int pagesize, String nodeGroup,
			String nodeKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("nodeGroup", nodeGroup);
		params.put("nodeKey", nodeKey);
		return sqlSessionTemplate.selectOne("ShiZiQiuConfNodeMapper.pageListCount", params);
	}

	@Override
	public int deleteByKey(String nodeGroup, String nodeKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeGroup", nodeGroup);
		params.put("nodeKey", nodeKey);
		return sqlSessionTemplate.delete("ShiZiQiuConfNodeMapper.deleteByKey", params);
	}

	@Override
	public void insert(ShiZiQiuConfNode xxlConfNode) {
		sqlSessionTemplate.insert("ShiZiQiuConfNodeMapper.insert", xxlConfNode);
	}

	@Override
	public ShiZiQiuConfNode selectByKey(String nodeGroup, String nodeKey) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("nodeGroup", nodeGroup);
		params.put("nodeKey", nodeKey);
		return sqlSessionTemplate.selectOne("ShiZiQiuConfNodeMapper.selectByKey", params);
	}

	@Override
	public int update(ShiZiQiuConfNode xxlConfNode) {
		return sqlSessionTemplate.update("ShiZiQiuConfNodeMapper.update", xxlConfNode);
	}

}
