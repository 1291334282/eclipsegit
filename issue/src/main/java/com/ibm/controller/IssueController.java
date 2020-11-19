package com.ibm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibm.entity.Issue;
import com.ibm.entity.IssueUser;
import com.ibm.entity.User;
import com.ibm.entity.UserRole;
import com.ibm.service.IssueService;
import com.ibm.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/issue")
@Api(tags = "issue控制类")
public class IssueController {
	@Autowired
	private IssueService issueService;


	@ApiOperation("功能：查看报表")
	@GetMapping("issuecount")
	public List<Issue> issueCount() {
		PageHelper.startPage(1, 20);	
		PageInfo<Issue> pageInfo = new PageInfo<Issue>(issueService.issueCount());
//		if (pageInfo.getList().isEmpty()) {
//			model.addAttribute("selectmsg", "查询不到");
//		}
		return pageInfo.getList();
	}
	
	@ApiOperation("功能：查询用户id或名字的issue")
	@GetMapping("issuebyidorname")
	public List<IssueUser> issueIdOrName( @ApiParam("issue对象") IssueUser issue) {
		PageHelper.startPage(1, 20);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.issueIdOrName(issue));
//		if (pageInfo.getList().isEmpty()) {
//			model.addAttribute("selectmsg", "查询不到");
//		}
		return pageInfo.getList();
	}
}
