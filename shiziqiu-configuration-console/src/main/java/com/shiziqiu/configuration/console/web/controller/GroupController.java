package com.shiziqiu.configuration.console.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiziqiu.configuration.console.model.ResultT;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;
import com.shiziqiu.configuration.console.service.ShiZiQiuConfGroupService;
import com.shiziqiu.configuration.console.service.ShiZiQiuConfNodeService;
import com.shiziqiu.configuration.util.StringUtils;
/**
 * @title : GroupController
 * @author : crazy
 * @date : 2017年9月7日 上午9:57:18
 * @Fun :
 */
@Controller
@RequestMapping("/group")
public class GroupController {
	
	@Resource
	private ShiZiQiuConfNodeService shiZiQiuConfNodeService;
	@Resource
	private ShiZiQiuConfGroupService shiZiQiuConfGroupService;
	
	@RequestMapping
	public String index(Model model) {
		List<ShiZiQiuConfGroup> list = shiZiQiuConfGroupService.findAll();
		model.addAttribute("list", list);
		return "group/index";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public ResultT<String> save(ShiZiQiuConfGroup shiZiQiuConfGroup){

		if (null == shiZiQiuConfGroup.getGroupName() || StringUtils.isBlank(shiZiQiuConfGroup.getGroupName())) {
			return new ResultT<String>(500, "请输入分组");
		}
		if (shiZiQiuConfGroup.getGroupName().length()<4 || shiZiQiuConfGroup.getGroupName().length()>100) {
			return new ResultT<String>(500, "分组长度限制为4~100");
		}
		if (null == shiZiQiuConfGroup.getGroupTitle() || StringUtils.isBlank(shiZiQiuConfGroup.getGroupTitle())) {
			return new ResultT<String>(500, "请输入分组名称");
		}

		if (shiZiQiuConfGroupService.get(shiZiQiuConfGroup.getGroupName()) != null) {
			return new ResultT<String>(500, "分组已存在,请勿重复添加");
		}

		int code = shiZiQiuConfGroupService.save(shiZiQiuConfGroup);
		return (code > 0) ? ResultT.SUCCESS : ResultT.FAIL;
	}

	@RequestMapping("/update")
	@ResponseBody
	public ResultT<String> update(ShiZiQiuConfGroup shiZiQiuConfGroup){

		if (shiZiQiuConfGroup.getGroupName()==null || StringUtils.isBlank(shiZiQiuConfGroup.getGroupName())) {
			return new ResultT<String>(500, "请输入GroupName");
		}
		if (shiZiQiuConfGroup.getGroupName().length() < 4 || shiZiQiuConfGroup.getGroupName().length() > 100) {
			return new ResultT<String>(500, "分组长度限制为4~100");
		}
		if (null == shiZiQiuConfGroup.getGroupTitle() || StringUtils.isBlank(shiZiQiuConfGroup.getGroupTitle())) {
			return new ResultT<String>(500, "请输入分组名称");
		}

		int code = shiZiQiuConfGroupService.update(shiZiQiuConfGroup);
		return (code > 0) ? ResultT.SUCCESS : ResultT.FAIL;
	}

	@RequestMapping("/remove")
	@ResponseBody
	public ResultT<String> remove(String groupName){

		int count = shiZiQiuConfNodeService.pageListCount(0, 10, groupName, null);
		if (count > 0) {
			return new ResultT<String>(500, "该分组使用中, 不可删除");
		}

		List<ShiZiQiuConfGroup> groupList = shiZiQiuConfGroupService.findAll();
		if (null != groupList && groupList.size() == 1) {
			return new ResultT<String>(500, "删除失败, 系统需要至少预留一个默认分组");
		}

		int code = shiZiQiuConfGroupService.remove(groupName);
		return (code > 0) ? ResultT.SUCCESS : ResultT.FAIL;
	}

}
