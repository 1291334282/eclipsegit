package com.ibm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ibm.entity.User;
import com.ibm.entity.UserRole;
import com.ibm.mapper.UserRoleMapper;

@Service
public class UserService {
	@Autowired
	UserRoleMapper userMapper;

	public UserRole findByName(String name) {
		// TODO Auto-generated method stub
		return userMapper.findByName(name);
	}

	public User findByUserId(int userID) {
		// TODO Auto-generated method stub
		return userMapper.findByUserId(userID);
	}

	public void addUser(User user) {
		userMapper.addUser(user);
	}

	public void updateUser(User user) {
		userMapper.updateUser(user);
	}

	public void updateRole(int userID) {
		userMapper.updateRole(userID);
	}

	public void updateUserState(int userID) {
		userMapper.updateUserState(userID);
	}

	public void updateUserStateTrue(String name) {
		userMapper.updateUserStateTrue(name);
	}

	public List<UserRole> findAll() {
		return userMapper.findAll();

	}

	public List<UserRole> findUserId() {
		return userMapper.findUserId();

	}

	public List<UserRole> findIdOrName(UserRole userRole) {
		return userMapper.findIdOrName(userRole);
	}

}
