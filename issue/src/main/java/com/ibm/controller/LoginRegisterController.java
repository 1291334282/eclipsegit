package com.ibm.controller;

import org.apache.shiro.subject.Subject;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.entity.User;
import com.ibm.entity.UserRole;
import com.ibm.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/loginregister")
@Api(tags = "用户登陆注册接口")
public class LoginRegisterController {
	@Autowired
	private UserService userService;

	@PostMapping("/main")
	public User main() {
		return new User();
	}

	@ApiOperation("功能：登陆接口")
	@PostMapping("/login")
	public Map<String, Object> login(@ApiParam("用户名") @RequestParam(value = "name", required = true) String name,
			@ApiParam("密码") @RequestParam(value = "password", required = true) String password) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(name, password);
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			subject.login(token);
			result.put("status", "登陆成功");
			result.put("name", name);
			result.put("userID", userService.findByName(name).getUserID());
			result.put("role", userService.findByName(name).getRole());
		} catch (UnknownAccountException e) {
			result.put("status", "用户名不存在");
		} catch (IncorrectCredentialsException e) {
			result.put("status", "密码错误");
		}
		return result;

	}

	@ApiOperation("功能：未授权的网页跳转")
	@GetMapping("/unauth")
	public Map<String, Object> unauth() {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("status", "未授权，无法访问");
		return result;
	}

	@ApiOperation("功能：退出登录")
	@GetMapping("/logout")
	public Map<String, Object> logout() {
		Map<String, Object> result = new HashMap<String, Object>();
		Subject subject = SecurityUtils.getSubject();
		subject.logout();
		result.put("status", "退出成功");
		return result;
	}

	@ApiOperation("功能：注册")
	@PostMapping("/register")
	public Map<String, Object> register(
			@ApiParam("用户ID") @RequestParam(value = "userID", required = true) Integer userID,
			@ApiParam("用户名") @RequestParam(value = "name", required = true) String name,
			@ApiParam("邮箱") @RequestParam(value = "email", required = true) String email,
			@ApiParam("密码") @RequestParam(value = "password", required = true) String password) {
		Map<String, Object> result = new HashMap<String, Object>();
		User userRole = new UserRole();
		userRole = userService.findByName(name);
		if (userRole != null) {
//			if (userRole.getUserstate().equals("注销")) {
//				userService.updateUserStateTrue(name);
//				result.put("status", "用户名本已注销，重新注册成功");
//				return result;
//			} else {
//				result.put("status", "用户名已存在");
//				return result;
//			}
			result.put("status", "用户名已存在");
			return result;
		}
		userRole.setUserID(userID);
		userRole.setRoleID(0);
		userRole.setName(name);
		userRole.setEmail(email);
		userRole.setUserstate("激活");
		userRole.setPassword(password);
		userService.addUser(userRole);
		result.put("status", "注册成功");
		return result;
	}

//	@GetMapping("/se")
//	public UserRole se(String name) {
//		UserRole userRole=userService.findByName(name);
//		return userRole;
//	}

}
