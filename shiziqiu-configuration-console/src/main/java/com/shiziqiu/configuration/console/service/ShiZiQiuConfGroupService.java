package com.shiziqiu.configuration.console.service;

import java.util.List;

import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;

/**
 * @title : ShiZiQiuConfGroupService
 * @author : crazy
 * @date : 2017年9月7日 上午9:41:49
 * @Fun :
 */
public interface ShiZiQiuConfGroupService {

	public List<ShiZiQiuConfGroup> findAll();

	public int save(ShiZiQiuConfGroup group);

	public int update(ShiZiQiuConfGroup group);

	public int remove(String groupName);

	public ShiZiQiuConfGroup get(String groupName);
}
