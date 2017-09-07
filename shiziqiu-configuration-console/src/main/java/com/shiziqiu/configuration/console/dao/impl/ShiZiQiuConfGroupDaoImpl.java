package com.shiziqiu.configuration.console.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shiziqiu.configuration.console.dao.ShiZiQiuConfGroupDao;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;
/**
 * @title : ShiZiQiuConfGroupDaoImpl
 * @author : crazy
 * @date : 2017年9月6日 下午7:57:58
 * @Fun :
 */
@Repository
public class ShiZiQiuConfGroupDaoImpl implements ShiZiQiuConfGroupDao {

	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<ShiZiQiuConfGroup> findAll() {
		 return sqlSessionTemplate.selectList("ShiZiQiuConfGroupMapper.findAll");
	}

	@Override
	public int save(ShiZiQiuConfGroup group) {
		return sqlSessionTemplate.insert("ShiZiQiuConfGroupMapper.save", group);
	}

	@Override
	public int update(ShiZiQiuConfGroup group) {
		return sqlSessionTemplate.update("ShiZiQiuConfGroupMapper.update", group);
	}

	@Override
	public int remove(String groupName) {
		 return sqlSessionTemplate.delete("ShiZiQiuConfGroupMapper.remove", groupName);
	}

	@Override
	public ShiZiQiuConfGroup load(String groupName) {
		 return sqlSessionTemplate.selectOne("ShiZiQiuConfGroupMapper.load", groupName);
	}

}
