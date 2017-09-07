package com.shiziqiu.configuration.console.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shiziqiu.configuration.console.dao.ShiZiQiuConfGroupDao;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;
import com.shiziqiu.configuration.console.service.ShiZiQiuConfGroupService;
/**
 * @title : ShiZiQiuConfGroupServiceImpl
 * @author : crazy
 * @date : 2017年9月7日 上午9:43:06
 * @Fun : 分组
 */
@Service("shiZiQiuConfGroupService")
public class ShiZiQiuConfGroupServiceImpl implements ShiZiQiuConfGroupService{
	
	@Autowired
	private ShiZiQiuConfGroupDao shiZiQiuConfGroupDao;

	@Override
	public List<ShiZiQiuConfGroup> findAll() {
		return shiZiQiuConfGroupDao.findAll();
	}

	@Override
	public int save(ShiZiQiuConfGroup group) {
		return shiZiQiuConfGroupDao.save(group);
	}

	@Override
	public int update(ShiZiQiuConfGroup group) {
		return shiZiQiuConfGroupDao.update(group);
	}

	@Override
	public int remove(String groupName) {
		return shiZiQiuConfGroupDao.remove(groupName);
	}

	@Override
	public ShiZiQiuConfGroup get(String groupName) {
		return shiZiQiuConfGroupDao.load(groupName);
	}

}
