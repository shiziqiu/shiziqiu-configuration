package com.shiziqiu.configuration.console.dao;

import java.util.List;

import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;

/**
 * @title : ShiZiQiuConfGroupDao
 * @author : crazy
 * @date : 2017年9月6日 下午7:57:13
 * @Fun :
 */
public interface ShiZiQiuConfGroupDao {

	public List<ShiZiQiuConfGroup> findAll();

	public int save(ShiZiQiuConfGroup group);

	public int update(ShiZiQiuConfGroup group);

	public int remove(String groupName);

	public ShiZiQiuConfGroup load(String groupName);
}
