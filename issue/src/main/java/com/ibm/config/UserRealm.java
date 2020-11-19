package com.ibm.config;

import org.apache.shiro.subject.Subject;

import java.util.HashSet;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.entity.User;
import com.ibm.entity.UserRole;
import com.ibm.service.UserService;

public class UserRealm extends AuthorizingRealm {
	@Autowired
	private UserService userService;
    //授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Subject subject=SecurityUtils.getSubject();
		UserRole user=(UserRole)subject.getPrincipal();
		 //设置角色
        Set<String> roles = new HashSet<>();
        roles.add(user.getRole());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo(roles);

        //设置权限
        info.addStringPermission(user.getRole());
		return info;
	}

    //认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		UsernamePasswordToken token2=(UsernamePasswordToken) token;
		UserRole user = userService.findByName(token2.getUsername());
		if (user==null) {
			return null;
		}
		return new SimpleAuthenticationInfo(user,user.getPassword(),"");
	}

}
