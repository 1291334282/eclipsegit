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
import com.ibm.entity.User;
import com.ibm.entity.UserRole;
import com.ibm.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
@Api(tags = "用户控制类")
public class UserController {
	@Autowired
	private UserService userService;

	@ApiOperation("功能：修改个人信息")
	@PostMapping("/updateuser")
	public Map<String, Object> updateUser(@ApiParam("用户类对象") User user) {
		Map<String, Object> result = new HashMap<String, Object>();
		userService.updateUser(user);
		result.put("status", "修改个人信息成功");
		return result;
	}

	@ApiOperation("功能：修改权限从普通用户变为经理")
	@PostMapping("/updaterole")
	public Map<String, Object> updateRole(int userID) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (userService.findByUserId(userID).getUserstate().equals("注销")) {
			result.put("status", "用户名已被注销，不能修改为经理");
			return result;
		}
		userService.updateRole(userID);
		result.put("status", "修改为经理成功");
		return result;
	}

	@ApiOperation("功能：修改状态为注销")
	@PostMapping("/updateuserstate")
	public Map<String, Object> updateUserState(int userID) {
		Map<String, Object> result = new HashMap<String, Object>();
		userService.updateUserState(userID);
		result.put("status", "修改状态为注销成功");
		return result;
	}

	@ApiOperation("功能：查询所有用户信息")
	@GetMapping("selectalluser")
	public List<UserRole> selectAllUser(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<UserRole> pageInfo = new PageInfo<UserRole>(userService.findAll());
//		if (pageInfo.getList().isEmpty()) {
//			model.addAttribute("selectmsg", "查询不到");
//		}
		return pageInfo.getList();
	}

	@ApiOperation("功能：模糊查询用户id或名字")
	@GetMapping("selectidorname")
	public List<UserRole> selectIdOrName(int pageNum, int pageSize, @ApiParam("用户类对象") UserRole userRole, Model model) {
		PageHelper.startPage(pageNum, pageSize);
		PageInfo<UserRole> pageInfo = new PageInfo<UserRole>(userService.findIdOrName(userRole));
//		if (pageInfo.getList().isEmpty()) {
//			model.addAttribute("selectmsg", "查询不到");
//		}
		return pageInfo.getList();
	}

}
