package com.ibm.mapper;

import java.util.List;

import com.ibm.entity.User;
import com.ibm.entity.UserRole;

public interface UserRoleMapper {
	public UserRole findByName(String name);

	public User findByUserId(int userID);

	public void addUser(User user);

	public void updateUser(User user);

	public void updateRole(int userID);

	public void updateUserState(int userID);

	public void updateUserStateTrue(String name);

	public List<UserRole> findAll();

	public List<UserRole> findUserId();

	public List<UserRole> findIdOrName(UserRole userRole);
}
