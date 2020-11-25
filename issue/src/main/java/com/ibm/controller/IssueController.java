package com.ibm.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

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
	@GetMapping("/selectissuecount")
	public PageInfo<Issue> issueCount(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<Issue> pageInfo = new PageInfo<Issue>(issueService.issueCount());
//		if (pageInfo.getList().isEmpty()) {
//			model.addAttribute("selectmsg", "查询不到");
//		}
		return pageInfo;
	}

	@ApiOperation("功能：查询用户id或名字的issue")
	@GetMapping("/selectissuebyidorname")
	public PageInfo<IssueUser> issueIdOrName(int pageNum, int pageSize, @ApiParam("issue对象") IssueUser issue) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.issueIdOrName(issue));
//		if (pageInfo.getList().isEmpty()) {
//			model.addAttribute("selectmsg", "查询不到");
//		}
		return pageInfo;
	}

	@ApiOperation("功能：创建issue")
	@PostMapping("/createIssue")
	public Map<String, Object> issue(@ApiParam("Issue题目") @RequestParam(value = "title", required = true) String title,
			@ApiParam("Issue类型") @RequestParam(value = "type", required = true) String type,
			@ApiParam("Issue等级") @RequestParam(value = "level", required = true) String level,
			@ApiParam("影响版本") @RequestParam(value = "version", required = true) String version,
			@ApiParam("创建人") @RequestParam(value = "creater", required = true) String creater,
			@ApiParam("计划修改完成时间") @RequestParam(value = "plantime", required = true) String plantime,
//			@ApiParam("实际完成时间") @RequestParam(value = "acttime", required = true) Timestamp acttime,
			@ApiParam("重现步骤") @RequestParam(value = "step", required = true) String step,
//			@ApiParam("解决方案") @RequestParam(value = "solution", required = true) String solution,
			@ApiParam("指派修改人ID") @RequestParam(value = "userID", required = true) Integer userID) {
		Map<String, Object> result = new HashMap<String, Object>();
		Issue issue = new Issue();
		issue.setTitle(title);
		issue.setType(type);
		issue.setLevel(level);
		issue.setVersion(version);
		issue.setPlantime(plantime);
		issue.setActtime(null);
		issue.setStep(step);
		issue.setUserID(userID);
		if (issueService.findByUserID(userID) == null) {
			result.put("status", "没有此userID");
			return result;
		}
		// -----------------------
		issue.setCreater(creater);
		issue.setIssuestate("待修改");
		issue.setSolution(null);
		issueService.addIssue(issue);
		result.put("status", "创建成功");
		return result;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {

		// 转换日期
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
	}

	@ApiOperation("功能：显示登录用户issue任务")
	@GetMapping("/selectIssuetask")
	public PageInfo<IssueUser> issueTask(int pageNum, int pageSize, int userID) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.findIssuetask(userID));
		return pageInfo;
	}

	@ApiOperation("功能：模糊查询issue表")
	@GetMapping("/selectLikeIssue")
	public PageInfo<IssueUser> findLikeIssue(int pageNum, int pageSize, @ApiParam("用户类对象") IssueUser issueUser) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.findLikeIssue(issueUser));
		return pageInfo;
	}
	@ApiOperation("功能：查询issue表全部")
	@GetMapping("/selectIssueAll")
	public PageInfo<IssueUser> findIssueAll(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.findIssueAll());
		return pageInfo;
	}

	@ApiOperation("功能：修改issue信息")
	@PostMapping("/updateIssue")
	public Map<String, Object> updateUser(@ApiParam("Issue类对象") Issue issue) {
		Map<String, Object> result = new HashMap<String, Object>();
		issue.setIssuestate("待验证");
		issueService.UpdateIssue(issue);
		result.put("status", "修改成功");
		return result;
	}

	@ApiOperation("功能：修改issue解决方案solution")
	@PostMapping("/updateUserSolution")
	public Map<String, Object> updateUserSolution(@RequestParam(value = "userID", required = true) Integer userID,
			@RequestParam(value = "issueID", required = true) Integer issueID,
			@RequestParam(value = "solution", required = true) String solution) {
		Map<String, Object> result = new HashMap<String, Object>();
		Issue issue = new Issue();
		issue.setUserID(userID);
		issue.setIssueID(issueID);
		issue.setSolution(solution);
		issueService.UpdateIssueSolution(issue);
		result.put("status", "修改成功");
		return result;
	}

	@ApiOperation("功能：修改issue状态")
	@PostMapping("/updateIssueState")
	public Map<String, Object> UpdateIssueState(
			@RequestParam(value = "issueID", required = true) Integer issueID,
			@RequestParam(value = "issuestate", required = true) String issuestate) {
		Map<String, Object> result = new HashMap<String, Object>();
		Issue issue = new Issue();
		issue.setIssueID(issueID);
		issue.setIssuestate(issuestate);
		if (issueService.compareIssueidUserid(issue) == null) {
			   result.put("status", "IssueID不存在");
			   return result;
			  }
		issueService.UpdateIssueState(issue);
		result.put("status", "修改成功");
		return result;
	}
	
	@ApiOperation("功能：显示登录用户创建的issue")
	@GetMapping("/findCreateIssue")
	public PageInfo<IssueUser> findCreateIssue(int pageNum, int pageSize, String creater) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.findCreateIssue(creater));
		return pageInfo;
	}
	
	@ApiOperation("功能：模糊查询登录用户的新建issue")
	@GetMapping("/findLikeNewIssue")
	public PageInfo<IssueUser> findLikeNewIssue(int pageNum, int pageSize, @ApiParam("用户类对象") IssueUser issueUser) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<IssueUser> pageInfo = new PageInfo<IssueUser>(issueService.findLikeNewIssue(issueUser));
		return pageInfo;
	}
}
