package com.shiziqiu.configuration.console.web.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shiziqiu.configuration.console.model.ResultT;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfGroup;
import com.shiziqiu.configuration.console.model.ShiZiQiuConfNode;
import com.shiziqiu.configuration.console.service.ShiZiQiuConfGroupService;
import com.shiziqiu.configuration.console.service.ShiZiQiuConfNodeService;
import com.shiziqiu.configuration.console.web.annotation.Permession;

/**
 * @title : ConfController
 * @author : crazy
 * @date : 2017年9月7日 上午9:34:01
 * @Fun :	配置管理
 */
@Controller
@RequestMapping("/conf")
public class ConfController {
	
	@Resource
	private ShiZiQiuConfNodeService shiZiQiuConfNodeService;
	@Resource
	private ShiZiQiuConfGroupService shiZiQiuConfGroupService;
	
	@RequestMapping("")
	@Permession
	public String index(Model model, String znodeKey){
		List<ShiZiQiuConfGroup> list = shiZiQiuConfGroupService.findAll();
		model.addAttribute("XxlConfNodeGroup", list);
		return "conf/index";
	}
	
	@RequestMapping("/pageList")
	@ResponseBody
	@Permession
	public Map<String, Object> pageList(@RequestParam(required = false, defaultValue = "0") int start,
			@RequestParam(required = false, defaultValue = "10") int length,
			String nodeGroup, String nodeKey) {
		return shiZiQiuConfNodeService.pageList(start, length, nodeGroup, nodeKey);
	}
	
	/**
	 * get
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	@Permession
	public ResultT<String> delete(String nodeGroup, String nodeKey){
		return shiZiQiuConfNodeService.deleteByKey(nodeGroup, nodeKey);
	}

	/**
	 * create/update
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	@Permession
	public ResultT<String> add(ShiZiQiuConfNode shiZiQiuConfNode){
		return shiZiQiuConfNodeService.add(shiZiQiuConfNode);
	}
	
	/** 
	 * create/update
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	@Permession
	public ResultT<String> update(ShiZiQiuConfNode shiZiQiuConfNode){
		return shiZiQiuConfNodeService.update(shiZiQiuConfNode);
	}
	
	

}
